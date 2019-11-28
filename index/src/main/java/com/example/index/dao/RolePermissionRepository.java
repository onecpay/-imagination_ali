package com.example.index.dao;

import com.example.index.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author ONEC
 */
@RepositoryDefinition(domainClass = RolePermission.class, idClass = Long.class)
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

}
