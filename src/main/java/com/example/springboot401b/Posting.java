package com.example.springboot401b;

import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import java.time.LocalDateTime;


@Entity
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    @NotNull
    @Size(min = 3)
    private String title;
    @NotNull
    @Size(min=3)
    private String content;
    @NotNull
    @Size(min=3)
    private String  postedDate;


//@Temporal()
//    private LocalDateTime postedDateTime;
//    public static LocalDateTime of(int year,
//                                   Month month,
//                                   int dayOfMonth,
//                                   int hour,
//                                   int minute)

//    public static LocalDateTime postedDateTime;
//    public void LocalDateTime(@RequestParam("localDateTime") LocalDateTime postedDateTime) {
//        // ...
//    }

//  @NotNull
//   @Size(min=3)
//    private String postedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User1 user;



//    public long user_id;

    public Posting() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

//    public String getPostedBy() {
//        return postedBy;
//    }
//
//    public void setPostedBy(String postedBy) {
//        this.postedBy = postedBy;
//    }

    public User1 getUser() {
        return user;
    }

    public void setUser(User1 user) {
        this.user = user;
    }
//    public LocalDateTime getPostedDateTime() {
//        return postedDateTime;
//    }
//
//    public void setPostedDateTime(LocalDateTime postedDateTime) {
//        this.postedDateTime = postedDateTime;
//    }
}

