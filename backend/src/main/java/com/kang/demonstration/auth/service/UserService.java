package com.kang.demonstration.auth.service;

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
}
