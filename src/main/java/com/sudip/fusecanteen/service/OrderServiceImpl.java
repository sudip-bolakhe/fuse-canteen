package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.dto.OrderDTO;
import com.sudip.fusecanteen.model.Food;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        order.setAmount(getBill(orderItems));
        return order;
    }

    double getBill(List<OrderItem> orderItems){
        double totalBill = orderItems.stream().mapToDouble(orderItem -> orderItem.getQuantity() * orderItem.getFood().getPrice()).sum();
        return totalBill;
    }
    @Override
    public List<Order> getByUsernameAndDate(String username, String startDate, String endDate) {
        User user = userService.getByUsername(username);
        user.setRoles(null);
        List<Order> orders = orderRepository.findByUserAndDateBetween(user.getId()
                , LocalDate.parse(startDate)
                , LocalDate.parse(endDate));
        return orders;
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

    @Override
    public Order update(OrderDTO orderDTO) {
        Order order= orderRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderItems(getNewData(orderDTO, order));
        order.setOrderItems(order.getOrderItems());
       return orderRepository.save(order);
    }

    private List<OrderItem> getNewData(OrderDTO orderDTO, Order order) {
        List<OrderItem> orderItems = order.getOrderItems();
        Map<String, Long> foodQuantity = orderDTO.getFoodQuantity();
        List<OrderItem> removedItem = new ArrayList<>();
        List<OrderItem> updatedItem = new ArrayList<>();
        List<OrderItem> newOrderItems = new ArrayList<>();
        orderItems.forEach(orderItem -> {
            if(foodQuantity.containsKey(orderItem.getFood().getName())){
                if(!foodQuantity.get(orderItem.getFood().getName()).equals(orderItem.getQuantity())){
                    updatedItem.add(orderItem);
                }else {
                    newOrderItems.add(orderItem);
                }

            }
            removedItem.add(orderItem);
        });
            if(!removedItem.isEmpty()){
                orderItems.removeAll(removedItem);
                List<String> ids = removedItem.stream().map(OrderItem::getId).collect(Collectors.toList());
                orderItemService.deleteByIds(ids);
            }
            orderItemService.update(orderItems);
            newOrderItems.addAll(updatedItem);
            return newOrderItems;
    }
}
