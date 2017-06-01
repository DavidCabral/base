package br.com.modelo.security.model.factory;

import br.com.modelo.entity.User;
import br.com.modelo.security.model.security.SecUser;
import org.springframework.security.core.authority.AuthorityUtils;

public class UserFactory {

  public static SecUser create(User user) {
    return new SecUser(
      user.getId(),
      user.getUsername(),
      user.getPassword(),
      user.getEmail(),
      user.getLastPasswordReset(),
      AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
    );
    
  }

}
