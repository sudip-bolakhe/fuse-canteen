package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.dto.UserDTO;
import com.sudip.fusecanteen.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    User add(UserDTO userDTO);

    List<User> getAll(Pageable pageable);

    User update(User user);

    User getByUsername(String username);

    ResponseEntity<?> login(UserDTO userDTO);
}
