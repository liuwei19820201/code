package com.example.demo.config.rabbit;


import com.example.demo.messaging.support.lw.LwConfirmCallBackListener;
import com.example.demo.messaging.support.lw.LwProducerCondition;
import com.example.demo.messaging.support.lw.LwReturnCallBackListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author lagon
 * @time 2017/10/18 15:47
 * @description 普惠运营MQ生产者配置类
 */
@Configuration
@Conditional(LwProducerCondition.class)
public class RabbitLwProducerConfig extends RabbitLwConfig {

    @Bean("lwRabbitTemplate")
    public RabbitTemplate lwRabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(lwRabbitConnectionFactory());
        rabbitTemplate.setConfirmCallback(lwConfirmCallBackListener());
        rabbitTemplate.setReturnCallback(lwReturnCallBackListener());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    @Bean
    public LwConfirmCallBackListener lwConfirmCallBackListener(){
        return new LwConfirmCallBackListener();
    }

    @Bean
    public LwReturnCallBackListener lwReturnCallBackListener(){
        return new LwReturnCallBackListener();
    }

}
