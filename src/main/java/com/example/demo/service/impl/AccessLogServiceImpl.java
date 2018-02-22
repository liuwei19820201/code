package com.example.demo.service.impl;

import com.example.demo.mybatis.mapper.AccessLogMapper;
import com.example.demo.mybatis.po.AccessLog;
import com.example.demo.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1.
 */
@Service
public class AccessLogServiceImpl implements AccessLogService {

    @Autowired
    private AccessLogMapper accessLogMapper;

    @Override
    public List<AccessLog> findAllList() {
        return accessLogMapper.findAllList();
    }

    @Override
    public AccessLog findById(Long id) {
        return accessLogMapper.findById(id);
    }


}
