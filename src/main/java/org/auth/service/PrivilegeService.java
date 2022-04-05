package org.auth.service;

import java.util.List;

import org.auth.entity.PrivilegeEntity;
import org.auth.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService {
	
	@Autowired
	private PrivilegeRepository repository;
	
	public PrivilegeEntity ajouterPrivilege(PrivilegeEntity privilege) {
		return repository.save(privilege);
	}
	
	public List<PrivilegeEntity> ajouterPrivileges(List<PrivilegeEntity>  privileges) {
		return repository.saveAll(privileges);
	}
	
	public PrivilegeEntity findPrivilegeById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<PrivilegeEntity> findAllPrivileges() {
		return repository.findAll();
	}

    public String deletePrivilege(Long id) {
        repository.deleteById(id);
        return "Privilege removed !! " + id;
    }

    public PrivilegeEntity updatePrivilege(PrivilegeEntity privilege) {
    	PrivilegeEntity existingPrivilege = repository.findById(privilege.getId()).orElse(null);
    	existingPrivilege.setName(privilege.getName());
        return repository.save(existingPrivilege);
    }

}
