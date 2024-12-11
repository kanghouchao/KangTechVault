package com.kang.demonstration.auth.service.impl;

import com.kang.demonstration.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author kanghouchao
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = this.userRepository.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return user;
    }

}
