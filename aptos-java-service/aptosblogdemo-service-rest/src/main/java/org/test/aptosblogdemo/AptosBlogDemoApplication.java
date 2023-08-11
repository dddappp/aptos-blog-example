package org.test.aptosblogdemo;

import org.test.aptosblogdemo.aptos.contract.service.ContractInitService;
import org.test.aptosblogdemo.specialization.ApplicationContext;
import org.test.aptosblogdemo.specialization.spring.SpringApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableSwagger2
@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
})
@EntityScan(basePackages = {
        "org.test.aptosblogdemo.aptos.contract"
})
@EnableScheduling
//@EnableAutoConfiguration
public class AptosBlogDemoApplication {

    @Autowired
    private ContractInitService contractInitService;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AptosBlogDemoApplication.class, args);
        ApplicationContext.current = new SpringApplicationContext(ctx);
        ctx.publishEvent(new ContextStartedEvent(ctx));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initAccountAddresses() {
        contractInitService.initAccountAddresses();
    }
}
