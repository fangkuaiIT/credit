package com.fangkuai.credit;


import com.alibaba.fastjson.JSON;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.print.attribute.standard.Destination;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class CreditApplicationTests {




    @Test
    void contextLoads() {
    }

}
