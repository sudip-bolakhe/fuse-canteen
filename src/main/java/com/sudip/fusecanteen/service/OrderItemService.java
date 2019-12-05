package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.model.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderItemService {
    List<OrderItem> addAll(Map<String, Long> foodQuantity);
    void deleteByIds(List<String> ids);
    List<OrderItem> update(List<OrderItem> orderItems);
}
