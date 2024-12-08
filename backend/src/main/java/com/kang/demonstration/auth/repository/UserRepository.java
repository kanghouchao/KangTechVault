package com.kang.demonstration.auth.repository;

import com.kang.demonstration.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kanghouchao
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByEmail(String email);

    boolean existsByEmail(String email);
}
