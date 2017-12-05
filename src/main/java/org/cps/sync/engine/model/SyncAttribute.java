package org.cps.sync.engine.model;

import java.util.List;

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
import javax.persistence.Table;

@Entity
@Table(name = "SYNC_ATTR")
@ManagedBean(name = "syncAttribute")
public class SyncAttribute {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="attr_id")
	private List<SyncAttrValue> value;

	private ATTR_TYPE type;
	
	@ManyToOne
    @JoinColumn(name="event_id")
	private SyncEvent syncEvent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SyncAttrValue> getValue() {
		return value;
	}

	public void setValue(List<SyncAttrValue> value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ATTR_TYPE getType() {
		return type;
	}

	public void setType(ATTR_TYPE type) {
		this.type = type;
	}

	
}
