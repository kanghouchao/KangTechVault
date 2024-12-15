package com.kang.demonstration.auth.service;

import com.kang.demonstration.auth.entity.User;

/**
 * @author kanghouchao
 */
public interface UserService {

    /**
     *
     * @param email
     * @return
     */
    boolean existsByEmail(String email);

    User createNewUser(String email, String password);
}
