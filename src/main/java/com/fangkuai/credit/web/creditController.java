package com.fangkuai.credit.web;


import com.fangkuai.credit.entity.User;
import com.fangkuai.credit.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.util.concurrent.TimeUnit;

/**
 * @创建人 lin
 * @创建时间 2020/4/7
 * @描述
 */
@RestController
@RequestMapping("/user")
public class creditController {

    @Value("${queueName}")
    private String queue;




    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JmsTemplate jmsTemplate;


   //发送短信验证码的思路，因为这里是一个简易的短信登陆实战demo，我们就不细分mvc层了，大家结合具体需要进行改进。
    @GetMapping("/sendmsg")
    public ResponseData index(@RequestParam(value = "telephone")  String telephone){

        System.out.println("电话号码"+telephone);

        try{
            //这里生成的验证码我们用最简便的方式，用随机函数生成
            final String code = (long)(Math.random()*1000000) + "";
            System.out.println("生成的验证码为："+code);

        //redisTemplat是springboot提供操作redis的接口，我们设置一个键为telephone,值为code，然后设置器过期时间为60秒
        redisTemplate.opsForValue().set(telephone,code,60,TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get(telephone)+"redis");

        jmsTemplate.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                MapMessage message = session.createMapMessage();
                message.setString("telephone",telephone);
                message.setString("code",code);
                return message;
            }
        });

    }catch (Exception e){
        return    ResponseData.error(400,"请求失效，请稍后重试");
        }


        return ResponseData.success("成功发送");
    }


    @PostMapping(value = "/login")
    public ResponseData login(@RequestBody User user){

            System.out.println("号码不为空");
            if(user.getCode().equals(redisTemplate.opsForValue().get(user.getPhone()))){
                return ResponseData.success("登陆成功");

        }
        return ResponseData.error(403,"登陆失败");

    }


}
