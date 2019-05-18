package com.tw.beach.splitwise.entity;

import com.tw.beach.splitwise.Common.BillType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Bill")
public class Bill {
    @Id
    @NotNull
    @Column(name = "bill_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billId;

    @NotNull
    @Column(name = "amount")
    private Double amount;

    @NotNull
    @Column(name = "type")
    private BillType type;

    //    @NotNull
    //    @OneToMany(cascade = CascadeType.ALL)
    //    @JoinColumn(name = "friend_id")
    @ManyToMany(mappedBy = "bill")
    private List<Friend> paidFor;

    private final static Double defaultExpenseRatio = 1.0;

    public Bill() {

    }

    public Bill(Double amount, BillType type, List<Friend> paidFor) {
        this.amount = amount;
        this.type = type;
        this.paidFor = paidFor;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BillType getType() {
        return type;
    }

    public void setType(BillType type) {
        this.type = type;
    }

    public List<Friend> getPaidFor() {
        return paidFor;
    }

    public void setPaidFor(List<Friend> paidFor) {
        this.paidFor = paidFor;
    }

    public static Double getDefaultExpenseRatio() {
        return defaultExpenseRatio;
    }


    void settle() {
        int friendsCount = paidFor.size();
        Double perFriendAmount = this.amount / friendsCount;
        for (Friend friend : this.paidFor) {
            friend.updateAmountToPay(perFriendAmount);
        }
    }
}