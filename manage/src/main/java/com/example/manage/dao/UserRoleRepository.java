package com.example.manage.dao;

import com.example.manage.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author ONEC
 */
@RepositoryDefinition(domainClass = UserRole.class, idClass = Long.class)
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
