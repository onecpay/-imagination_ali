package com.example.payment.dao.primary;

import com.example.payment.entity.primary.ChannelInfo;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author ONEC
 */
@RepositoryDefinition(domainClass = ChannelInfo.class, idClass = Long.class)
public interface ChannelInfoRepository extends BaseRepository<ChannelInfo, Long> {

}
