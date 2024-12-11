package com.kang.demonstration.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author kanghouchao
 */
@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private boolean isActive = true;

    @Column(nullable = false)
    private boolean isAccountNonExpired = true;

    @Column(nullable = false)
    private boolean isAccountNonLocked = true;

    @Column(nullable = false)
    private boolean isCredentialsNonExpired = true;

    @Column(nullable = false)
    private boolean isEnabled = true;

    @Column(nullable = false)
    private boolean isEmailVerified = false;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //TODO should return real authorities
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
