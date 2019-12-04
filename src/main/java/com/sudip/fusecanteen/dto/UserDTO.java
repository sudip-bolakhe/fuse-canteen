package com.sudip.fusecanteen.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String address;
    private String username;
    private String password;
    private List<String> roles;

}
