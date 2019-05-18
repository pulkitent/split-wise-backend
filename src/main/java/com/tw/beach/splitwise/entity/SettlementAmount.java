package com.tw.beach.splitwise.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
//This class represents debt amount to be paid to whom
@Table(name="Settlement_Amount")
public class SettlementAmount {
    @Id
    @NotNull
    @Column(name = "settlement_amount_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long settlementId;

    @NotNull
    @Column(name = "value")
    private final Double value;

    @NotNull
    @Column(name = "to_be_paid_to")
    private final Friend toBePaidTo;

    SettlementAmount(Double value, Friend toBePaidTo) {
        this.value = value;
        this.toBePaidTo = toBePaidTo;
    }

    @Override
    public String toString() {
        return value + " " + toBePaidTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SettlementAmount settlementAmount = (SettlementAmount) o;
        return Objects.equals(value, settlementAmount.value) &&
                Objects.equals(toBePaidTo, settlementAmount.toBePaidTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, toBePaidTo);
    }
}