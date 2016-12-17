package com.test.mail.app.business.conf;

import com.test.mail.app.dao.conf.HibernateConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by tigran on 12/11/16.
 */
@Configuration
@ComponentScan(basePackages = "com.test.mail.app.business")
@Import(HibernateConfiguration.class)
//@PropertySource(value = { "classpath:dao-app.properties" })
public class BusinessConfig {
}
