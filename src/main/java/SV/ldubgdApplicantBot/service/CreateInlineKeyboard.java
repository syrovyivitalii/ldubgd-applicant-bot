package SV.ldubgdApplicantBot.service;

import SV.ldubgdApplicantBot.model.InlineKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class CreateInlineKeyboard {
    private InlineKeyboardMarkup createInlineKeyboard(List<InlineKeyboard> inlineKeyboards){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardList = new ArrayList<>();

        // sort inlineKeyboards by "sort" column
        Collections.sort(inlineKeyboards, Comparator.comparingInt(InlineKeyboard::getSort));

        // add buttons to keyboardList in the sorted order
        int index = 0;
        for (InlineKeyboard keyboard: inlineKeyboards){
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(InlineKeyboardButton.builder()
                    .text(keyboard.getKeyboard())
                    .callbackData(keyboard.getCallback())
                    .build());
            keyboardList.add(row);
            index++;
        }

        inlineKeyboardMarkup.setKeyboard(keyboardList);
        return inlineKeyboardMarkup;
    }
    public InlineKeyboardMarkup getCreateInlineKeyboard(List<InlineKeyboard> inlineKeyboards){
        return createInlineKeyboard(inlineKeyboards);
    }
    private InlineKeyboardMarkup createInlineKeyboardURL(List<InlineKeyboard> inlineKeyboards){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardList = new ArrayList<>();

        // sort inlineKeyboards by "sort" column
        Collections.sort(inlineKeyboards, Comparator.comparingInt(InlineKeyboard::getSort));

        // add buttons to keyboardList in the sorted order
        int index = 0;
        for (InlineKeyboard keyboard: inlineKeyboards){
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(InlineKeyboardButton.builder()
                    .text(keyboard.getKeyboard())
                    .callbackData(keyboard.getCallback())
                    .url(keyboard.getUrl())
                    .build());
            keyboardList.add(row);
            index++;
        }

        inlineKeyboardMarkup.setKeyboard(keyboardList);
        return inlineKeyboardMarkup;
    }
    public InlineKeyboardMarkup getCreateInlineKeyboardURL(List<InlineKeyboard> inlineKeyboards){
        return createInlineKeyboardURL(inlineKeyboards);
    }
}
