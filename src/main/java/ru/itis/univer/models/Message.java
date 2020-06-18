package ru.itis.univer.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users_messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private LocalDateTime dateOfSending;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver_id")
    private User receiver;
}
