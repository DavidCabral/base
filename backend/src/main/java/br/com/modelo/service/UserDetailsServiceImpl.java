package br.com.modelo.service;

import br.com.modelo.entity.User;
import br.com.modelo.repository.UserDAO;
import br.com.modelo.security.model.factory.UserFactory;
import br.com.modelo.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserFactory.create(getUser(username));
    }


    @Transactional(readOnly = true)
    public User getUser(String username) {
        User user;
        if (username.equalsIgnoreCase("admin")) {
            user = getAdmin();
        } else {
            user = userDAO.findByUsername(username);
        }
        if(user==null){
            throw new RuntimeException("Usuario ("+username+") não Localizado");
        }
        return user;
    }


    private User getAdmin() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("$2a$10$jbfBfx4zmbtPzgsB0QFudut6cHWUHwYH8spzDVGHldrdbXyKCSWOa");//admin criptgrafado
        user.setAuthorities("ADMIN,ROOT");
        return user;
    }


    @Transactional(readOnly = true)
    public List<User> getList() {
        return userDAO.findAll();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void setUser(User user){
        //se for novo usuário, verifica se o login já existe
        if(user.getId()==null || user.getId().equals(0)){
            if(userDAO.findByUsername(user.getUsername())!=null || user.getPassword().equalsIgnoreCase("admin")){
                throw new RuntimeException("Login já está em uso");
            }
            user.setPassword(Util.enconder(user.getPassword()));
        }
        user.setAuthorities("ADMIN,ROOT");//remove
        userDAO.save(user);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePassword(User user){
        user.setLastPasswordReset(new Date());
        user.setPassword(Util.enconder(user.getPassword()));
        userDAO.save(user);
    }





}
