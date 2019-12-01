package com.sudip.fusecanteen.repository;

import com.sudip.fusecanteen.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<Food, String > {
}
