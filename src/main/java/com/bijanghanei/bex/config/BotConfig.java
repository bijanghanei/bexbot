package com.bijanghanei.bex.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource("application.properties")
public class BotConfig {
    @Value("${bot.username}")
    private String botUsername;
    @Value("${bot.token}")
    private String botToken;
}
