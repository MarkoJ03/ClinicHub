package com.clinichub.adapters.persistence.reference.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contry")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryJpa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontry")
    private Long id;
    
    @Column(name = "name")
    private String name;
}



