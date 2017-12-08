package org.cps.prov.engine.model;

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
@Table(name = "APP_PARAMS")
@ManagedBean(name = "applicationConfigParams")
public class ApplicationParams {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false)
	private String name;

	private String description;

	@Column(nullable=false)
	private APP_PARAM_TYPE type;

	@Column(nullable=false)
	private String value;

	private boolean  encrypted;

	@ManyToOne
    @JoinColumn(name="app_id")
	private Application application;


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

	public APP_PARAM_TYPE getType() {
		return type;
	}

	public void setType(APP_PARAM_TYPE type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	public String getDescription() {
		return description;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name+ ", value=" + value+ ", type=" + type+ ", description=" + description ;
	}

}
