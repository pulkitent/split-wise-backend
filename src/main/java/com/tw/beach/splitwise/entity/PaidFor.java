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
@Table(name="paid_for")
public class PaidFor {
	@Id
    @NotNull
    @Column(name = "paid_for_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paidForId;
	
	@NotNull
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.DETACH, CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "bill_id")
	private Long billId;
	
	@NotNull
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.DETACH, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
	          name = "paid_for_friend",
	          joinColumns = {@JoinColumn(name = "paid_for_id")},
	          inverseJoinColumns = {@JoinColumn(name = "friend_id")}
			  )
	private Friend paidForFriend;
	
	 @NotNull
	 @Column(name = "amount")
	 private Double amount;

	public Long getPaidForId() {
		return paidForId;
	}

	public void setPaidForId(Long paidForId) {
		this.paidForId = paidForId;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Friend getPaidForFriend() {
		return paidForFriend;
	}

	public void setPaidForFriend(Friend paidForfriend) {
		this.paidForFriend = paidForfriend;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PaidFor(@NotNull Long paidForId, @NotNull Long billId, @NotNull Friend paidForfriend, @NotNull Double amount) {
		this.paidForId = paidForId;
		this.billId = billId;
		this.paidForFriend = paidForfriend;
		this.amount = amount;
	}

	public PaidFor() {
		
	}

	@Override
	public String toString() {
		return "PaidFor [paidForId=" + paidForId + ", billId=" + billId + ", paidForFriend=" + paidForFriend
				+ ", amount=" + amount + "]";
	}
	 
}