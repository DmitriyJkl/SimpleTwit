package org.example.dpostnov.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor()
public class Message {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Setter
    @NonNull
    private String text;
    @Setter
    @NonNull
    private String tag;


}
