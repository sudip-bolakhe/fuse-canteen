package com.sudip.fusecanteen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class MenuDTO {

    private List<String> foodName;
    private String username;
}
