package com.tw.beach.splitwise.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Friend")
public class Friend {
    @Id
    @NotNull
    @Column(name = "friend_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long friendId;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "amount_to_pay")
    private Double amountToPay;

    @NotNull
    @Column(name = "amount_paid")
    private Double amountPaid;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Bill_Friend",
            joinColumns = {@JoinColumn(name = "fiend_id")},
            inverseJoinColumns = {@JoinColumn(name = "bill_id")}
    )
    private List<Bill> bill;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_be_paid_by")
    private List<SettlementAmount> settlementAmounts;

    public Friend() {

    }

    public Friend(String name, Double amountPaid, Double amountToPay) {
        this.name = name;
        this.amountPaid = amountPaid;
        this.amountToPay = amountToPay;
        this.settlementAmounts = new LinkedList<>();
        this.bill = new LinkedList<>();
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public List<Bill> getBill() {
        return bill;
    }

    public void setBill(List<Bill> bill) {
        this.bill = bill;
    }

    public List<SettlementAmount> getSettlementAmounts() {
        return settlementAmounts;
    }

    public void setSettlementAmounts(List<SettlementAmount> settlementAmounts) {
        this.settlementAmounts = settlementAmounts;
    }

    public List<SettlementAmount> getSettlementAmount() {
        return settlementAmounts;
    }

    void updateAmountToPay(Double amount) {
        this.amountToPay += amount;
    }

    void settlementAmountWith(Friend friend, Double amount) {
        this.amountToPay = this.amountToPay - amount;
        this.addSettlementAmount(new SettlementAmount(amount, friend));
    }

    void receiveAmountFrom(Friend friend, Double amount) {
        this.amountPaid = this.amountPaid - amount;
        friend.addSettlementAmount(new SettlementAmount(amount, this));
    }

    Double calculatePaidAndToPayDifference() {
        return this.amountPaid - this.amountToPay;
    }

    private void addSettlementAmount(SettlementAmount settlementAmount) {
        this.settlementAmounts.add(settlementAmount);
    }

    int compare(Friend friend) {
        Integer finalAmount = (int) Math.abs(friend.amountPaid - friend.amountToPay);
        Integer anotherFinalAmount = (int) Math.abs(this.amountPaid - this.amountToPay);

        return anotherFinalAmount - finalAmount;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Friend friend = (Friend) o;
        return Objects.equals(name, friend.name) &&
                Objects.equals(amountToPay, friend.amountToPay) &&
                Objects.equals(amountPaid, friend.amountPaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}