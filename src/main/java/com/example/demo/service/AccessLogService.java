package com.example.demo.service;

import com.example.demo.mybatis.po.AccessLog;

import java.util.List;

public interface AccessLogService {

    List<AccessLog> findAllList();

    AccessLog findById(Long id);
}
