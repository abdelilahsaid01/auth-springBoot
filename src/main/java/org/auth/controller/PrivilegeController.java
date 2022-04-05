package org.auth.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.auth.entity.PrivilegeEntity;
import org.auth.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivilegeController {
    private static final Logger logger = LogManager.getLogger(PrivilegeController.class);
    
    @Autowired
    private PrivilegeService service;

    @PostMapping("/privilege/add")
	public PrivilegeEntity ajouterPrivilege(@RequestBody PrivilegeEntity privilege) {
		return service.ajouterPrivilege(privilege);
	}

    @PostMapping("/privilege/addList")
	public List<PrivilegeEntity> ajouterPrivileges(@RequestBody List<PrivilegeEntity> privileges) {
		return service.ajouterPrivileges(privileges);
	}

    @GetMapping("/privilege/byId/{id}")
	public PrivilegeEntity findPrivilegeById(@PathVariable Long id) {
		return service.findPrivilegeById(id);
	}

    @GetMapping("/privilege/findAll")
	public List<PrivilegeEntity> findAllPrivileges() {
    	logger.info("Call findAllPrivileges ...");
		return service.findAllPrivileges();
	}

    @PutMapping("/privilege/update")
    public PrivilegeEntity updatePrivilege(@RequestBody PrivilegeEntity privilege) {
        return service.updatePrivilege(privilege);
    }

    @DeleteMapping("/privilege/delete/{id}")
    public String deletePrivilege(@PathVariable Long id) {
        return service.deletePrivilege(id);
    }


}
