package com.example.task_management.controllers;

import com.example.task_management.models.AuditLog;
import com.example.task_management.services.AuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    private final AuditService auditService;

    public AuditLogController(AuditService auditService) {
        this.auditService = auditService;
    }

    // Create a new audit log
    @PostMapping
    public ResponseEntity<AuditLog> createAuditLog(@RequestBody AuditLog auditLog) {
        AuditLog createdLog = auditService.createAuditLog(auditLog);
        return new ResponseEntity<>(createdLog, HttpStatus.CREATED);
    }

    // Get all audit logs
    @GetMapping
    public List<AuditLog> getAllAuditLogs() {
        return auditService.getAllAuditLogs();
    }

    // Get audit logs by entity type
    @GetMapping("/entity-type/{entityType}")
    public List<AuditLog> getAuditLogsByEntityType(@PathVariable String entityType) {
        return auditService.getAuditLogsByEntityType(entityType);
    }

    // Get audit logs by entity ID
    @GetMapping("/entity-id/{entityId}")
    public List<AuditLog> getAuditLogsByEntityId(@PathVariable Long entityId) {
        return auditService.getAuditLogsByEntityId(entityId);
    }

    // Get audit logs created after a specific date
    @GetMapping("/created-after/{createdAt}")
    public List<AuditLog> getAuditLogsCreatedAfter(@PathVariable String createdAt) {
        LocalDateTime date = LocalDateTime.parse(createdAt);
        return auditService.getAuditLogsCreatedAfter(date);
    }
}
