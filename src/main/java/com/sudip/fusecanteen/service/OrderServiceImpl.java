package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.dto.OrderDTO;
import com.sudip.fusecanteen.model.Order;
import com.sudip.fusecanteen.model.OrderItem;
import com.sudip.fusecanteen.model.User;
import com.sudip.fusecanteen.repository.OrderRepository;
import com.sudip.fusecanteen.utils.FoodOrderType;
import com.sudip.fusecanteen.utils.OrderStatus;
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
        order.setStatus(OrderStatus.PENDING.toString());
        order.setDate(LocalDate.now());
        order.setType(checkEnum(orderDTO.getType()).toUpperCase());
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
        User user = userService.getByUsername(username);
        return orderRepository.findByUserAndDateBetween(user
                , LocalDate.parse(startDate)
                , LocalDate.parse(endDate));
    }

    @Override
    public Order updateStatus(String id, OrderStatus orderStatus) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(orderStatus.toString());
        orderRepository.save(order);
        return order;
    }

    private String checkEnum(String orderType){
        if(FoodOrderType.contains(orderType)){
            return orderType;
        }
        throw new RuntimeException("Invalid Enum");
    }

    @Override
    public List<Order> getByDateAndType(String date, String type) {
       return orderRepository.findByDateAndType(LocalDate.parse(date),type);
    }
}
