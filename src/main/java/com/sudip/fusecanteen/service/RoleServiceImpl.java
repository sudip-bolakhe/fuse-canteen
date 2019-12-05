package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.model.Role;
import com.sudip.fusecanteen.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role add(Role role) {
      return   roleRepository.save(role);
    }

    @Override
    public List<Role> getByNames(List<String> names) {
        List<Role> roles = roleRepository.findByNameIn(names);
        if(roles.isEmpty())
            throw new RuntimeException("Roles Not found");
        return roles;
    }
}
