package br.com.modelo.service;

import br.com.modelo.entidades.Usuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario user = new Usuario();
    user.setUsername(username);
    user.setPassword("$2a$10$vrf004mzKShvRDAQe6/mV.cPJvE4bneJr2JHq63NuIa3tRrVEvUVK");
    user.setRules("ADMIN,ROOT");

    return user;
  }

  
}
