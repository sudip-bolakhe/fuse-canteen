package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.dto.OrderDTO;
import com.sudip.fusecanteen.model.Food;
import com.sudip.fusecanteen.model.Order;
import com.sudip.fusecanteen.model.User;
import com.sudip.fusecanteen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final FoodService foodService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(final UserService userService
            , final FoodService foodService
            , final OrderRepository orderRepository) {
        this.userService = userService;
        this.foodService = foodService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order add(OrderDTO orderDTO) {
        User user = userService.getByUsername(orderDTO.getUsername());
        Map<Food, Long> foods ;
        return null;
    }

    @Override
    public List<Order> getByUsernameAndDate(String username, String startDate, String endDate) {
        return null;
    }
}
