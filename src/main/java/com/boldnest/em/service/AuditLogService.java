package com.boldnest.em.service;

import com.boldnest.em.entity.AuditLog;

import java.util.List;

public interface AuditLogService {
    void logAction(String action, String performedBy);
    List<AuditLog> getAllLogs();
}
