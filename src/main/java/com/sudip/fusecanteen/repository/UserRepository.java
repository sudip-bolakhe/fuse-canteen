package com.sudip.fusecanteen.repository;

import com.sudip.fusecanteen.model.User;
import org.graalvm.compiler.lir.LIRInstruction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
