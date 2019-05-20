package com.tw.beach.splitwise.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="friend")
public class Friend {
    @Id
    @NotNull
    @Column(name = "friend_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long friendId;

    @NotNull
    @Column(name = "name")
    private final String name;
    
    @NotNull
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.DETACH, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
	          name = "paid_for_friend",
	          joinColumns = {@JoinColumn(name = "friend_id")},
	          inverseJoinColumns = {@JoinColumn(name = "paid_for_id")}
			  )
	private PaidFor paidForBill;
    
    @NotNull
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.DETACH, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
	          name = "paid_by_friend",
	          joinColumns = {@JoinColumn(name = "friend_id")},
	          inverseJoinColumns = {@JoinColumn(name = "paid_by_id")}
			  )
	private PaidBy paidByBill;

	public Long getFriendId() {
		return friendId;
	}

	public void setFriendId(Long friendId) {
		this.friendId = friendId;
	}

	public PaidFor getPaidForBill() {
		return paidForBill;
	}

	public void setPaidForBill(PaidFor paidForBill) {
		this.paidForBill = paidForBill;
	}

	public PaidBy getPaidByBill() {
		return paidByBill;
	}

	public void setPaidByBill(PaidBy paidByBill) {
		this.paidByBill = paidByBill;
	}

	public String getName() {
		return name;
	}

	public Friend(@NotNull Long friendId, @NotNull String name, @NotNull PaidFor paidForBill,
			@NotNull PaidBy paidByBill) {
		this.friendId = friendId;
		this.name = name;
		this.paidForBill = paidForBill;
		this.paidByBill = paidByBill;
	}

	@Override
	public String toString() {
		return "Friend [friendId=" + friendId + ", name=" + name + ", paidForBill=" + paidForBill + ", paidByBill="
				+ paidByBill + "]";
	}

}