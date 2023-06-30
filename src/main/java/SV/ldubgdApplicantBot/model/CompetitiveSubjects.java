package SV.ldubgdApplicantBot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "competitive_subjects",schema = "public")
public class CompetitiveSubjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "educational_level")
    private String educational_level;
    @Column(name = "speciality")
    private String speciality;
    @Column(name = "subjects")
    private String subjects;

}
