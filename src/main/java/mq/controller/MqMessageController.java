package mq.controller;

import mq.service.DemoProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.model.MqObject;

/**
 * author: zf
 * Date: 2016/12/20  14:20
 * Description:
 */
@Controller
@RequestMapping("/mq/letGo")
public class MqMessageController {
    @Autowired
    DemoProducerService producerService;

    @RequestMapping(value = "/test")
    @ResponseBody
    public void  sendMessage(MqObject object){

        producerService.sendMessage(object);
    }

    @RequestMapping(value = "/any")
    @ResponseBody
    public void sendMessageInDetail(MqObject object){

        producerService.sendMessageInDetail(object.getExchange(),object.getRoutingKey(),object);
    }
}
