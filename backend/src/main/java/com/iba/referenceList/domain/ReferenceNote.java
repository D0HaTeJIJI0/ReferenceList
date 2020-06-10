package com.iba.referenceList.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class ReferenceNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Instant date;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Rule rule;

    public ReferenceNote(Long id) {
        this.id = id;
    }
}
