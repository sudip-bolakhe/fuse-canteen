package com.sudip.fusecanteen.service.mapper;

import com.sudip.fusecanteen.dto.UserDTO;
import com.sudip.fusecanteen.model.Role;
import com.sudip.fusecanteen.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {

    public User convertToUser(UserDTO userDTO, List<Role> roles){
       User user = new User();
       user.setPassword(userDTO.getPassword());
       user.setUsername(userDTO.getUsername());
       user.setAddress(userDTO.getAddress());
       user.setName(userDTO.getName());
       user.setRoles(roles);
        return user;
    }
}
