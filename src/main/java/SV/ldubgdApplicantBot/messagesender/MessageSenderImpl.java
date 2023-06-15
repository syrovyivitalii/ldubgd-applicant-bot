package SV.ldubgdApplicantBot.messagesender;

import SV.ldubgdApplicantBot.service.LdubgdBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
@Service
public class MessageSenderImpl implements MessageSender{
    private LdubgdBot ldubgdBot;
    @Override
    public void sendMessage(SendMessage sendMessage) {
        try {
            ldubgdBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    @Autowired
    public void setLdubgdBot(LdubgdBot ldubgdBot) {
        this.ldubgdBot = ldubgdBot;
    }
}
