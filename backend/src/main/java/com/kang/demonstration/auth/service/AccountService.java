package com.kang.demonstration.auth.service;

import com.kang.demonstration.auth.entity.User;

/**
 * @author kanghouchao
 */
public interface AccountService {

    /**
     * save user
     *
     * @param user user
     * @return userId
     */
    Long save(User user);
}
