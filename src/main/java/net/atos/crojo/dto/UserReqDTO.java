package net.atos.crojo.dto;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReqDTO extends BaseDTO{

    private String name;
    private String lastName;
    private String surName;
    private String email;
    private String role;
    private String username;
    private String password;
    private String status;
   
	
		
}
