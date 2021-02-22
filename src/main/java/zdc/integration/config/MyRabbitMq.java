package zdc.integration.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Slf4j
@Configuration
@Order(9)
public class MyRabbitMq {


    public final  static  String  KEY_EXCHANGE_DIRECT ="zhilianExchange";
    public final  static  String  KEY_QUEUE_DIRECT ="zhilianqueue";


    @Autowired
    ConnectionFactory connectionFactory;

    @Bean
    public RabbitAdmin MyRabbitAdmin(
        @Qualifier("MyDirectQueue") Queue MyDirectQueue,
        @Qualifier("MyDirectExchange") DirectExchange MyDirectExchange,
        @Qualifier("MyBindingDirect") Binding bindingDirect

    ){
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.declareQueue(MyDirectQueue);
        admin.declareExchange(MyDirectExchange);
        admin.declareBinding(bindingDirect);
        return admin;
    }


    @Bean("MyDirectQueue")
    public Queue MyDirectQueue(){
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
      return  new Queue(KEY_QUEUE_DIRECT,true,false,false);
    }

    @Bean("MyDirectExchange")
    public DirectExchange MyDirectExchange(){
        return  new DirectExchange(KEY_EXCHANGE_DIRECT,true,false);
    }

    //创建并绑定直连型交换机 多用于单机队列
    @Bean("MyBindingDirect")
    public Binding MyBindingDirect(
            @Qualifier("MyDirectQueue") Queue q,
            @Qualifier("MyDirectExchange") DirectExchange e
    ){
        return BindingBuilder.bind(q).to(e).withQueueName();
    }


    //工具类, 可以不用每次发信息的时候都写routerkey和 exchange
  /*  @Bean("myRabbitTemplate")
    public MyRabbitTemplate  getMyRabbitTemplate(RabbitTemplate rabbitTemplate){
        return new MyRabbitTemplate(rabbitTemplate);
    }*/





}
