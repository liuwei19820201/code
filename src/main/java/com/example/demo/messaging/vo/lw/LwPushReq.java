package com.example.demo.messaging.vo.lw;

import com.example.demo.messaging.support.base.MQApiIdentifier;
import com.example.demo.messaging.vo.base.MQReqMessage;
import com.example.demo.messaging.vo.base.MQResMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lagon
 * @time 2017/10/19 11:41
 * @description 消息推送响应实体
 */
@Getter
@Setter
@ToString
public class LwPushReq extends MQReqMessage<LwPushReqBody> {
    public LwPushReq(){
        mqApiIdentifier= MQApiIdentifier.LW_MQ_TEST_INFORM;
        apiIdentifier= MQApiIdentifier.LW_MQ_TEST_INFORM.getMark();
    }
}
