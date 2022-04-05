package org.auth.request;

import java.io.Serializable;
import java.util.List;


public class ProfilRequest implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Long id;
	private String name;
    private List<PrivilegeRequest> privilegesRequest;
 
	   
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
	public List<PrivilegeRequest> getPrivilegesRequest() {
		return privilegesRequest;
	}
	public void setPrivilegesRequest(List<PrivilegeRequest> privilegesRequest) {
		this.privilegesRequest = privilegesRequest;
	}
	

}
