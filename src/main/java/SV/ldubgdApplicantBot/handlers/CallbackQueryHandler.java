package SV.ldubgdApplicantBot.handlers;

import SV.ldubgdApplicantBot.messagesender.MessageSender;
import SV.ldubgdApplicantBot.model.InlineKeyboard;
import SV.ldubgdApplicantBot.model.Menu;
import SV.ldubgdApplicantBot.model.Users;
import SV.ldubgdApplicantBot.repository.*;
import SV.ldubgdApplicantBot.service.CreateInlineKeyboard;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CallbackQueryHandler implements Handler <CallbackQuery>{
    private final MessageSender messageSender;
    private final MenuRepository menuRepository;
    private final InlineKeyboardRepository inlineKeyboardRepository;
    private final CreateInlineKeyboard createInlineKeyboard;
    private final UsersRepository usersRepository;
    private final CompetitiveSubjectsRepository competitiveSubjectsRepository;
    private final CostEducationRepository costEducationRepository;
    private final ToEntrantRepository toEntrantRepository;
    private final StagesAdmissionRepository stagesAdmissionRepository;
    public CallbackQueryHandler(@Lazy MessageSender messageSender, MenuRepository menuRepository, InlineKeyboardRepository inlineKeyboardRepository, CreateInlineKeyboard createInlineKeyboard, UsersRepository usersRepository, CompetitiveSubjectsRepository competitiveSubjectsRepository, CostEducationRepository costEducationRepository, ToEntrantRepository toEntrantRepository, StagesAdmissionRepository stagesAdmissionRepository) {
        this.messageSender = messageSender;
        this.menuRepository = menuRepository;
        this.inlineKeyboardRepository = inlineKeyboardRepository;
        this.createInlineKeyboard = createInlineKeyboard;
        this.usersRepository = usersRepository;
        this.competitiveSubjectsRepository = competitiveSubjectsRepository;
        this.costEducationRepository = costEducationRepository;
        this.toEntrantRepository = toEntrantRepository;
        this.stagesAdmissionRepository = stagesAdmissionRepository;
    }
    @Override
    public void choose(CallbackQuery callbackQuery) {
        //надіслати нове повідомлення
        Message message = callbackQuery.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.HTML);
        String chatID = String.valueOf(message.getChatId());
        sendMessage.setChatId(chatID);
        String callbackText = callbackQuery.getData();

        Users users;
        Optional<Users> byTelegramId = usersRepository.findByTelegramId(chatID);
        users = byTelegramId.get();
        ArrayList<String> byBachelor = inlineKeyboardRepository.findByCallback("Бакалаврат спеціальності");
        if (callbackText.equals("Бакалаврат ПЗСО")){
            Menu bySpeciality = menuRepository.findByTitle("speciality");
            sendMessage.setText(bySpeciality.getMenu());
            List<InlineKeyboard> byBachelorSpeciality = inlineKeyboardRepository.findByMenu("Бакалаврат спеціальності");
            sendMessage.setReplyMarkup(createInlineKeyboard.getCreateInlineKeyboard(byBachelorSpeciality));
            users.setEducational_level(callbackText);
            usersRepository.save(users);
        } else if (byBachelor.contains(callbackText)) {
            Menu bachelor = menuRepository.findByTitle("меню спеціальності");
            sendMessage.setText(bachelor.getMenu());
            List<InlineKeyboard> byBachelorMenu = inlineKeyboardRepository.findByMenu("Меню");
            sendMessage.setReplyMarkup(createInlineKeyboard.getCreateInlineKeyboard(byBachelorMenu));
            users.setSpeciality(callbackText);
            usersRepository.save(users);
        } else if (callbackText.equals("Конкурсні предмети")) {
            String byLevelSpeciality = competitiveSubjectsRepository.findByLevelSpeciality(users.getEducational_level(), users.getSpeciality());
            sendMessage.setText(byLevelSpeciality);
        } else if (callbackText.equals("Вартість навчання")) {
            String byLevelSpeciality = costEducationRepository.findByLevelSpeciality(users.getEducational_level(), users.getSpeciality());
            sendMessage.setText(byLevelSpeciality);
        } else if (callbackText.equals("Етапи вступу")) {
            if (users.getEducational_level().equals("Бакалаврат ПЗСО")){
                Menu chooseOrder = menuRepository.findByTitle("вибрати замовлення");
                sendMessage.setText(chooseOrder.getMenu());
                List<InlineKeyboard> byTypeOrder = inlineKeyboardRepository.findByMenu("Тип замовлення");
                sendMessage.setReplyMarkup(createInlineKeyboard.getCreateInlineKeyboard(byTypeOrder));
            }
        } else if (callbackText.equals("Інформація")) {
            Menu byFaculty = menuRepository.findByTitle("кафедра");
            sendMessage.setText(byFaculty.getMenu());
            List<InlineKeyboard> byMenu = inlineKeyboardRepository.findByMenu(users.getSpeciality());
            sendMessage.setReplyMarkup(createInlineKeyboard.getCreateInlineKeyboardURL(byMenu));
        } else if (callbackText.equals("Вступ/інфо")) {
            String byLevelSpeciality = toEntrantRepository.findByLevelSpeciality(users.getEducational_level(), users.getSpeciality());
            sendMessage.setText(byLevelSpeciality);
        } else if (callbackText.equals("Державне замовлення")) {
            users.setGovernment_order(callbackText);
            users.setFull_time("Денна");
            usersRepository.save(users);
            String byLevelSpeciality = stagesAdmissionRepository.findByLevelSpeciality(users.getEducational_level(), users.getSpeciality(), users.getGovernment_order(), users.getFull_time());
            sendMessage.setText(byLevelSpeciality);
        } else if (callbackText.equals("Кошти фізичних осіб")) {
            users.setGovernment_order(callbackText);
            usersRepository.save(users);
            Menu chooseFormEducation = menuRepository.findByTitle("вибрати форму");
            sendMessage.setText(chooseFormEducation.getMenu());
            List<InlineKeyboard> formEducation = inlineKeyboardRepository.findByMenu("Форма навчання");
            sendMessage.setReplyMarkup(createInlineKeyboard.getCreateInlineKeyboard(formEducation));
        } else if (callbackText.equals("Денна") || callbackText.equals("Заочна")) {
            users.setFull_time(callbackText);
            usersRepository.save(users);
            String byLevelSpeciality = stagesAdmissionRepository.findByLevelSpeciality(users.getEducational_level(), users.getSpeciality(), users.getGovernment_order(), users.getFull_time());
            sendMessage.setText(byLevelSpeciality);
        }
        messageSender.sendMessage(sendMessage);
    }
}
