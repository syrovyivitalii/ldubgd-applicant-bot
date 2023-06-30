package SV.ldubgdApplicantBot.handlers;

import SV.ldubgdApplicantBot.messagesender.MessageSender;
import SV.ldubgdApplicantBot.model.InlineKeyboard;
import SV.ldubgdApplicantBot.model.Menu;
import SV.ldubgdApplicantBot.model.Users;
import SV.ldubgdApplicantBot.repository.InlineKeyboardRepository;
import SV.ldubgdApplicantBot.repository.MenuRepository;
import SV.ldubgdApplicantBot.repository.UsersRepository;
import SV.ldubgdApplicantBot.service.CreateInlineKeyboard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class MessageHandler implements Handler <Message> {
    private final MessageSender messageSender;
    private final UsersRepository usersRepository;
    private final MenuRepository menuRepository;
    private final InlineKeyboardRepository inlineKeyboardRepository;
    private final CreateInlineKeyboard createInlineKeyboard;

    public MessageHandler(@Lazy MessageSender messageSender, UsersRepository usersRepository, MenuRepository menuRepository,InlineKeyboardRepository inlineKeyboardRepository, CreateInlineKeyboard createInlineKeyboard) {
        this.messageSender = messageSender;
        this.usersRepository = usersRepository;
        this.menuRepository = menuRepository;
        this.inlineKeyboardRepository = inlineKeyboardRepository;
        this.createInlineKeyboard = createInlineKeyboard;
    }

    @Override
    public void choose(Message message) {
        String chatId = String.valueOf(message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        log.info("{}:{}", chatId, message.getText());
        if (message.hasText()){
            String messageText = message.getText();
            if (messageText.equals("/start")){
                //додавання користувача в БД
                Users users = new Users();
                Optional<Users> byTelegramId = usersRepository.findByTelegramId(chatId);
                if (!byTelegramId.isPresent()){
                    users.setTelegram_id(chatId);
                    usersRepository.save(users);
                }
                Menu byTitle = menuRepository.findByTitle(messageText);
                sendMessage.setText(byTitle.getMenu());
            } else if (messageText.equals("/info")) {
                Menu byTitle = menuRepository.findByTitle(messageText);
                sendMessage.setText(byTitle.getMenu());
            } else if (messageText.equals("/educational_level")){
                Menu byTitle = menuRepository.findByTitle(messageText);
                sendMessage.setText(byTitle.getMenu());
                List<InlineKeyboard> byMenu = inlineKeyboardRepository.findByMenu(messageText);
                sendMessage.setReplyMarkup(createInlineKeyboard.getCreateInlineKeyboard(byMenu));
            }else if (messageText.equals("/reception_procedure")){
                Menu byTitle = menuRepository.findByTitle(messageText);
                sendMessage.setText(byTitle.getMenu());
            }
            messageSender.sendMessage(sendMessage);
        }
    }
}
