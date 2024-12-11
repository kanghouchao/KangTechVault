package com.kang.demonstration.auth.repository;

import com.kang.demonstration.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kanghouchao
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Get user by email
     *
     * @param email email
     * @return user entity
     */
    User getUserByEmail(String email);

    /**
     * if user exists by email
     *
     * @param email email
     * @return bool
     */
    boolean existsByEmail(String email);
}
