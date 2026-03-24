package com.example.RealEstate.Model;

import com.example.RealEstate.Enum.TourStatus;
import com.example.RealEstate.Enum.TourType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tour_schedules")
public class TourSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tourId;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private TourType tourType; // virtual, physical

    private LocalDateTime scheduledAt;

    @Enumerated(EnumType.STRING)
    private TourStatus status; // pending, confirmed, cancelled
}
