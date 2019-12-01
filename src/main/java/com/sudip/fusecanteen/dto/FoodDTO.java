package com.sudip.fusecanteen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class FoodDTO {
    private String id;
    private String name;
    private long quantity;
    private double price;
    private boolean status;
    private LocalDate lastOrderedDate;
    private String username;
}
