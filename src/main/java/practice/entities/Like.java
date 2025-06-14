package practice.entities;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.CascadeType.DETACH;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_gen")
    @SequenceGenerator(name = "like_gen", sequenceName = "like_seq", allocationSize = 1)
    Long id;
    @Column(name = "is_like")
    boolean isLike;
    @OneToOne(cascade = {DETACH})
    User user;
    @ManyToOne(cascade = {DETACH})
    Post post;
    @ManyToOne(cascade = {DETACH})
    Comment comment;
}
