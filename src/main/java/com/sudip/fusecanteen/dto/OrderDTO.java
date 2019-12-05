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
   private String id;
   private Map<String , Long> foodQuantity;
   private String username;
   private String status;
   private String type;
}
