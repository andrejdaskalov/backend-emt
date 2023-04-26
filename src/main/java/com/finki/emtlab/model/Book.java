package com.finki.emtlab.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated
    private Category category;
    @ManyToOne
    private Author author;
    private Integer availableCopies;

}
