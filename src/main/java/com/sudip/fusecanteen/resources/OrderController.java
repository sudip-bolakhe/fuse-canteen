package com.sudip.fusecanteen.resources;

import com.sudip.fusecanteen.dto.OrderDTO;
import com.sudip.fusecanteen.model.Order;
import com.sudip.fusecanteen.service.OrderService;
import com.sudip.fusecanteen.utils.FoodOrderType;
import com.sudip.fusecanteen.utils.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity<Order>  add(@RequestBody OrderDTO orderDTO){
        Order addedOrder = orderService.add(orderDTO);
        return new ResponseEntity<>(addedOrder, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity<List<Order>>  getAll(@PathVariable String username
            , @RequestParam String startDate, @RequestParam String endDate){
        List<Order> orders = orderService.getByUsernameAndDate(username, startDate, endDate);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Order> updateStatus(@PathVariable String id, @RequestParam OrderStatus orderStatus){
        Order order = orderService.updateStatus(id, orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<Order>> getByDateAndType(@RequestParam String date
            , @PathVariable FoodOrderType type){
        List<Order> orders = orderService.getByDateAndType(date, type.toString());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
