package SV.ldubgdApplicantBot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "to_entrant",schema = "public")
public class ToEntrant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "educational_level")
    private String educational_level;
    @Column(name = "speciality")
    private String speciality;
    @Column(name = "info")
    private String info;
}
