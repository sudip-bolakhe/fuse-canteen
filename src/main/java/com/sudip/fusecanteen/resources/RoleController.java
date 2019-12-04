package com.sudip.fusecanteen.resources;

import com.sudip.fusecanteen.model.Role;
import com.sudip.fusecanteen.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sudip.fusecanteen.utils.ApiConstant.ROLE_PATH;

@RequestMapping(ROLE_PATH)
@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> add(@RequestBody Role role){
        Role savedRole = roleService.add(role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
