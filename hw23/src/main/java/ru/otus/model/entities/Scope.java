package ru.otus.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "scopes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scope {

    @Id
    private String id;

    private String name;
    private String description;

    private List<String> packages;
}
