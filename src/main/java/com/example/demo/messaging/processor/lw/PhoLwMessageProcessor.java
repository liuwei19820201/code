package com.example.demo.messaging.processor.lw;

import com.example.demo.messaging.support.base.MQConstants;
import com.example.demo.messaging.support.base.ResDetail;
import com.example.demo.messaging.support.lw.LwConsumerCondition;
import com.example.demo.messaging.support.lw.LwMessageProcessor;
import com.example.demo.messaging.vo.lw.RepaymentStatusInformReq;
import com.example.demo.service.UserService;
import com.example.demo.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@Conditional(LwConsumerCondition.class)
public class PhoLwMessageProcessor implements LwMessageProcessor<RepaymentStatusInformReq> {
    @Value("${rabbitmq.lw.trans.res.routingkey.name}")
    private String routingKey;

    @Autowired
    private UserService userService;

    @Override
    public String getDataTransferFormat() {
        return MQConstants.DATA_TRANSFER_FORMAT_JSON;
    }

    @Override
    public Class<RepaymentStatusInformReq> getTClass() {
        return RepaymentStatusInformReq.class;
    }

    @Override
    public Set<String> getProcessedRoutingKeys() {
        return new HashSet<String>() {
            {
                add(routingKey);
            }
        };
    }

    @Override
    public ResDetail process(MessageProperties messageProperties, RepaymentStatusInformReq message) {
        log.info("开始处理还款状态通知MQ消息，时间：{}", DateUtil.getStandardCurrentTime());
        log.info("message:"+message);
        log.info("messageProperties:"+messageProperties);
//        userService.processMessage(message);
        log.info("完成处理还款状态通知MQ消息，时间：{}", DateUtil.getStandardCurrentTime());
        return new ResDetail(true, "success");
    }
}
