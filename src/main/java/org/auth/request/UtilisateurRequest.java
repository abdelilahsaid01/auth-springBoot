package org.auth.request;

import java.io.Serializable;


public class UtilisateurRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9042638075886458029L;

	private Long id;
	private String utilisateurId;
	private String nom;
    private String prenom;
    private String login;
	private String email;
	private String password;
    private ProfilRequest profil;
    private String datecreation;
    private String tel;
	private String encryptedPassword;
	private String emailverificationToken;
	private Boolean emailverificationStatus = false;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUtilisateurId() {
		return utilisateurId;
	}
	public void setUtilisateurId(String utilisateurId) {
		this.utilisateurId = utilisateurId;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ProfilRequest getProfil() {
		return profil;
	}
	public void setProfil(ProfilRequest profil) {
		this.profil = profil;
	}
	public String getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(String datecreation) {
		this.datecreation = datecreation;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmailverificationToken() {
		return emailverificationToken;
	}
	public void setEmailverificationToken(String emailverificationToken) {
		this.emailverificationToken = emailverificationToken;
	}
	public Boolean getEmailverificationStatus() {
		return emailverificationStatus;
	}
	public void setEmailverificationStatus(Boolean emailverificationStatus) {
		this.emailverificationStatus = emailverificationStatus;
	}
	
}
