package org.example.springbootdemo.services;

import org.example.springbootdemo.entity.Order;
import org.example.springbootdemo.entity.OrderItem;
import org.example.springbootdemo.models.request.OrderRequest;
import org.example.springbootdemo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setTotalMoney(0L);
        List<OrderItem> orderItems = orderRequest.getItems().stream().map(
                item->{
                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(item.getPrice());
                    orderItem.setProductId(item.getProductId());
                    order.setTotalMoney(order.getTotalMoney() + item.getPrice() * orderItem.getQuantity());
                    orderItem.setOrder(order);
                    return orderItem;
                }).toList();
        order.setItems(orderItems);
        order.setCreatedAt(new Date());
        return orderRepository.save(order);
    }
}
