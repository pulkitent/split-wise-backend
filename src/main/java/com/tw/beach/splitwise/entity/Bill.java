package com.tw.beach.splitwise.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="bill")
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
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "bill")
    private final List<PaidFor> paidFor;
    
    @NotNull
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "bill")
    private final List<PaidBy> paidBy;    

	public Bill(Double amount, List<PaidFor> paidFor,List<PaidBy> paidBy) {
        this.amount  = amount;
        this.paidFor = paidFor;
        this.paidBy  = paidBy;
    }
	

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", amount=" + amount + ", paidFor=" + paidFor + ", paidBy=" + paidBy + "]";
	}

}