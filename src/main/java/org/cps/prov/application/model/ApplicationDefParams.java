package org.cps.prov.application.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 *
 */
@Entity
@Table(name = "APP_DEF_PARAMS")
@ManagedBean(name = "appDefParams")
public class ApplicationDefParams {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;

	private String type;

	private String defalutValue;

	@ManyToOne
    @JoinColumn(name="app_def_id")
	private ApplicationDefinition appDefinition;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDefalutValue() {
		return defalutValue;
	}

	public void setDefalutValue(String defalutValue) {
		this.defalutValue = defalutValue;
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

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", defalutValue=" + defalutValue
				+ ", type=" + type + ", description=" + description;
	}

}
