package org.auth.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.auth.entity.UtilisateurEntity;
import org.auth.exception.BadRequestException;
import org.auth.request.UtilisateurRequest;
import org.auth.response.UtilisateurResponse;
import org.auth.service.UtilisateurService;
import org.auth.shared.dto.UtilisateurDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilisateurController {
    private static final Logger logger = LogManager.getLogger(UtilisateurController.class);
    public  String comp = "UtilisateurController";
	
	@Autowired
	private UtilisateurService utilisateurService;

    @PostMapping("/utilisateur/add")
	public ResponseEntity<UtilisateurResponse> ajouterUtilisateur(@RequestBody UtilisateurRequest utilisateur) {
        String methodeName = " ajouterUtilisateur ";
        logger.info(this.comp + " : -->> " + methodeName + " start .......... ");
        
    	if(validateFields(utilisateur)) {
	    	ModelMapper modelMapper = new ModelMapper();
	    	UtilisateurDto userDto = modelMapper.map(utilisateur, UtilisateurDto.class);
    		UtilisateurDto createdUser = utilisateurService.ajouterUtilisateur(userDto);

        	UtilisateurResponse userResponse = new UtilisateurResponse();
    		userResponse = modelMapper.map(createdUser, UtilisateurResponse.class);
            logger.info(this.comp +" : <<-- " + methodeName + " end. ");
    		return  new ResponseEntity<UtilisateurResponse>(userResponse, HttpStatus.CREATED);
    	}else {
    		throw new BadRequestException();
    	}
	}

    @PostMapping("/utilisateur/addList")
	public List<UtilisateurEntity> ajouterUtilisateurs(@RequestBody List<UtilisateurEntity> utilisateurs) {
		return null;//service.ajouterUtilisateurs(utilisateurs);
	}

    @GetMapping("/utilisateur/byId/{id}")
	public ResponseEntity<UtilisateurResponse> findUtilisateurById(@PathVariable Long id) {
    	UtilisateurDto userDto = utilisateurService.findUtilisateurById(id);
    	/*UtilisateurResponse userResponse = new UtilisateurResponse();
		BeanUtils.copyProperties(userDto, userResponse);*/
    	ModelMapper modelMapper = new ModelMapper();
    	UtilisateurResponse userResponse = modelMapper.map(userDto, UtilisateurResponse.class);
		return  new ResponseEntity<UtilisateurResponse>(userResponse, HttpStatus.OK);
	}

    @GetMapping("/utilisateur/findAll")
	public List<UtilisateurResponse> findAllUtilisateurs(@RequestParam(value="page", defaultValue = "1") int page, @RequestParam(value="limit", defaultValue = "15") int limit) {
    	logger.info("Call findAllUtilisateurs ...");
    	List<UtilisateurResponse> usersResponse = new ArrayList<>();
    	List<UtilisateurDto> usersDto = utilisateurService.findAllUtilisateurs(page,limit);
    	
    	for(UtilisateurDto temp : usersDto) {
    		/*UtilisateurResponse userResponse = new UtilisateurResponse();
    		BeanUtils.copyProperties(temp, userResponse);*/
        	ModelMapper modelMapper = new ModelMapper();
        	UtilisateurResponse userResponse = modelMapper.map(temp, UtilisateurResponse.class);
    		usersResponse.add(userResponse);
    	}
		return usersResponse;
	}

    @GetMapping("/utilisateur/byLogin/{login}")
	public ResponseEntity<UtilisateurResponse> findUtilisateurByLogin(String login) {
    	UtilisateurDto userDto = utilisateurService.findUtilisateurByLogin(login);
    	/*UtilisateurResponse userResponse = new UtilisateurResponse();
		BeanUtils.copyProperties(userDto, userResponse);*/
    	ModelMapper modelMapper = new ModelMapper();
    	UtilisateurResponse userResponse = modelMapper.map(userDto, UtilisateurResponse.class);
		return  new ResponseEntity<UtilisateurResponse>(userResponse, HttpStatus.OK);
	}

    @PutMapping("/utilisateur/update")
    public ResponseEntity<UtilisateurResponse> updateUtilisateur(@RequestBody UtilisateurRequest utilisateur) {
        String methodeName = " updateUtilisateur ";
        logger.info(this.comp + " : -->> " + methodeName + " start .......... ");
        
    	//UtilisateurResponse userResponse = new UtilisateurResponse();
        logger.info(this.comp +" : <<-- " + methodeName + " Id  " + utilisateur.getId());
        logger.info(this.comp +" : <<-- " + methodeName + " Id  " + utilisateur.getPrenom());
    	
    	if(validateFields(utilisateur)) {
        	/*UtilisateurDto userDto = new UtilisateurDto();
    		BeanUtils.copyProperties(utilisateur, userDto);*/
        	ModelMapper modelMapper = new ModelMapper();
        	UtilisateurDto userDto = modelMapper.map(utilisateur, UtilisateurDto.class);	
            logger.info(this.comp +" : <<-- " + methodeName + " Id  " + userDto.getId());
            logger.info(this.comp +" : <<-- " + methodeName + " Id  " + userDto.getPrenom());    	
    		UtilisateurDto createdUser = utilisateurService.updateUtilisateur(userDto);
    		//BeanUtils.copyProperties(createdUser, userResponse);
    		UtilisateurResponse userResponse = modelMapper.map(createdUser, UtilisateurResponse.class);
            logger.info(this.comp +" : <<-- " + methodeName + " end. ");
    		return  new ResponseEntity<UtilisateurResponse>(userResponse, HttpStatus.ACCEPTED);
    	}else {
            logger.info(this.comp +" : <<-- " + methodeName + " end. ");
    		throw new BadRequestException();
    	}

    }

    @PutMapping("/utilisateur/updatePassword")
    public ResponseEntity<UtilisateurResponse> updatePassword(@RequestBody UtilisateurRequest utilisateur) {
        String methodeName = " updateUtilisateur ";
        logger.info(this.comp + " : -->> " + methodeName + " start .......... ");
        
    	//UtilisateurResponse userResponse = new UtilisateurResponse();
    	
    	if(validateFields(utilisateur)) {
        	/*UtilisateurDto userDto = new UtilisateurDto();
    		BeanUtils.copyProperties(utilisateur, userDto);*/
        	ModelMapper modelMapper = new ModelMapper();
        	UtilisateurDto userDto = modelMapper.map(utilisateur, UtilisateurDto.class);	    	
    		UtilisateurDto createdUser = utilisateurService.updatePassword(userDto);
    		//BeanUtils.copyProperties(createdUser, userResponse);
    		UtilisateurResponse userResponse = modelMapper.map(createdUser, UtilisateurResponse.class);
            logger.info(this.comp +" : <<-- " + methodeName + " end. ");
    		return  new ResponseEntity<UtilisateurResponse>(userResponse, HttpStatus.ACCEPTED);
    	}else {
    		throw new BadRequestException();
    	}

    }

    @DeleteMapping("/utilisateur/{id}")
    public ResponseEntity<Object> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return  new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }


    /**************************************************************************
    **************** Method     : validateFields() --
    **************************************************************************/
       public boolean validateFields(UtilisateurRequest utilisateur) {
    	   logger.debug("validateFields() starts");
           boolean valid = true;
           //this.setError("");
           //expediteur
           if (utilisateur.getLogin() == null || utilisateur.getLogin().equals("")) {
               //this.setError(this.getError() + "*le champ Expéditeur est vide ");
               valid = false;
           }
           /*if (utilisateur.getPassword() == null || utilisateur.getPassword().equals("")) {
               //this.setError(this.getError() + "*le champ Expéditeur est vide ");
               valid = false;
           }*/
           
           return valid;

       } // validateFields()
}
