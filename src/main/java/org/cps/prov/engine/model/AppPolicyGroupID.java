package org.cps.prov.engine.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.cps.identity.model.Group;

@Embeddable
public class AppPolicyGroupID implements Serializable{

	@ManyToOne(cascade = CascadeType.ALL)
	private ProvisioningPolicy policy;

	@ManyToOne(cascade = CascadeType.ALL)
	private Group group;

	public ProvisioningPolicy getPolicy() {
		return policy;
	}

	public void setPolicy(ProvisioningPolicy policy) {
		this.policy = policy;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
