package test;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitServlet implements InitializingBean {
 
    @Autowired
    private Producer producer;
    @Autowired
    private Consumer consumer;
 
    @Override
    public void afterPropertiesSet() throws Exception {
        consumer.receiver();
        producer.produce();
    }
 
}