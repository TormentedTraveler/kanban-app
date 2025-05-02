package com.esen.java_kanban_rework.repository;

import com.esen.java_kanban_rework.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long> {
    OTP findByEmail(String email);
}
