package com.example.demo.mybatis.mapper;


import com.example.demo.mybatis.po.MQErrorLog;

/**
 * @author lagon
 * @time 2017/6/27 18:02
 * @description MQ错误日志DAO
 */
public interface MQErrorLogMapper {

    /**
     * 保存MQ错误日志
     * @param mqErrorLogPO
     */
    void saveMQErrorLog(MQErrorLog mqErrorLogPO);

}
