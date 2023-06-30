package SV.ldubgdApplicantBot.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table (name = "users",schema = "public")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "telegram_id")
    private String telegram_id;
    @Column(name = "educational_level")
    private String educational_level;
    @Column(name = "speciality")
    private String speciality;
    @Column(name = "government_order")
    private String government_order;
    @Column(name = "full_time")
    private String full_time;

}
