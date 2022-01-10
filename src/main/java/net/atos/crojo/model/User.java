package net.atos.crojo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Users")
public class User extends Base{

	@Column(name="Name")
    private String name;
	@Column(name="LastName")
    private String lastName;
	@Column(name="SurName")
    private String surName;
	@Column(name="Email")
    private String email;
	@Column(name="Role")
    private String role;
	@Column(name="UserName")
    private String username;
	@Column(name="Password")
    private String password;
	@Column(name="Status")
    private String status;
	
	
	
	@Column(name="Usermodifier")
    private String userModifier;
	@Column(name="UserCreator")
    private String userCreator;
	
		
}
