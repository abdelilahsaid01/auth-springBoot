package org.auth.repository;

import org.auth.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UtilisateurRepository extends PagingAndSortingRepository<UtilisateurEntity,Long> {
	
	public UtilisateurEntity findByLogin(String login);
	public UtilisateurEntity findByUtilisateurId(String utilisateurId);
	@Query("select u from UtilisateurEntity u where u.login = :login and u.encryptedPassword = :password ")
	public UtilisateurEntity findUtilisateurByLoginAndPassword(@Param("login")String login,@Param("password")String password);

}
