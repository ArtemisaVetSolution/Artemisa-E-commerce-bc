package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "medias")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Media {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column ( name = "type", nullable = false)
    private String type;

    @Column (name = "url", nullable = false)
    private String url;

}

