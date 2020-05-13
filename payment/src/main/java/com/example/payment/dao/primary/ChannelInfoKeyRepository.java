package com.example.payment.dao.primary;

import com.example.payment.entity.primary.ChannelInfo;
import com.example.payment.entity.primary.ChannelInfoKey;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author ONEC
 */
@RepositoryDefinition(domainClass = ChannelInfoKey.class,idClass = Long.class)
public interface ChannelInfoKeyRepository extends BaseRepository<ChannelInfoKey, Long> {

}
