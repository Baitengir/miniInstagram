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
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH}, fetch = FetchType.EAGER)
    User user;
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH}, fetch = FetchType.EAGER)
    Post post;
    @OneToMany(mappedBy = "comment", cascade = {REMOVE})
    List<Like> likes = new ArrayList<>();

    @Override
    public String toString() {
        return "\nComment{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", createDate=" + createDate +
                ", changedDate=" + changedDate +
                ", likesCount=" + likesCount +
                '}';
    }
}
