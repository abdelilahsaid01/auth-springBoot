package org.auth.service;

import java.util.List;

import org.auth.entity.ProfilEntity;
import org.auth.repository.ProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfilService {
	
	@Autowired
	private ProfilRepository repository;
	
	public ProfilEntity ajouterProfil(ProfilEntity profil) {
		return repository.save(profil);
	}
	
	public List<ProfilEntity> ajouterProfils(List<ProfilEntity>  profils) {
		return repository.saveAll(profils);
	}
	
	public ProfilEntity findProfilById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<ProfilEntity> findAllProfils() {
		return repository.findAll();
	}

    public String deleteProfil(Long id) {
        repository.deleteById(id);
        return "Profil removed !! " + id;
    }

    public ProfilEntity updateProfil(ProfilEntity profil) {
    	ProfilEntity existingProfil = repository.findById(profil.getId()).orElse(null);
    	existingProfil.setName(profil.getName());
    	existingProfil.setPrivileges(profil.getPrivileges());
        return repository.save(existingProfil);
    }


}
