package com.sudip.fusecanteen.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "foods")
public class Food {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private long quantity;
    private double price;
    private boolean status;
    private LocalDate lastOrderedDate;
    @DBRef
    @Field("createdBy")
    private  User createdBy;
}
