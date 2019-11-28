package com.example.index.dao;

import com.example.index.entity.PermissionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author ONEC
 */
@RepositoryDefinition(domainClass = PermissionInfo.class, idClass = Long.class)
public interface PermissionRepository extends JpaRepository<PermissionInfo, Long> {

}
