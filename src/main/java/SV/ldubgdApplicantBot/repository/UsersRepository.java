package SV.ldubgdApplicantBot.repository;


import SV.ldubgdApplicantBot.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository <Users,Long> {
    @Query(value = "select u from Users u where u.telegram_id = :telegram_id")
    Optional<Users> findByTelegramId(String telegram_id);
}
