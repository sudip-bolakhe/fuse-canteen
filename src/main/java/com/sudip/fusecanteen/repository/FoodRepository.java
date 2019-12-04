package com.sudip.fusecanteen.repository;

import com.sudip.fusecanteen.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FoodRepository extends MongoRepository<Food, String > {
    List<Food> findByNameIn(List<String> names);
}
