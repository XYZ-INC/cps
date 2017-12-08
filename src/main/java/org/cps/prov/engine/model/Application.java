package org.cps.prov.engine.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.cps.identity.model.User;

 
/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 *
 */
@Entity
@Table(name = "APPLICATION")
@ManagedBean(name = "application")
public class Application {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique=true,nullable=false)
	private String name;

	private String description;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean isTrusted;

	@ManyToOne(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinColumn(name="app_def_id")
	private ApplicationDefinition appDefinition;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="app_id")
	private List<ApplicationParams> params;

    @ManyToMany(mappedBy="applications")
    private Collection<ProvisioningPolicy> provPolicies;

	
    private String createdBy;
    private String updatedBy;
    private Date createdDate;
    private Date updatedDate;

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

	public ApplicationDefinition getAppDefinition() {
		return appDefinition;
	}

	public void setAppDefinition(ApplicationDefinition appDefinition) {
		this.appDefinition = appDefinition;
	}

	
	public List<ApplicationParams> getParams() {
		return params;
	}

	public void setParams(List<ApplicationParams> params) {
		this.params = params;
	}

	public boolean isTrusted() {
		return isTrusted;
	}

	public void setTrusted(boolean isTrusted) {
		this.isTrusted = isTrusted;
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

	
	
	public Collection<ProvisioningPolicy> getProvPolicies() {
		return provPolicies;
	}

	public void setProvPolicies(Collection<ProvisioningPolicy> provPolicies) {
		this.provPolicies = provPolicies;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", description=" + description + ", addDefinition : " + appDefinition.getName();
	}

}
