package com.test.mail.app.web.conf;

import com.test.mail.app.business.conf.BusinessConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by tigran on 12/18/16.
 */
@Configuration
@ComponentScan(basePackages = "com.test.mail.app.web")
@Import(BusinessConfig.class)
public class WebConfig {
}
