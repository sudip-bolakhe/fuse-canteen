package com.sudip.fusecanteen.repository;

import com.sudip.fusecanteen.model.Order;
import com.sudip.fusecanteen.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{'user' :{?0}, 'date' : { $gte: ?1, $lte: ?2 } }")
    List<Order> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);

    List<Order> findByDateAndType(LocalDate date, String type);
}
