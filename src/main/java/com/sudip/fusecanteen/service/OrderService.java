package com.sudip.fusecanteen.service;

import com.sudip.fusecanteen.dto.OrderDTO;
import com.sudip.fusecanteen.model.Order;

import java.util.List;

public interface OrderService {
    Order add(OrderDTO orderDTO);
    List<Order> getByUsernameAndDate(String username, String startDate, String endDate);
}
