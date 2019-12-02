package com.sudip.fusecanteen.repository;

import com.sudip.fusecanteen.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUser_usernameAndDateBetween(String username, LocalDate startDate, LocalDate endDate);
}
