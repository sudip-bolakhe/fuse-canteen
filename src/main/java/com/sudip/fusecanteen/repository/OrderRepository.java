package com.sudip.fusecanteen.repository;

import com.sudip.fusecanteen.model.Order;
import com.sudip.fusecanteen.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
