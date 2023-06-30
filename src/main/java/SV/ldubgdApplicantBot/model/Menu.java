package SV.ldubgdApplicantBot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "menu",schema = "public")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "menu")
    private String menu;
}
