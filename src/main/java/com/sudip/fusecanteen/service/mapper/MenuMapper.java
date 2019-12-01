package com.sudip.fusecanteen.service.mapper;

import com.sudip.fusecanteen.dto.MenuDTO;
import com.sudip.fusecanteen.model.Food;
import com.sudip.fusecanteen.model.Menu;
import com.sudip.fusecanteen.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuMapper {

    public Menu convertToMenu(MenuDTO menuDTO, List<Food> foods, User user){
        Menu menu = new Menu();
        menu.setFoods(foods);
        menu.setCreatedBy(user);
        return menu;
    }
}
