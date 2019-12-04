package com.sudip.fusecanteen.service.mapper;

import com.sudip.fusecanteen.dto.FoodDTO;
import com.sudip.fusecanteen.model.Food;
import com.sudip.fusecanteen.model.User;
import org.springframework.stereotype.Service;

@Service
public class FoodMapper {

    public Food convertToFood(FoodDTO foodDTO, User user){
        Food food = new Food();
        food.setName(foodDTO.getName());
        food.setQuantity(foodDTO.getQuantity());
        food.setPrice(foodDTO.getPrice());
        food.setStatus(foodDTO.isStatus());
        food.setCreatedBy(user);
        return food;
    }
}
