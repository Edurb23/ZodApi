package com.br.zod.model;

import com.br.zod.dto.loginDto.AtualizarEmail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_JV_ZOD_LOGIN")
@SequenceGenerator(name = "seq_login", sequenceName = "SEQ_TB_JV_ZOD_LGOIN", allocationSize = 1)
public class Login implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_login")
    @Column(name = "ID_LOGIN", length = 36)
    private Long id;

    @Column(name = "DS_EMAIL",nullable = false, unique = true)
    private String email;

    @Column(name = "CD_SENHA",nullable = false)
    private String senha;

    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
    private Usuario usuario;


    public Login(String email, String encode) {
        this.email = email;
        this.senha = encode;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void atualizarEmail(AtualizarEmail dto) {
        if(dto.email() != null)
            email = dto.email();
    }
}
