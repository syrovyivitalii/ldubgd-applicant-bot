package SV.ldubgdApplicantBot.repository;

import SV.ldubgdApplicantBot.model.CostEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CostEducationRepository extends JpaRepository <CostEducation,Long> {
    @Query(value = "select u.price from CostEducation u where u.educational_level = :level AND u.speciality = :speciality")
    String findByLevelSpeciality(String level,String speciality);
}
