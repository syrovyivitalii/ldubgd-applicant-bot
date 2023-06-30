package SV.ldubgdApplicantBot.repository;

import SV.ldubgdApplicantBot.model.StagesAdmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StagesAdmissionRepository extends JpaRepository <StagesAdmission,Long> {
    @Query(value = "select u.dates from StagesAdmission u where u.educational_level = :level AND u.speciality = :speciality AND u.government_order = :government_order AND u.full_time = :full_time")
    String findByLevelSpeciality(String level,String speciality, String government_order,String full_time);
}
