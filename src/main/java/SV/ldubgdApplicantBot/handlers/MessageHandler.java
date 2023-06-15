package SV.ldubgdApplicantBot.handlers;

import SV.ldubgdApplicantBot.messagesender.MessageSender;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
@Component
public class MessageHandler implements Handler <Message> {
    private final MessageSender messageSender;

    public MessageHandler(@Lazy MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public void choose(Message message) {

    }
}
