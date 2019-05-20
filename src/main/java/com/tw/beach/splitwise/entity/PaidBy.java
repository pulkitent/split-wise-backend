package com.tw.beach.splitwise.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="paid_by")
public class PaidBy {

	@Id
    @NotNull
    @Column(name = "paid_by_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paidById;
	
	@NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.DETACH, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "bill_id")
	private Long billId;
	
	@NotNull
	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.DETACH, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
	          name = "paid_by_Friend",
	          joinColumns = {@JoinColumn(name = "paid_by_id")},
	          inverseJoinColumns = {@JoinColumn(name = "friend_id")}
			  )

	private Friend paidByfriend;
	
	 @NotNull
	 @Column(name = "amount")
	 private Double amount;

	public Long getPaidById() {
		return paidById;
	}

	public void setPaidById(Long paidById) {
		this.paidById = paidById;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Friend getPaidByfriend() {
		return paidByfriend;
	}

	public void setPaidByfriend(Friend paidByfriend) {
		this.paidByfriend = paidByfriend;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PaidBy(@NotNull Long paidById, @NotNull Long billId, @NotNull Friend paidByfriend, @NotNull Double amount) {
		this.paidById = paidById;
		this.billId = billId;
		this.paidByfriend = paidByfriend;
		this.amount = amount;
	}
	
	public PaidBy() {
		
	}

	@Override
	public String toString() {
		return "PaidBy [paidById=" + paidById + ", billId=" + billId + ", paidByfriend=" + paidByfriend + ", amount="
				+ amount + "]";
	}
	
}
