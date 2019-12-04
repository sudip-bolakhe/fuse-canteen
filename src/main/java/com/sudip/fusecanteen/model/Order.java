package com.sudip.fusecanteen.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Document(collection = "order")
public class Order {

    @Id
    private String id;
    @DBRef
    private List<Food> foods;
    @DBRef
    private  User user;
    private LocalDate date;

}