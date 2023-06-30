package SV.ldubgdApplicantBot.repository;

import SV.ldubgdApplicantBot.model.InlineKeyboard;
import SV.ldubgdApplicantBot.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface InlineKeyboardRepository extends JpaRepository <InlineKeyboard,Long> {
    @Query(value = "select u from InlineKeyboard u where u.menu = :menu")
    List<InlineKeyboard> findByMenu(String menu);

    @Query(value = "select u.callback from InlineKeyboard u where u.menu = :menu")
    ArrayList findByCallback(String menu);
}
