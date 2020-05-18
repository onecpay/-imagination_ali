package com.example.payment.dao.primary;

import com.example.payment.entity.primary.Business;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author ONEC
 */
@RepositoryDefinition(domainClass = Business.class,idClass = Long.class)
public interface BusinessRepository extends BaseRepository<Business, Long> {

}
