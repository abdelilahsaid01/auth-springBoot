package org.auth.shared.dto;

import java.io.Serializable;
import java.util.List;


public class ProfilDto implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;
	private String name;
    private List<PrivilegeDto> privileges;
 
	   
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
	public List<PrivilegeDto> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<PrivilegeDto> privileges) {
		this.privileges = privileges;
	}

}
