package br.com.modelo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "usuario")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O Nome do Usuário é Obrigatório")
    @Size(max = 12, message = "O tamanho do nome deve estar entre 1 e 12")
    private String username;
    @NotBlank(message = "A Senha do Usuário é Obrigatória")
    @Size(message = "O tamanho do nome deve estar entre 1 e 12")
    private String password;
    @Email
    @Size(max = 120, message = "O email deve conter no máximo 120 caracteres")
    private String email;
    private Date lastPasswordReset;
    private String authorities;


    public User() {
        super();
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return this.username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return this.email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public Date getLastPasswordReset() {
        return this.lastPasswordReset;
    }


    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }


    public String getAuthorities() {
        return this.authorities;
    }


    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id != null ? id.equals(user.id) : user.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "";
    }
}
