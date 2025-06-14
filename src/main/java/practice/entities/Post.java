package practice.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_gen")
    @SequenceGenerator(name = "post_gen", sequenceName = "post_seq", allocationSize = 1)
    Long id;
    String description;
    @Column(name = "image_url")
    String imageUrl;
    @Column(name = "create_date")
    LocalDate createDate;
    @Column(name = "likes_count")
    int likesCount = 0;
    @Column(name = "comments_count")
    int commentsCount = 0;
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    User user;
    @OneToMany(mappedBy = "post", cascade = {REMOVE, MERGE, REFRESH})
    List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "post", cascade = {REMOVE, MERGE, REFRESH}, orphanRemoval = true)
    List<Like> likes = new ArrayList<>();

    public Post(String description, String imageUrl, LocalDate createDate, User user) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.createDate = createDate;
        this.user = user;
    }
}
