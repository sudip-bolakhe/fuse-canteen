package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.dto.FoodDTO;
import com.sudip.fusecanteen.model.Food;
import com.sudip.fusecanteen.model.User;
import com.sudip.fusecanteen.repository.FoodRepository;
import com.sudip.fusecanteen.service.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {

    private final UserService userService;

    private final FoodRepository foodRepository;

    private final FoodMapper foodMapper;

    @Autowired
    public FoodServiceImpl(final UserService userService
            , final FoodRepository foodRepository
            , final FoodMapper foodMapper) {
        this.userService = userService;
        this.foodRepository = foodRepository;
        this.foodMapper = foodMapper;
    }

    @Override
    public Food add(FoodDTO foodDTO) {
        User user = userService.getByUsername(foodDTO.getUsername());
        Food food = foodMapper.convertToFood(foodDTO, user);
        food.setLastOrderedDate(LocalDate.now());
        return foodRepository.save(food);
    }

    @Override
    public List<Food> getAll(Pageable pageable) {
        return foodRepository.findAll(pageable).getContent();
    }

    @Override
    public Food update(FoodDTO foodDTO) {
        User user = userService.getByUsername(foodDTO.getUsername());
        Food food = foodMapper.convertToFood(foodDTO, user);
        return foodRepository.save(food);
    }

    @Override
    public List<Food> getByNames(List<String> names) {
        return foodRepository.findByNameIn(names);
    }
}
