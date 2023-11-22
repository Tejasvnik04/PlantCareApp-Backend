package com.example.demo.response;

import com.example.demo.model.enumerate.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse 
{
    private String token;
    private Long uid;
    private Role role;
    
    public Role getRole() {
    	return role;
    }
    
    public void setRole(Role role) {
    	this.role = role;
    }
    
    public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
}
