package com.sudip.fusecanteen.resources;

import com.sudip.fusecanteen.dto.MenuDTO;
import com.sudip.fusecanteen.model.Menu;
import com.sudip.fusecanteen.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.sudip.fusecanteen.utils.ApiConstant.MENU_API;

@RestController
@RequestMapping(MENU_API)
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<Menu> getByDate(@RequestParam String date){
        Menu menu = menuService.getByDate(date);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Menu> add(@RequestBody MenuDTO menuDTO){
        Menu addedMenu = menuService.add(menuDTO);
        return new ResponseEntity<>(addedMenu, HttpStatus.OK);
    }
}
