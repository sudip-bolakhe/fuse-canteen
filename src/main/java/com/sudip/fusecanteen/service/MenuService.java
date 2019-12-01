package com.sudip.fusecanteen.service;


import com.sudip.fusecanteen.dto.MenuDTO;
import com.sudip.fusecanteen.model.Menu;

public interface MenuService {
    Menu add(MenuDTO menuDTO);

    Menu getByDate(String date);
}
