package org.cps.prov.engine.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.cps.identity.model.Group;

@Entity
@Table(name = "PROV_POL_GROUPS")
@AssociationOverrides({
    @AssociationOverride(name = "id.policy",
        joinColumns = @JoinColumn(name = "PROV_POLICY_ID")),
    @AssociationOverride(name = "id.group",
        joinColumns = @JoinColumn(name = "GROUP_ID")) })
public class AppPolicyGroup {

	@EmbeddedId
    private AppPolicyGroupID id = new AppPolicyGroupID();
    
	boolean allowed;
	  
	public AppPolicyGroupID getId() {
		return id;
	}

	public void setId(AppPolicyGroupID id) {
		this.id = id;
	}

	public ProvisioningPolicy getPolicy() {
		 
		return getId().getPolicy();
	}

	public void setPolicy(ProvisioningPolicy policy) {

		getId().setPolicy(policy);
	}

	public Group getGroup() {
 
		return getId().getGroup();
	}

	public void setGroup(Group group) {
		 System.out.println("setGroup : " + group);

		getId().setGroup(group);;
	}

	public boolean isAllowed() {
		return allowed;
	}

	public void setAllowed(boolean allowed) {
		this.allowed = allowed;
	}

     
	
	
}
