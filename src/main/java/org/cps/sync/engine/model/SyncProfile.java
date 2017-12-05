package org.cps.sync.engine.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "SYNC_PROFILE")
//@ManagedBean(name = "syncProfile")
public class SyncProfile {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique=true)
	private String name;
	
	private String tableName;
	
	private SYNC_TYPE syncType;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="profile_id")
	private List<ProfileAttribute> profileAttr;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="rule_id")
	private MatchingRule matchingRule;
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public List<ProfileAttribute> getProfileAttr() {
		return profileAttr;
	}
	
	public void setProfileAttr(List<ProfileAttribute> profileAttr) {
		this.profileAttr = profileAttr;
	}

	public SYNC_TYPE getSyncType() {
		return syncType;
	}

	public void setSyncType(SYNC_TYPE syncType) {
		this.syncType = syncType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public MatchingRule getMatchingRule() {
		return matchingRule;
	}

	public void setMatchingRule(MatchingRule matchingRule) {
		this.matchingRule = matchingRule;
	}


}
