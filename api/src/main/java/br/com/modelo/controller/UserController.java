package br.com.modelo.controller;

import br.com.modelo.entity.User;
import br.com.modelo.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> getList() {
        return ResponseEntity.ok(userDetailsService.getList());
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid User user) {
        userDetailsService.setUser(user);
        return ResponseEntity.ok(user);
    }


    @RequestMapping(value = "update/password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePass(@RequestBody @Valid User user) {
        userDetailsService.updatePassword(user);
        return ResponseEntity.ok(user);
    }


    @RequestMapping(value = "{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(userDetailsService.getUser(username));
    }




}
