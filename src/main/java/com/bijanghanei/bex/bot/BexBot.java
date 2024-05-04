package com.bijanghanei.bex.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
@Slf4j
@Component
public class BexBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "bexcurrencybot";
    }
    @Override
    public String getBotToken(){
        return "mybottoken";
    }
    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();
            if(message.hasText()){
                String text = message.getText();

                if (text.equals("/start")){
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText("Hi! Welcome to BEX! \n You can choose the currency you want to get the price for it.");
                    sendMessage.setChatId(message.getChatId());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
    private void sendMessageAndKeyboard(String textToSend, long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

//        TODO create keyboard with currencies

        executeMessage(message);
    }
    private void sendMessage(String textToSend, long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(textToSend);
        executeMessage(sendMessage);
    }
    private void executeMessage(SendMessage sendMessage){
        try {
            execute(sendMessage);
        }catch (TelegramApiException e){
            log.error("ERROR_TEXT: " + e.getMessage());
        }
    }
}
