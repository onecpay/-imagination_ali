package com.example.payment.dao.primary;

import com.example.payment.entity.primary.ChannelMerchant;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author ONEC
 */
@RepositoryDefinition(domainClass = ChannelMerchant.class,idClass = Long.class)
public interface ChannelMerchantRepository extends BaseRepository<ChannelMerchant, Long> {

}
