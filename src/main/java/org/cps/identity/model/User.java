package org.cps.identity.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.cps.identity.common.UserManagerConstants;
import org.cps.identity.common.UserManagerUtils;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 *
 */
@Entity
@Table(name="USR")
@ManagedBean(name="user")
public class User {
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
      
	@Column(nullable = false)
    private String userName;
    private String password;
    private String email;
    private String firstName;  
    private String middleName; 
    private String lastName;
    private String passwordSalt;
    private String createdBy;
    private String updatedBy;
    private Date createdDate;
    private Date updatedDate;
    
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="org_id")
	//@Column(nullable = false)
    private Organization organization; 
	
	

    @ManyToMany 
    @JoinTable(name="USR_GRP", 
          joinColumns=@JoinColumn(name="USR_ID"),
          inverseJoinColumns=@JoinColumn(name="GRP_ID"))
    private Collection<Group> groups;
	

    public User(){
    	groups = new ArrayList<Group>();
    	
    }

 
	public int getId() {
        return id;
    }
  
    public void setId(int id) {
        this.id = id;
    }
  
      
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@Override
    public String toString(){
        return "id="+id+"";
    }

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt=passwordSalt;		
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Collection<Group> getGroups() {
		return groups;
	}

	public void addGroups(Group group) {
        if (!getGroups().contains(group)) {
        	getGroups().add(group);
        }
        if (!group.getUsers().contains(this)) {
        	group.getUsers().add(this);
        }

	}

	
 }
