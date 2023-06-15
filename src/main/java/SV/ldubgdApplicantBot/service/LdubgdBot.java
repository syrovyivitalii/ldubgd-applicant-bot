package SV.ldubgdApplicantBot.service;

import SV.ldubgdApplicantBot.processors.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
@Component
public class LdubgdBot extends TelegramLongPollingBot {
    @Value("${BOT.BOT_USERNAME}")
    private String botUsername;
    @Value("${BOT.BOT_TOKEN}")
    private String botToken;
    private Processor processor;
    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        processor.process(update);
    }
    @Autowired
    public void setProcessor(Processor processor) {
        this.processor = processor;
    }
}
