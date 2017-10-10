package com.db.shelly.domain;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {

	@Size(min=2,message="Name should have atleast 2 characters")
	private String name;
	
	@Past
	private Date dateOfBirth;
	private Integer id;
	
	public User(){
		
	}
	
	public User(Integer id, String name, Date dateOfBirth){
		this.id = id;
		this.name= name;
		this.dateOfBirth = dateOfBirth;
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
