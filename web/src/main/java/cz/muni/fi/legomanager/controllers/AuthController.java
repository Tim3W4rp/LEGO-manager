package cz.muni.fi.legomanager.controllers;

import cz.fi.muni.legomanager.dto.TokenDTO;
import cz.fi.muni.legomanager.dto.UserCredentialsDTO;
import cz.muni.fi.legomanager.security.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Štěpán Granát
 */
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000") // for development mode
public class AuthController {
    
    final static Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @RequestMapping(method = RequestMethod.POST)
    public final TokenDTO authenticate(@RequestBody UserCredentialsDTO credentials) {
        logger.debug("rest authenticate() username <{}>", credentials.getUsername());
        /*
        UserDetailDTO user = this.userFacade.findByCredentials(credentials);
        
        if (user == null) {
            throw new UnprocessableEntityException();
        }
        */
        
        String token = AuthorizationService.getTokenForUser();
        return new TokenDTO(token);
    }
}
