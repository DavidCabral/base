package br.com.modelo.controller;

import br.com.modelo.entity.User;
import br.com.modelo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/register")
public class RegisterController {
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public RegisterController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody @Valid User user) {
        userDetailsService.setUser(user);
        return ResponseEntity.ok(user);
    }

}
