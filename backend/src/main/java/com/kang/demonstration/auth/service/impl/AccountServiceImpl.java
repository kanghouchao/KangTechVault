package com.kang.demonstration.auth.service.impl;

import com.kang.demonstration.auth.entity.User;
import com.kang.demonstration.auth.repository.UserRepository;
import com.kang.demonstration.auth.service.AccountService;
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
public class AccountServiceImpl implements UserDetailsService, AccountService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = this.userRepository.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return user;
    }

    @Override
    public Long save(User user) {
        return this.userRepository.save(user).getId();
    }
}
