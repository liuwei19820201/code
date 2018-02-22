package com.example.demo.jpa.dao;

import com.example.demo.jpa.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 刘巍 on 2017/7/2.
 */
@Repository
public interface JpaUserRepository extends JpaRepository<JpaUser, Long> {
}
