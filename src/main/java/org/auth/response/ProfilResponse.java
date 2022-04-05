package org.auth.response;

import java.io.Serializable;
import java.util.List;


public class ProfilResponse implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;
	private String name;
    private List<PrivilegeResponse> privileges;
 
	   
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PrivilegeResponse> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<PrivilegeResponse> privileges) {
		this.privileges = privileges;
	}
	

}
