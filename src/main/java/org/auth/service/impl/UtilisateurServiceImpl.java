package org.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.auth.entity.UtilisateurEntity;
import org.auth.repository.UtilisateurRepository;
import org.auth.service.UtilisateurService;
import org.auth.shared.Utils;
import org.auth.shared.dto.UtilisateurDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	private static final Logger logger = LogManager.getLogger(UtilisateurServiceImpl.class);
	public String comp = "UtilisateurServiceImpl";

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	Utils utils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UtilisateurDto ajouterUtilisateur(UtilisateurDto utilisateur) {
		String methodeName = " ajouterUtilisateur ";
		logger.info(this.comp + " : -->> " + methodeName + " start .......... ");

		UtilisateurEntity checkUser = utilisateurRepository.findByLogin(utilisateur.getLogin());
		if (checkUser != null)
			throw new RuntimeException("User already exist");

		ModelMapper modelMapper = new ModelMapper();
		UtilisateurEntity userEntity = modelMapper.map(utilisateur, UtilisateurEntity.class);

		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
		userEntity.setUtilisateurId(utils.generateStringId(32));
		UtilisateurEntity createdUser = utilisateurRepository.save(userEntity);

		UtilisateurDto userDto = modelMapper.map(createdUser, UtilisateurDto.class);

		logger.info(this.comp + " : <<-- " + methodeName + " end. ");
		return userDto;
	}

	@Override
	public List<UtilisateurDto> ajouterUtilisateurs(List<UtilisateurDto> utilisateurs) {
		String methodeName = " ajouterUtilisateurs ";
		logger.info(this.comp + " : -->> " + methodeName + " start .......... ");

		logger.info(this.comp + " : <<-- " + methodeName + " end. ");
		return null;
	}

	@Override
	public UtilisateurDto findUtilisateurById(Long id) {
		String methodeName = " findUtilisateurById ";
		logger.info(this.comp + " : -->> " + methodeName + " start .......... ");
		UtilisateurEntity userEntity = utilisateurRepository.findById(id).orElseThrow(null);

		ModelMapper modelMapper = new ModelMapper();
		UtilisateurDto userDto = modelMapper.map(userEntity, UtilisateurDto.class);

		logger.info(this.comp + " : <<-- " + methodeName + " end. ");
		return userDto;
	}

	@Override
	public List<UtilisateurDto> findAllUtilisateurs(int page, int limit) {

		if (page > 0)
			page = page - 1;
		List<UtilisateurDto> listUtilisateurs = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);

		Page<UtilisateurEntity> userPage = utilisateurRepository.findAll(pageableRequest);
		List<UtilisateurEntity> utilisateurs = userPage.getContent();
		ModelMapper modelMapper = new ModelMapper();
		if (utilisateurs != null && utilisateurs.size() != 0) {
			for (UtilisateurEntity temp : utilisateurs) {
				UtilisateurDto userDto = new UtilisateurDto();
				userDto = modelMapper.map(temp, UtilisateurDto.class);
				listUtilisateurs.add(userDto);
			}
		}
		return listUtilisateurs;
	}

	@Override
	public UtilisateurDto findUtilisateurByLogin(String login) {
		String methodeName = " findUtilisateurByLogin ";
		logger.info(this.comp + " : -->> " + methodeName + " start .......... ");
		UtilisateurEntity userEntity = utilisateurRepository.findByLogin(login);
		if (userEntity == null)
			throw new UsernameNotFoundException(login);
		ModelMapper modelMapper = new ModelMapper();
		UtilisateurDto userDto = modelMapper.map(userEntity, UtilisateurDto.class);

		logger.info(this.comp + " : <<-- " + methodeName + " endddddddd. ");
		return userDto;
	}

	@Override
	public void deleteUtilisateur(Long id) {
		utilisateurRepository.deleteById(id);
	}

	@Override
	public UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDto) {

		ModelMapper modelMapper = new ModelMapper();

		UtilisateurEntity existingUtilisateur = utilisateurRepository.findById(utilisateurDto.getId()).orElse(null);
		logger.info(this.comp + " : <<--  utilisateurDto  " + utilisateurDto.getId());
		logger.info(this.comp + " : <<--  existingUtilisateur  " + existingUtilisateur.getId());
		existingUtilisateur.setNom(utilisateurDto.getNom());
		existingUtilisateur.setPrenom(utilisateurDto.getPrenom());
		existingUtilisateur.setLogin(utilisateurDto.getLogin());
		// existingUtilisateur.setEncryptedPassword(bCryptPasswordEncoder.encode(utilisateurDto.getPassword()));
		existingUtilisateur.setEmail(utilisateurDto.getEmail());
		/*
		 * existingUtilisateur.setTel(utilisateurDto.getTel()); ProfilEntity
		 * profilEntity = modelMapper.map(utilisateurDto.getProfil(),
		 * ProfilEntity.class); existingUtilisateur.setProfil(profilEntity);
		 */
		UtilisateurEntity updatedUtilisateur = utilisateurRepository.save(existingUtilisateur);

		UtilisateurDto userDto = modelMapper.map(updatedUtilisateur, UtilisateurDto.class);
		return userDto;
	}

	@Override
	public UtilisateurDto updatePassword(UtilisateurDto utilisateurDto) {

		ModelMapper modelMapper = new ModelMapper();

		UtilisateurEntity existingUtilisateur = utilisateurRepository.findById(utilisateurDto.getId()).orElse(null);
		existingUtilisateur.setEncryptedPassword(bCryptPasswordEncoder.encode(utilisateurDto.getPassword()));
		UtilisateurEntity updatedUtilisateur = utilisateurRepository.save(existingUtilisateur);

		UtilisateurDto userDto = modelMapper.map(updatedUtilisateur, UtilisateurDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UtilisateurEntity utilisateurEntity = utilisateurRepository.findByLogin(login);
		if (utilisateurEntity == null)
			throw new UsernameNotFoundException(login);
		return new User(utilisateurEntity.getLogin(), utilisateurEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UtilisateurDto findUtilisateurByUtilisateurId(String utilisateurId) {
		String methodeName = " findUtilisateurByLogin ";
		logger.info(this.comp + " : -->> " + methodeName + " start .......... ");
		UtilisateurEntity userEntity = utilisateurRepository.findByUtilisateurId(utilisateurId);
		if (userEntity == null)
			throw new UsernameNotFoundException(utilisateurId);
		UtilisateurDto userDto = new UtilisateurDto();
		BeanUtils.copyProperties(userEntity, userDto);

		logger.info(this.comp + " : <<-- " + methodeName + " end. ");
		return userDto;
	}

}
