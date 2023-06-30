package SV.ldubgdApplicantBot.repository;

import SV.ldubgdApplicantBot.model.CompetitiveSubjects;
import SV.ldubgdApplicantBot.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompetitiveSubjectsRepository extends JpaRepository <CompetitiveSubjects, Long> {
    @Query(value = "select u.subjects from CompetitiveSubjects u where u.educational_level = :level AND u.speciality = :speciality")
    String findByLevelSpeciality(String level,String speciality);
}
