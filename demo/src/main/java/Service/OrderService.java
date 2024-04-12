package Service;

import Entity.Order;
import Entity.Product;
import Entity.User;
import Repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrders(Order order) {
        return orderRepository.save(order);
    }


}
