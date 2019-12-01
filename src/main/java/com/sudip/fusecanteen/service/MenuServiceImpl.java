package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.dto.MenuDTO;
import com.sudip.fusecanteen.model.Food;
import com.sudip.fusecanteen.model.Menu;
import com.sudip.fusecanteen.model.User;
import com.sudip.fusecanteen.repository.MenuRepository;
import com.sudip.fusecanteen.service.mapper.FoodMapper;
import com.sudip.fusecanteen.service.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    private final FoodService foodService;
    private final UserService userService;
    private final MenuRepository menuRepository;
    private final MenuMapper mapper;

    @Autowired
    public MenuServiceImpl(final FoodService foodService
            , final UserService userService
            , final MenuRepository menuRepository
            , final MenuMapper mapper) {
        this.foodService = foodService;
        this.userService = userService;
        this.menuRepository = menuRepository;
        this.mapper = mapper;
    }

    @Override
    public Menu add(MenuDTO menuDTO) {
        List<Food> foods = foodService.getByNames(menuDTO.getFoodName());
        User user = userService.getByUsername(menuDTO.getUsername());
        Menu menu = mapper.convertToMenu(menuDTO, foods, user);
        menu.setDate(LocalDate.now());
        return menuRepository.save(menu);
    }

    @Override
    public Menu getByDate(String date) {
        return menuRepository.findByDate(LocalDate.parse(date))
                .orElseThrow(() -> new RuntimeException("Menu not found"));
    }
}
