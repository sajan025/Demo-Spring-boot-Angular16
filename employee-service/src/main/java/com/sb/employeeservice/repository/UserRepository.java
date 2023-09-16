package com.sb.employeeservice.repository;

import com.sb.employeeservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUserName(String username);

    User findByUserName(String username);

    @Transactional
    void deleteByUserName(String username);
}
