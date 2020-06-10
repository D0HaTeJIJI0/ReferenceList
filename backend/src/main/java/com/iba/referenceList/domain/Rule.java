package com.iba.referenceList.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Rule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 1000000)
    private String text;

    @Column(nullable = false)
    private Instant createDate;

    @OneToMany(mappedBy = "rule")
    private List<ReferenceNote> referenceNotes;

    public Rule(Long id) {
        this.id = id;
    }
}
