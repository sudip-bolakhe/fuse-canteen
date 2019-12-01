package com.sudip.fusecanteen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "menus")
public class Menu {

    @Id
    private String id;
    @DBRef
    @JsonIgnoreProperties("createdBy")
    private List<Food> foods;
    private LocalDate date;
    @DBRef
    private User createdBy;
}
