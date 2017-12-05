package org.cps.sync.engine.model;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SYNC_ATTR_VALUE")
@ManagedBean(name = "application")
public class SyncAttrValue {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String value;
	
	@ManyToOne
    @JoinColumn(name="attr_id")
	private SyncAttribute syncAttr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public SyncAttribute getSyncAttr() {
		return syncAttr;
	}

	public void setSyncAttr(SyncAttribute syncAttr) {
		this.syncAttr = syncAttr;
	}


}
