package com.tw.beach.splitwise.entity;

import com.tw.beach.splitwise.Common.BillType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity
public class Bill {
    @Id
    @NotNull
    @Column(name = "bill_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;

    @NotNull
    @Column(name = "amount")
    private final Double amount;

    @NotNull
    @Column(name = "type")
    private final BillType type;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_id")
    private final List<Friend> paidFor;

    @NotNull
    @Column(name = "name")
    private final Map<Friend, Double> expenseRatio;

    private final static Double defaultExpenseRatio = 1.0;

    public Bill(Double amount, BillType type, List<Friend> paidFor, Map<Friend, Double> expenseRatio) {
        this.amount = amount;
        this.type = type;
        this.paidFor = paidFor;
        this.expenseRatio = expenseRatio;
    }

    void settle() {
        int friendsCount = paidFor.size();
        for (Friend friend : this.paidFor) {
            Double ratio = findExpenseRatio(friend);
            Double perFriendAmount = ratio * this.amount;

            if (Double.compare(ratio, defaultExpenseRatio) == 0) {
                perFriendAmount = perFriendAmount / friendsCount;
            }

            friend.updateAmountToPay(perFriendAmount);
        }
    }

    private Double findExpenseRatio(Friend friend) {
        if (this.expenseRatio == null)
            return defaultExpenseRatio;
        return expenseRatio.getOrDefault(friend, defaultExpenseRatio);
    }
}