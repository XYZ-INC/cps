package org.cps.sync.engine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ENTITY_MATCHINE_RULE")
public class MatchingRule {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String cpsAttibute;
	
	private String tgtAttribute;
	
	private MATCHING_ACTION matchingAction;
	
	private MATCHING_CONDITION matchingCondition;
	
	@OneToOne
    @JoinColumn(name="profile_id")
	private SyncProfile syncProfile;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpsAttibute() {
		return cpsAttibute;
	}

	public void setCpsAttibute(String cpsAttibute) {
		this.cpsAttibute = cpsAttibute;
	}

	public String getTgtAttribute() {
		return tgtAttribute;
	}

	public void setTgtAttribute(String tgtAttribute) {
		this.tgtAttribute = tgtAttribute;
	}

	public MATCHING_ACTION getMatchingAction() {
		return matchingAction;
	}

	public void setMatchingAction(MATCHING_ACTION matchingAction) {
		this.matchingAction = matchingAction;
	}

	public MATCHING_CONDITION getMatchingCondition() {
		return matchingCondition;
	}

	public void setMatchingCondition(MATCHING_CONDITION matchingCondition) {
		this.matchingCondition = matchingCondition;
	}

	public SyncProfile getSyncProfile() {
		return syncProfile;
	}

	public void setSyncProfile(SyncProfile syncProfile) {
		this.syncProfile = syncProfile;
	}
	
	

}
