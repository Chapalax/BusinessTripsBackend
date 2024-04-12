package ru.mts.hackathon.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users_data")
public class UserDataEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "ud_seq", sequenceName = "users_data_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ud_seq")
    private long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private UserEntity userId;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty
    @Column(name = "father_name")
    private String fatherName;

    @NotEmpty
    @Column(name = "grade")
    private String grade;

    @Column(name = "birth_date")
    private OffsetDateTime birthDate;

    @NotEmpty
    @Column(name = "phone")
    private String phone;

    @NotEmpty
    @Column(name = "passport")
    private String passport;

}
