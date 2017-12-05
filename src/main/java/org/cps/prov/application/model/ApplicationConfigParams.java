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
@Table(name = "APP_CONFIG_PARAMS")
@ManagedBean(name = "applicationParams")
public class ApplicationConfigParams {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String sourceAttribute;

	private String targetAttribute;

//	private String type;
//
//	private String length;
//	
//	private int order;
	

	@ManyToOne
    @JoinColumn(name="app_id")
	private Application application;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getSourceAttribute() {
		return sourceAttribute;
	}

	public void setSourceAttribute(String sourceAttribute) {
		this.sourceAttribute = sourceAttribute;
	}

	public String getTargetAttribute() {
		return targetAttribute;
	}

	public void setTargetAttribute(String targetAttribute) {
		this.targetAttribute = targetAttribute;
	}

	public Application getApplication() {
		return application;
	}
 
	public void setApplication(Application application) {
		this.application = application;
	}

//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	public String getLength() {
//		return length;
//	}
//
//	public void setLength(String length) {
//		this.length = length;
//	}
//
//	public int getOrder() {
//		return order;
//	}
//
//	public void setOrder(int order) {
//		this.order = order;
//	}

//	@Override
//	public String toString() {
//		return "id=" + id + ", sourceAttribute=" + sourceAttribute+ ", targetAttribute=" + targetAttribute + 
//				", type=" + type + ", length=" + length+ ", order=" + order;
//	}

}
