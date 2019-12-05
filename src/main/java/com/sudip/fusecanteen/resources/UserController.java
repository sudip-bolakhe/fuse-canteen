package com.sudip.fusecanteen.resources;

import com.sudip.fusecanteen.dto.UserDTO;
import com.sudip.fusecanteen.model.User;
import com.sudip.fusecanteen.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sudip.fusecanteen.utils.ApiConstant.USER_API;

@RestController
@RequestMapping(USER_API)
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> add(@RequestBody UserDTO userDTO) {
        User addedUser = userService.add(userDTO);
        return new ResponseEntity<>(addedUser, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        User updatedUser = userService.update(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(@PageableDefault(page = 0
            , size = 10
            , direction = Sort.Direction.DESC)
                                                     Pageable pageable) {
        List<User> users = userService.getAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        User addedUser = userService.getByUsername(username);
        return new ResponseEntity<>(addedUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    ResponseEntity login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }
}
