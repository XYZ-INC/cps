package org.cps.prov.engine.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.cps.identity.model.Group;

@Entity
@Table(name = "PROV_POLICY")
@ManagedBean(name = "provPolicy")
public class ProvisioningPolicy {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique=true,nullable=false)
	private String name;

	private String description;
	
    private String createdBy;
    private String updatedBy;
    private Date createdDate;
    private Date updatedDate;

	
    @OneToMany(mappedBy = "id.policy",
            cascade = CascadeType.ALL)
    private Set<AppPolicyGroup> appPolicyGroups = new HashSet<AppPolicyGroup>();
	
	@ManyToMany
    @JoinTable(name="PROV_APP", 
    joinColumns=@JoinColumn(name="PROV_ID"),
    inverseJoinColumns=@JoinColumn(name="APP_ID"))
	private List<Application> applications;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
    public Set<AppPolicyGroup> getAppPolicyGroups() {
        return appPolicyGroups;
    }
 
    public void setAppPolicyGroups(Set<AppPolicyGroup> appPolicyGroups) {
        this.appPolicyGroups = appPolicyGroups;
    }
     
    public void addAppPolicyGroup(AppPolicyGroup appPolicyGroup) {
        this.appPolicyGroups.add(appPolicyGroup);
    }  
    
//	public List<Group> getAllowedGroup() {
//		return allowedGroup;
//	}
//
//	public void setAllowedGroup(List<Group> allowedGroup) {
//		this.allowedGroup = allowedGroup;
//	}
//
//	public List<Group> getDeniedGroup() {
//		return deniedGroup;
//	}
//
//	public void setDeniedGroup(List<Group> deniedGroup) {
//		this.deniedGroup = deniedGroup;
//	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	
}
