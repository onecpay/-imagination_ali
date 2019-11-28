package com.example.payment.dao.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * primary 公共请求dao
 * @author ONEC
 */
@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID> {

    /**
     * 通过id 查询实体返回optional 容器
     * @param id
     * @return
     */
    @Override
    Optional<T> findById(ID id);

    /**
     *  保存给对象并且返回
     * @param entity
     * @param <S>
     * @return
     */
    @Override
    <S extends  T> S save(S entity);
}
