package com.example.demo.jpa.dao;

import com.example.demo.jpa.entity.SysPropsConfPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 刘巍 on 2017/12/29.
 */
@Repository
public interface SysPropsConfDao extends JpaRepository<SysPropsConfPO,Long> {

}
