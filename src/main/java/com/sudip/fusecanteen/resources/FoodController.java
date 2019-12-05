package com.sudip.fusecanteen.resources;

import com.sudip.fusecanteen.dto.FoodDTO;
import com.sudip.fusecanteen.model.Food;
import com.sudip.fusecanteen.model.User;
import com.sudip.fusecanteen.service.FoodService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sudip.fusecanteen.utils.ApiConstant.FOOD_API;

@RestController
@RequestMapping(FOOD_API)
public class FoodController {

    private final FoodService foodService;

    public FoodController(final FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public ResponseEntity<Food> add(@RequestBody FoodDTO foodDTO){
        Food food = foodService.add(foodDTO);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Food> update(@RequestBody FoodDTO foodDTO){
        Food food = foodService.update(foodDTO);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Food>> getAll(@PageableDefault(page = 0
            , size = 10
            , direction = Sort.Direction.DESC)
                                                     Pageable pageable){
        List<Food> foods = foodService.getAll(pageable);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

}
