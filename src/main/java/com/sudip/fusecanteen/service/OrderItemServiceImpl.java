package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.model.Food;
import com.sudip.fusecanteen.model.OrderItem;
import com.sudip.fusecanteen.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final FoodService foodService;

    public OrderItemServiceImpl(final OrderItemRepository orderItemRepository
            , final FoodService foodService) {
        this.orderItemRepository = orderItemRepository;
        this.foodService = foodService;
    }

    @Override
    public List<OrderItem> addAll(Map<String, Long> foodQuantity) {
        return orderItemRepository.saveAll(getOrderItems(foodQuantity));
    }

    private List<OrderItem> getOrderItems(Map<String, Long> foodQuantity) {
       List<Food> foods = foodService.getByNames(new ArrayList<>(foodQuantity.keySet()));
        List<OrderItem> orderItems = new ArrayList<>();
        for (Map.Entry<String, Long> foodItem: foodQuantity.entrySet()) {
            orderItems = foods.stream().map(food -> {
                if (food.getName().equals(foodItem.getKey())) {
                    return new OrderItem(food, foodItem.getValue());
                }
               else {
                   throw new RuntimeException("Unexpected Behaviour");
                }
            }).collect(Collectors.toList());
        }
        return orderItems;
    }

    @Override
    public void deleteByIds(List<String> ids) {
        orderItemRepository.deleteAll(orderItemRepository.findAllById(ids));
    }

    @Override
    public List<OrderItem> update(List<OrderItem> orderItems) {
        return orderItemRepository.saveAll(orderItems);
    }
}
