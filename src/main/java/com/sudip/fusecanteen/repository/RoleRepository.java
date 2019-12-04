package com.sudip.fusecanteen.repository;

import com.sudip.fusecanteen.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    List<Role> findByNameIn(List<String> names);
}
