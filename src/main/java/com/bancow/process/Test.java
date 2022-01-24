package com.bancow.process;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Test {
    public void sendSms() {

        String api_key = "NCS2AFPRRW6UB4PV";
        String api_secret = "KO7AWJFZ1JLRBH5SENVBWRR05BX8AB3R";
        Message coolsms = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("to", "01020179422");
        params.put("from", "01030212317");
        params.put("type", "SMS");
        params.put("text", "bancow 테스트 하 빡친다- 왔는지 회신 바람");
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
