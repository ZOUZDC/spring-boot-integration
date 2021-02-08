package zdc.integration.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zdc.integration.config.MyRabbitMq;
import zdc.integration.config.MyRabbitTemplate;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/z1")
@Slf4j
public class FirstController {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    MyRabbitTemplate myRabbitTemplate;




    @GetMapping("/z1")
    public String getOne(@RequestParam(defaultValue = "1") Integer count){
        for (int i = 0; i < count; i++) {
            // 队列唯一 ,可以不写交换机
          //  rabbitTemplate.convertAndSend(MyRabbitMq.KEY_EXCHANGE_DIRECT, MyRabbitMq.KEY_QUEUE_DIRECT,"北京时间:"+LocalDateTime.now().toString());
            rabbitTemplate.convertAndSend( MyRabbitMq.KEY_QUEUE_DIRECT,"北京时间:"+LocalDateTime.now().toString());
        }
        return "Ok" ;
    }

    @GetMapping("/z2")
    public String getTwo(@RequestParam(defaultValue = "1") Integer count){
        for (int i = 0; i < count; i++) {
           // log.info("---------------------------");
            myRabbitTemplate.sendToDirect("上海时间:"+LocalDateTime.now().toString());
        }
        return "Ok" ;
    }



}
