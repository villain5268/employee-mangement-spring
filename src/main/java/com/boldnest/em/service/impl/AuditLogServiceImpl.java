package com.boldnest.em.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boldnest.em.entity.AuditLog;
import com.boldnest.em.repository.AuditLogRepository;
import com.boldnest.em.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {

	@Autowired
	private AuditLogRepository auditLogRepository;

	@Override
	public void logAction(String action, String performedBy) {
		AuditLog log = new AuditLog(null, action, performedBy, LocalDateTime.now());
		auditLogRepository.save(log);
	}

	@Override
	public List<AuditLog> getAllLogs() {
		return auditLogRepository.findAll();
	}
}
