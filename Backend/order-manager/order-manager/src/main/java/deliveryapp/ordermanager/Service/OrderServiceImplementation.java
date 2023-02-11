package deliveryapp.ordermanager.Service;

import deliveryapp.ordermanager.Model.Order;
import deliveryapp.ordermanager.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImplementation implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order Create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order Update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Integer id) {
        orderRepository.deleteById(id);
    }
}
