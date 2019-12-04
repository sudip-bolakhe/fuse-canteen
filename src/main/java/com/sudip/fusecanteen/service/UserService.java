package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User add(User user);

    List<User> getAll(Pageable pageable);

    User update(User user);

    User getByUsername(String username);
}
