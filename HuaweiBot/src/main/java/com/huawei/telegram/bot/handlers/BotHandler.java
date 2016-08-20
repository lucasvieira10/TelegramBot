package com.huawei.telegram.bot.handlers;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendChatAction;
import org.telegram.telegrambots.api.methods.send.SendLocation;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.huawei.telegram.bot.constants.BotConfig;
import com.huawei.telegram.bot.maps.Maps;

import emoji4j.EmojiUtils;

public class BotHandler extends TelegramLongPollingBot {

    static final Logger LOG = LoggerFactory.getLogger(BotHandler.class);

    private static final String START_COMMAND = "/start";
    private static final String LL_COMMAND = "/ll";
    private static final String LOCATION_COMMAND = "/location";
    
    @SuppressWarnings("deprecation")
	@Override
    public void onUpdateReceived(Update update) {

        String nomeUsuario = update.getMessage().getFrom().getFirstName();

        String comando_recebido = update.getMessage().getText();
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(update.getMessage().getChatId().toString());
        sendMessageRequest.enableMarkdown(true);
        SendChatAction sendChatAction = new SendChatAction();
        sendChatAction.setChatId(update.getMessage().getChatId().toString());
        sendChatAction.setAction("typing");
        try {
            sendChatAction(sendChatAction);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        switch (comando_recebido) {
            case START_COMMAND:
                sendMessageRequest.setText("Bem vindo ao Bot " + nomeUsuario);
                break;
                
            case LL_COMMAND:            	
            	SendPhoto photo = new SendPhoto();
            	photo.setChatId(update.getMessage().getChatId().toString()); 
            	photo.setCaption("My Picture");
            	photo.setNewPhoto(new File("/home/huawei/Desktop/Yo.jpg"));
            	
				try {
					sendPhoto(photo);
				} catch (TelegramApiException e1) {
					e1.printStackTrace();
				}
	            
            	sendMessageRequest.setText(EmojiUtils.emojify(":D"));

				break;
			
            case LOCATION_COMMAND:
            	Maps maps = new Maps("Campina Grande/PB");

            	SendLocation location = new SendLocation();
            	location.setChatId(update.getMessage().getChatId().toString());
            	location.setLatitude(maps.getLatitude());
            	location.setLongitude(maps.getLongitude());
            	
            	try {
            		sendLocation(location);
            	} catch (TelegramApiException e1) {
            		e1.printStackTrace();
            	}
            	
            	break;
			
            default:
                sendMessageRequest.setText("Você digitou: " + update.getMessage().getText());
                break;
        }

//        try {
//            sendMessage(sendMessageRequest);
//        } catch (TelegramApiException e) {
//            LOG.error("Falha na API do Telegram, erro: " + e.getMessage());
//            e.printStackTrace();
//        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.NFL_BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.NFL_BOT_KEY;
    }
}
