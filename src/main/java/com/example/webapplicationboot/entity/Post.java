package com.example.webapplicationboot.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Post {
    private static final String DATE_PATTERN = "yyyy-MM-dd  HH:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String massage;
    @ManyToOne(targetEntity = User.class)
    private User author;
    private LocalDateTime date = LocalDateTime.now();
    @Transient
    private List<Like> likes = new ArrayList<>();
    @Transient
    private List<Comment> comments = new ArrayList<>();

    /**
     * Constructor for getting post from data base:
     * */
    public Post(long id, String title, String massage, User author, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.massage = massage;
        this.author = author;
        this.date = date;
    }

    /**
     * Constructor for getting demo-post from data base:
     * */
    public Post(long id, String title, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }


    public void setComment(Comment comment) {
        this.comments.add(comment);
    }

    public void setLike(Like like) {
        this.likes.add(like);
    }

    public String showDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return date.format(formatter);
    }
}
