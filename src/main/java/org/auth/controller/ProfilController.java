package org.auth.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.auth.entity.ProfilEntity;
import org.auth.service.ProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfilController {
    private static final Logger logger = LogManager.getLogger(ProfilController.class);
    
    @Autowired
    private ProfilService service;

    @PostMapping("/profil/add")
	public ProfilEntity ajouterProfil(@RequestBody ProfilEntity profil) {
		return service.ajouterProfil(profil);
	}

    @PostMapping("/profil/addList")
	public List<ProfilEntity> ajouterProfils(@RequestBody List<ProfilEntity> profils) {
		return service.ajouterProfils(profils);
	}

    @GetMapping("/profil/byId/{id}")
	public ProfilEntity findProfilById(@PathVariable Long id) {
		return service.findProfilById(id);
	}

    @GetMapping("/profil/findAll")
	public List<ProfilEntity> findAllProfils() {
    	logger.info("Call findAllProfils ...");
		return service.findAllProfils();
	}

    @PutMapping("/profil/update")
    public ProfilEntity updateProfil(@RequestBody ProfilEntity profil) {
        return service.updateProfil(profil);
    }

    @DeleteMapping("/profil/delete/{id}")
    public String deleteProfil(@PathVariable Long id) {
        return service.deleteProfil(id);
    }



}
