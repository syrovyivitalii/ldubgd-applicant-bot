package SV.ldubgdApplicantBot.repository;

import SV.ldubgdApplicantBot.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface MenuRepository extends JpaRepository <Menu,Long> {
    @Query(value = "select u from Menu u where u.title = :title")
    Menu findByTitle(String title);

}
