package org.cps.prov.application.model;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 *
 */
@Entity
@Table(name = "APP_DEF")
@ManagedBean(name = "appDefinition")
public class ApplicationDefinition {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="app_def_id")
	private List<ApplicationDefParams> params;

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

	public List<ApplicationDefParams> getParams() {
		return params;
	}

	public void setParams(List<ApplicationDefParams> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", description=" + description;
	}

}
