package com.example.demo.service;

import com.example.demo.common.CallStatus;
import com.example.demo.common.ProviderRealm;
import com.example.demo.mybatis.mapper.AccessLogMapper;
import com.example.demo.mybatis.po.AccessLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author lagon
 * @time 2017/10/25 17:33
 * @description 系统支撑组件
 */
@Slf4j
@Component
public class SystemServiceSupport {

    @Autowired
    private AccessLogMapper accessLogMapper;
    @Autowired
    private TaskExecutor taskExecutor;

    //持久化访问日志
    public void persistAccessLog(ProviderRealm provider, String address, String uri, String title, String bizId, String serialNo, CallStatus callStatus, String reqData, String resData, String exception){
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AccessLog accessLog=new AccessLog();
                accessLog.setProvider(provider.getMark());
                accessLog.setAddress(address);
                accessLog.setUri(uri);
                accessLog.setTitle(title);
                accessLog.setBizId(bizId);
                accessLog.setSerialNo(serialNo);
                accessLog.setCallStatus(callStatus.getCode());
                accessLog.setReqData(reqData);
                accessLog.setResData(resData);
                accessLog.setException(exception);
                accessLog.setRemark(title);
                accessLogMapper.saveAccessLog(accessLog);
                log.info("异步存储访问日志成功");
            }
        });
    }

    //变更访问日志
    public void updateAccessLog(ProviderRealm provider, String bizId, String serialNo, CallStatus callStatus, String resData, String exception){
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AccessLog accessLog=new AccessLog();
                accessLog.setProvider(provider.getMark());
                accessLog.setBizId(bizId);
                accessLog.setSerialNo(serialNo);
                accessLog.setCallStatus(callStatus.getCode());
                accessLog.setResData(resData);
                accessLog.setException(exception);
                accessLogMapper.updateAccessLog(accessLog);
                log.info("异步更新访问日志成功");
            }
        });
    }

}
