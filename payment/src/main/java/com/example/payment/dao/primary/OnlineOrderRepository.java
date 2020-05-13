package com.example.payment.dao.primary;

import com.example.payment.entity.primary.OnlineOrder;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author ONEC
 */
@RepositoryDefinition(domainClass = OnlineOrder.class, idClass = Long.class)
public interface OnlineOrderRepository extends BaseRepository<OnlineOrder, Long> {

}
