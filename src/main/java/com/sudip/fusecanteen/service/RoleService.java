package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.model.Role;

import java.util.List;

public interface RoleService {
    Role add(Role role);
    List<Role> getByNames(List<String> names);

}
