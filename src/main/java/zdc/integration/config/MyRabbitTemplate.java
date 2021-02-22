package zdc.integration.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRabbitTemplate {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public MyRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate=rabbitTemplate;
    }

    public boolean sendToDirect(Object o){
        this.rabbitTemplate.setExchange(MyRabbitMq.KEY_EXCHANGE_DIRECT);
        this.rabbitTemplate.convertAndSend(o);
        return true;
    }




}
