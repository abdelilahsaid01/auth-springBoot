package org.auth.service;

import java.util.List;

import org.auth.shared.dto.UtilisateurDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UtilisateurService extends UserDetailsService  {
	
	UtilisateurDto ajouterUtilisateur(UtilisateurDto utilisateurDto) ;
	
	List<UtilisateurDto> ajouterUtilisateurs(List<UtilisateurDto>  utilisateursDto);
	
	UtilisateurDto findUtilisateurById(Long id);
	
	UtilisateurDto findUtilisateurByUtilisateurId(String utilisateurId);
	
	List<UtilisateurDto> findAllUtilisateurs(int page, int limit);
	
	UtilisateurDto findUtilisateurByLogin(String login);
	
	void deleteUtilisateur(Long id);
	
	UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDto);
	
	UtilisateurDto updatePassword(UtilisateurDto utilisateurDto);

}
