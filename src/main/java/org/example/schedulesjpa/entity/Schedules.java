package org.example.schedulesjpa.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="schedules")
@Getter
public class Schedules extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Setter
    private String title;

    @Column(nullable = false, columnDefinition = "longtext")
    @Setter
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedules() {
    }

    public Schedules(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Schedules(String title, String contents, User user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
