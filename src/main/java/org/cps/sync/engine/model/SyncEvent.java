package org.cps.sync.engine.model;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.cps.identity.model.User;


@Entity
@Table(name = "SYNC_EVENT")
@ManagedBean(name = "syncEvent")
public class SyncEvent {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String uid;
	
	private String uidValue;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="event_id")
	private List<SyncAttribute> attributes;
	
	// Isko araam se dekhenge
//	private List<SyncAttribute> childAttributes;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User linkedUser;
	
//	private int linkedAcctountId;
	
	private String failureReason;
	private EVENT_STATE eventState;
	private Date creationDate;
	private Date actionDate;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="profile_id")
	private SyncProfile syncProfile;

	public String getUidValue() {
		return uidValue;
	}

	public void setUidValue(String uidValue) {
		this.uidValue = uidValue;
	}

	public List<SyncAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SyncAttribute> attributes) {
		this.attributes = attributes;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public SyncProfile getSyncProfile() {
		return syncProfile;
	}

	public void setSyncProfile(SyncProfile syncProfile) {
		this.syncProfile = syncProfile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getLinkedUser() {
		return linkedUser;
	}

	public void setLinkedUserId(User linkedUser) {
		this.linkedUser = linkedUser;
	}

 
	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public EVENT_STATE getEventState() {
		return eventState;
	}

	public void setEventState(EVENT_STATE eventState) {
		this.eventState = eventState;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	
	
	
}
