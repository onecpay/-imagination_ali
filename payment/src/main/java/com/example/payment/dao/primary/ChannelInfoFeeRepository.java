package com.example.payment.dao.primary;

import com.example.payment.entity.primary.ChannelInfoFee;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author ONEC
 */
@RepositoryDefinition(domainClass = ChannelInfoFee.class, idClass = Long.class)
public interface ChannelInfoFeeRepository extends BaseRepository<ChannelInfoFee, Long> {

}
