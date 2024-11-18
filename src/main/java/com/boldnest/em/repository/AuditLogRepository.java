package com.boldnest.em.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boldnest.em.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
