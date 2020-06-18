package ru.itis.univer.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder()
@Table(name = "users_files")
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storageFileName;
    private String originalFileName;
    private Long size;
    private String type;
    private String url;


}
