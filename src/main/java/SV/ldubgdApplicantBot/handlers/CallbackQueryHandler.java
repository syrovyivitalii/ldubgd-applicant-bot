package SV.ldubgdApplicantBot.handlers;

import SV.ldubgdApplicantBot.messagesender.MessageSender;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Component
public class CallbackQueryHandler implements Handler <CallbackQuery>{
    private final MessageSender messageSender;

    public CallbackQueryHandler(@Lazy MessageSender messageSender) {
        this.messageSender = messageSender;
    }
    @Override
    public void choose(CallbackQuery callbackQuery) {

    }
}
