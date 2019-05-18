package com.tw.beach.splitwise.entity;

import com.tw.beach.splitwise.Common.BillType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    //    @NotNull
    //    @OneToMany(cascade = CascadeType.ALL)
    //    @JoinColumn(name = "friend_id")
    @ManyToMany(mappedBy = "bill")
    private final List<Friend> paidFor;

    private final static Double defaultExpenseRatio = 1.0;

    public Bill(Double amount, BillType type, List<Friend> paidFor) {
        this.amount = amount;
        this.type = type;
        this.paidFor = paidFor;
    }

    void settle() {
        int friendsCount = paidFor.size();
        Double perFriendAmount = this.amount / friendsCount;
        for (Friend friend : this.paidFor) {
            friend.updateAmountToPay(perFriendAmount);
        }
    }
}