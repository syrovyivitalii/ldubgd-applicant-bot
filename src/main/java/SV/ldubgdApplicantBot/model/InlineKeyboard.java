package SV.ldubgdApplicantBot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "inline_keyboard",schema = "public")
public class InlineKeyboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "menu")
    private String menu;
    @Column(name = "keyboard")
    private String keyboard;
    @Column(name = "callback")
    private String callback;
    @Column(name = "sort")
    private Integer sort;
    @Column(name = "url")
    private String url;
}
