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
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_gen")
    @SequenceGenerator(name = "comment_gen", sequenceName = "comment_seq", allocationSize = 1)
    Long id;
    @Column(name = "comment_text")
    String commentText;
    @Column(name = "create_date")
    LocalDate createDate;
    @Column(name = "changed_date")
    LocalDate changedDate;
    @Column(name = "likes_count")
    int likesCount = 0;
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    User user;
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    Post post;
    @OneToMany(mappedBy = "comment", cascade = {REMOVE})
    List<Like> likes = new ArrayList<>();
}
