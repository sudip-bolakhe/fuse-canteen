package com.sudip.fusecanteen.resources;

import com.sudip.fusecanteen.dto.OrderDTO;
import com.sudip.fusecanteen.model.Order;
import com.sudip.fusecanteen.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @PreAuthorize("hasRole('User')")
    public String  add(@RequestBody OrderDTO orderDTO){
        orderService.add(orderDTO);
        return orderDTO.toString();
    }
}
