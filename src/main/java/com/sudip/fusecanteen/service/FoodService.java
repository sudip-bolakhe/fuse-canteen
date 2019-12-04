package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.dto.FoodDTO;
import com.sudip.fusecanteen.model.Food;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FoodService {

    Food add(FoodDTO food);

    List<Food> getAll(Pageable pageable);

    Food update(FoodDTO food);

    List<Food> getByNames(List<String> names);
}
