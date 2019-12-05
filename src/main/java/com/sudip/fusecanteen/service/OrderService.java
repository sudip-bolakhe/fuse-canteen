package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.dto.OrderDTO;
import com.sudip.fusecanteen.model.Order;
import com.sudip.fusecanteen.utils.OrderStatus;

import java.util.List;

public interface OrderService {
    Order add(OrderDTO orderDTO);
    List<Order> getByUsernameAndDate(String username, String startDate, String endDate);
    Order updateStatus(String id, OrderStatus orderStatus);
    List<Order> getByDateAndType(String date, String type);
    Order update(OrderDTO orderDTO);
}
