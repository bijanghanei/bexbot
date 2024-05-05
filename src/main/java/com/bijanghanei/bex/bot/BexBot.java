package com.bijanghanei.bex.bot;

import com.bijanghanei.bex.config.BotConfig;
import com.bijanghanei.bex.entity.Price;
import com.bijanghanei.bex.service.BexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class BexBot extends TelegramLongPollingBot {

    private final BexService bexService;
    private final BotConfig botConfig;

    public BexBot(BexService bexService, BotConfig botConfig) {
        this.bexService = bexService;
        this.botConfig = botConfig;
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUsername();
    }
    @Override
    public String getBotToken(){
        return botConfig.getBotToken();
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
                    sendMessageAndKeyboard("Hi! Welcome to BEX! \n You can choose the currency you want to get the price for it.",message.getChatId());
                }else{
                    List<String> symbols = bexService.getWatchList().getSymbols();
                    if (symbols.contains(text.toLowerCase())) {
                        String answer = createPriceString(text);
                        sendMessage(answer, message.getChatId());
                    }
                }
            }
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
    private String createPriceString(String symbol){
        Price price = bexService.getPrice(symbol.toLowerCase());
        String priceString = "High : " + price.getHigh()
                + "\n Current : " + price.getPrice()
                + "\n Low : " + price.getLow();
        return priceString;
    }
    private void sendMessageAndKeyboard(String textToSend, long chatId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);

//      Creating keyboard markup
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRowList = new ArrayList<>();

//      Getting latest symbols in the symbols list of the currency exchange API
        List<String> symbols = bexService.getWatchList().getSymbols();

//      Adding symbols to keyboard

        for (String symbol : symbols){
            if (!Objects.equals(symbol, "irr")){
                KeyboardRow row = new KeyboardRow();
                row.add(symbol.toUpperCase());
                keyboardRowList.add(row);
            }
        }

        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        message.setReplyMarkup(replyKeyboardMarkup);


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
