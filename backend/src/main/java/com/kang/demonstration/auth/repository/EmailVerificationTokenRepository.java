package com.kang.demonstration.auth.repository;

import com.kang.demonstration.auth.entity.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kanghouchao
 */
@Repository
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {

    EmailVerificationToken findByToken(String token);

    EmailVerificationToken findByEmail(String email);

    Long deleteByEmail(String email);
}
