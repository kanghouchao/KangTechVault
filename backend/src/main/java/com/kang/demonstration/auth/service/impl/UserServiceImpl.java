package com.kang.demonstration.auth.service.impl;

import com.kang.demonstration.auth.repository.UserRepository;
import com.kang.demonstration.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author kanghouchao
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }
}
