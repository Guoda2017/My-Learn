package com.guo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : fengbo.guo
 * @date : 2023-04-14 16:10
 * @Description :
 */
@Slf4j
public class MqDemo {

    private final static String QUEUE_NAME = "ttl.oms.queue";

    public static void main(String[] argv) throws Exception {

        String uri = "amqp://sbs:zabank123@10.0.0.191:5672/sbs";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(uri);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        channel.queueBind(QUEUE_NAME, "", QUEUE_NAME);

        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}

