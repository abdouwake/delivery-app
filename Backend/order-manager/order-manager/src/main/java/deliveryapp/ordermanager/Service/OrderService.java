package deliveryapp.ordermanager.Service;

import deliveryapp.ordermanager.Model.Order;

public interface OrderService {
    public Order Create(Order order);
    public Order Update(Order order);
    public void delete(Integer id);
}
