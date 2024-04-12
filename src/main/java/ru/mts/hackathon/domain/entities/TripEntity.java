package ru.mts.hackathon.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "trips")
public class TripEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "tr_seq", sequenceName = "trips_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tr_seq")
    private long id;

    @OneToOne
    @JoinColumn(name="owner_id")
    private UserEntity ownerId;

    @NotEmpty
    @Column(name = "boss_id")
    private Long bossId;

    @Column(name = "creation_date")
    private OffsetDateTime creationDate;

    @Column(name = "start_date")
    private OffsetDateTime startDate;

    @Column(name = "end_date")
    private OffsetDateTime endDate;

    @NotEmpty
    @Column(name = "destination")
    private String destination;

    @NotEmpty
    @Column(name = "goal")
    private String goal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "comment")
    private String comment;
}
