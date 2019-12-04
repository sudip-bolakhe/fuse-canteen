package com.sudip.fusecanteen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class OrderDTO {

    Map<String , Long> foodQuantity;
    String username;


}
