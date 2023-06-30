package SV.ldubgdApplicantBot.repository;

import SV.ldubgdApplicantBot.model.ToEntrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ToEntrantRepository extends JpaRepository<ToEntrant,Long> {
    @Query(value = "select u.info from ToEntrant u where u.educational_level = :level AND u.speciality = :speciality")
    String findByLevelSpeciality(String level,String speciality);
}
