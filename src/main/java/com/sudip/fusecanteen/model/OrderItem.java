package com.sudip.fusecanteen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "order_items")
public class OrderItem {

    @Id
    private String id;
    @JsonIgnoreProperties("createdBy")
    @DBRef
    private Food food;
    private long quantity;

    public OrderItem(Food food, long quantity){
        this.food = food;
        this.quantity = quantity;
    }
}
