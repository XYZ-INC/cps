package org.cps.prov.application.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

 
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

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="app_id")
	private List<ApplicationConfigParams> configParams;


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

	
	
	public List<ApplicationConfigParams> getConfigParams() {
		return configParams;
	}

	public void setConfigParams(List<ApplicationConfigParams> configParams) {
		this.configParams = configParams;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", description=" + description + ", addDefinition : " + appDefinition.getName();
	}

}
