package com.example.payment.dao.primary;

import com.example.payment.entity.primary.BusinessRoute;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author ONEC
 */
@RepositoryDefinition(domainClass = BusinessRoute.class,idClass = Long.class)
public interface BusinessRouteRepository extends BaseRepository<BusinessRoute, Long> {

}
