package com.bijanghanei.bex.bot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
@Component
public class BotInitializer implements CommandLineRunner {
    private final BexBot bexBot;

    public BotInitializer(BexBot bexBot) {
        this.bexBot = bexBot;
    }

    @Override
    public void run(String... args) throws Exception {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this.bexBot);
    }
}
