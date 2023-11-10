package com.kad.configs;

import com.kad.entity.ContactForm;
import com.kad.service.ContactFormService;
import com.kad.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class AppConfigs {

    @Bean
    public EmailUtils emailUtils() {
    return new EmailUtils();
    }

}
