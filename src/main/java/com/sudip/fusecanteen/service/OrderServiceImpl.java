package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.dto.OrderDTO;
import com.sudip.fusecanteen.model.Order;
import com.sudip.fusecanteen.model.OrderItem;
import com.sudip.fusecanteen.model.User;
import com.sudip.fusecanteen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(final UserService userService
            , final OrderItemService orderItemService
            , final OrderRepository orderRepository) {
        this.userService = userService;
        this.orderItemService = orderItemService;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order add(OrderDTO orderDTO) {
        User user = userService.getByUsername(orderDTO.getUsername());
        Order order = getOrderAmount(orderDTO.getFoodQuantity());
        order.setUser(user);
        order.setDate(LocalDate.now());
        return orderRepository.save(order);
    }

    private Order getOrderAmount(Map<String, Long > orderQuantity) {
        Order order = new Order();
        List<OrderItem> orderItems = orderItemService.addAll(orderQuantity);
        order.setOrderItems(orderItems);
        double totalBill = orderItems.stream().mapToDouble(orderItem -> orderItem.getQuantity() * orderItem.getFood().getPrice()).sum();
        order.setAmount(totalBill);
        return order;
    }

    @Override
    public List<Order> getByUsernameAndDate(String username, String startDate, String endDate) {
        return orderRepository.findByUser_usernameAndDateBetween(username
                , LocalDate.parse(startDate)
                , LocalDate.parse(endDate));
    }
}
