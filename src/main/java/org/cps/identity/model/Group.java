package org.cps.identity.model;

import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.cps.prov.engine.model.AppPolicyGroup;


@Entity
@Table(name="GRP")
@ManagedBean(name="group")
public class Group {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

	private String name;
	
	private String description;
	
    private String createdBy;
    private String updatedBy;
    private Date createdDate;
    private Date updatedDate;
    
    @OneToMany(mappedBy = "id.group",
            cascade = CascadeType.ALL)
    private List<AppPolicyGroup> appPolicyGroups = new ArrayList<AppPolicyGroup>();


    @ManyToMany(mappedBy="groups")
    private Collection<User> users;
    
    public Group(){
    	users = new ArrayList<User>();
    }
    
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

	public Collection<User> getUsers() {
		return users;
	}

	public void addUsersToGroup(User user) {
        if (!getUsers().contains(user)) {
            getUsers().add(user);
        }
        if (!user.getGroups().contains(this)) {
            user.getGroups().add(this);
        }
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

	public List<AppPolicyGroup> getAppPolicyGroups() {
		return appPolicyGroups;
	}

	public void setAppPolicyGroups(List<AppPolicyGroup> appPolicyGroups) {
		this.appPolicyGroups = appPolicyGroups;
	}
	
	public void addAppPolicyGroup(AppPolicyGroup appPolicyGroup){
		this.appPolicyGroups.add(appPolicyGroup);
	}
	
    
	

}
