package org.auth.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "profil")
public class ProfilEntity implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	private String profilId;
	private String name;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE})
    @JoinTable(name = "profil_privilege", joinColumns = @JoinColumn(name = "idprofil"), inverseJoinColumns = @JoinColumn(name = "idprivilege"))
    private List<PrivilegeEntity> privileges = new ArrayList<PrivilegeEntity>();
 
	   
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

	public List<PrivilegeEntity> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<PrivilegeEntity> privileges) {
		this.privileges = privileges;
	}
	public String getProfilId() {
		return profilId;
	}
	public void setProfilId(String profilId) {
		this.profilId = profilId;
	}


}
