package com.fangkuai.credit.util;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @创建人 lin
 * @创建时间 2020/4/7
 * @描述
 */
@Component
public class SmsListenter {

    @Autowired
   private SMSUtil smsUtil;

    @JmsListener(destination="${queueName}")
    public void sendSms(Map<String,String> map){

     try {

         //发短信
         SendSmsResponse response = smsUtil.sendSms(
                 map.get("telephone"),
                 map.get("code")
               );
         System.out.println("短信接口返回的数据----------------");
         System.out.println("Code=" + response.getCode());
         System.out.println("Message=" + response.getMessage());
         System.out.println("RequestId=" + response.getRequestId());
         System.out.println("BizId=" + response.getBizId());


     }catch (ClientException e){
         e.printStackTrace();
     }

    }
}
