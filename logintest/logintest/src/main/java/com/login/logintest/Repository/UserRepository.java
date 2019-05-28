package com.login.logintest.Repository;

import com.login.logintest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByNameAndPassword(String name,String password);
    Optional<User> findByName(String name);

    //更新查询
    @Modifying
    @Transactional
    @Query(value="update user set activeState = true where user_name = ?1",nativeQuery = true)
    int active(String name);


}
