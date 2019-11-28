package com.example.index.dao;

import com.example.index.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

/**
 * @author ONEC
 */
@RepositoryDefinition(domainClass = UserInfo.class, idClass = Long.class)
public interface UserRepository extends JpaRepository<UserInfo, Long> {


    /**
     * 符合JPA命名规则的方法定义
     *
     * @param name
     * @return
     */
    UserInfo findByUserName(String name);

    /**
     * 手机查询
     * @param phone
     * @return
     */
    UserInfo findByPhone(String phone);

    /**
     * 邮箱查询
     * @param email
     * @return
     */
    UserInfo findByEmail(String email);

//    @Query(value = "from User where position =?1")
//    List<User> selectByCustomSqlTest1(String position);
//
//    @Query(value = "select * from wm_user where position = ?1", nativeQuery = true)
//    List<User> selectByCustomSqlTest2(String position);
//
//    Page<User> findByNickName(String nickName, Pageable pageable);
}
