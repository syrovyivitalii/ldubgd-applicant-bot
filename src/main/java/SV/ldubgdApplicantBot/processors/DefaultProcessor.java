package SV.ldubgdApplicantBot.processors;

import SV.ldubgdApplicantBot.handlers.CallbackQueryHandler;
import SV.ldubgdApplicantBot.handlers.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class DefaultProcessor implements Processor{
    private MessageHandler messageHandler;
    private CallbackQueryHandler callbackQueryHandler;
    @Override
    public void executeMessage(Message message) {
        messageHandler.choose(message);
    }
    @Override
    public void executeCallBackQuery(CallbackQuery callbackQuery) {
        callbackQueryHandler.choose(callbackQuery);
    }

    @Autowired
    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Autowired
    public void setCallbackQueryHandler(CallbackQueryHandler callbackQueryHandler) {
        this.callbackQueryHandler = callbackQueryHandler;
    }
}
