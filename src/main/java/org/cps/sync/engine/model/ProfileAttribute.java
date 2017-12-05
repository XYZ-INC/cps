package org.cps.sync.engine.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROFILE_ATTR")
@ManagedBean(name = "profileAttribute")
public class ProfileAttribute {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String targetAttrName;
	
	private PR_ATTR_TYPE type;
	
	private int length;
	
	boolean isKey;
	
	@ManyToOne
    @JoinColumn(name="profile_id")
	private SyncProfile syncProfile;


	public enum PR_ATTR_TYPE{
		STRING,NUMBER,BOOLEAN,DATE;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PR_ATTR_TYPE getType() {
		return type;
	}

	public void setType(PR_ATTR_TYPE type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public boolean isKey() {
		return isKey;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

	public SyncProfile getSyncProfile() {
		return syncProfile;
	}

	public void setSyncProfile(SyncProfile syncProfile) {
		this.syncProfile = syncProfile;
	}

	public String getTargetAttrName() {
		return targetAttrName;
	}

	public void setTargetAttrName(String targetAttrName) {
		this.targetAttrName = targetAttrName;
	}
	
	
	
}
