package com.huawei.telegram.bot;

import com.huawei.telegram.bot.handlers.BotHandler;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

public class BotMain {

    public static void main(String[] args) {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        System.out.println("Aplicacao iniciada!!");

        try {
            telegramBotsApi.registerBot(new BotHandler());
        } catch (TelegramApiException ex) {
            System.out.println("Erro na aplicacao: " + ex.getLocalizedMessage());
        }
    }
}
