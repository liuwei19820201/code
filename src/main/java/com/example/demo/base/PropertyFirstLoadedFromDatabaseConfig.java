package com.example.demo.base;

import com.example.demo.jpa.dao.SysPropsConfDao;
import com.example.demo.jpa.entity.SysPropsConfPO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

@Slf4j
@Component("PropertyFirstLoadedFromDatabaseConfig") //把这个类交给Spring管理，重新起个名字叫 PropertyFirstLoadedFromDatabaseConfig
class PropertyFirstLoadedFromDatabaseConfig {

    @Autowired
    private ConfigurableEnvironment env; //暴露来自Spring　ConfigurableEnvironment的属性

    @Autowired
    private SysPropsConfDao sysPropsConfDao;

    @PostConstruct
    public void initializeDatabasePropertySourceUsage() {
        MutablePropertySources propertySources = env.getPropertySources();
        try {
            //数据库加载可用属性集
            List<SysPropsConfPO> sysPropsConfPOs = sysPropsConfDao.findAll();
            Properties properties = new Properties();
            String curEnv=env.getProperty("spring.profiles.active");
            log.info("curEnv======="+ curEnv);
            for(SysPropsConfPO sysPropsConfPO : sysPropsConfPOs){
                if("dev".equals(curEnv)){
                    properties.put(sysPropsConfPO.getEnvKey(), StringUtils.trimToEmpty(sysPropsConfPO.getDevEnvValue()));
                }else if("test".equals(curEnv)){
                    properties.put(sysPropsConfPO.getEnvKey(), StringUtils.trimToEmpty(sysPropsConfPO.getTestEnvValue()));
                }else if("uat".equals(curEnv)){
                    properties.put(sysPropsConfPO.getEnvKey(), StringUtils.trimToEmpty(sysPropsConfPO.getUatEnvValue()));
                }else if("prod".equals(curEnv)){
                    properties.put(sysPropsConfPO.getEnvKey(), StringUtils.trimToEmpty(sysPropsConfPO.getProdEnvValue()));
                }else{
                    throw new RuntimeException("spring.profiles.active is invalid variable");
                }
            }
            PropertiesPropertySource dbPropertySource = new PropertiesPropertySource("dbPropertySource", properties);
            propertySources.addFirst(dbPropertySource);
            log.info("succeed in loading properties from database");
        } catch (Exception e) {
            throw new RuntimeException("error during database properties loaded",e);
        }
    }
}
