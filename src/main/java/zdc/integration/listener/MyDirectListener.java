package zdc.integration.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import zdc.integration.config.MyRabbitMq;

@Component
@Slf4j
public class MyDirectListener {

    @RabbitListener(bindings ={@QueueBinding(value = @Queue(value = "q5",durable = "true"),
            exchange =@Exchange(value = MyRabbitMq.KEY_EXCHANGE_DIRECT,durable = "true",type = ExchangeTypes.DIRECT),
            key = "welcome")})
    public void foL1(String in){
        log.info(in);
    }

}
