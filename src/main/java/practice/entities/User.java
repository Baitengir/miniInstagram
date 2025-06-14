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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1)
    Long id;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    String email;
    @Column(name = "phone_number")
    String phoneNumber;
    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user",
            cascade = {
                    REMOVE,
                    MERGE,
                    REFRESH
            }
    )
    List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = {REMOVE, MERGE, REFRESH})
    List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = {REMOVE, MERGE, REFRESH})
    List<Like> likes = new ArrayList<>();

    public User(String firstName, String lastName, String email, String phoneNumber, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
}
