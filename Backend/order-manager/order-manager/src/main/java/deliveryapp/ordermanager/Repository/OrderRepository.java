package deliveryapp.ordermanager.Repository;

import deliveryapp.ordermanager.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    public List<Order> findByIdUser(Integer id);
    public List<Order> findByIdRestaurant(Integer idRestaurant);
    public boolean existsByIdUser(Integer idRestaurant);

    public List<Order> findByOrderStatus(Order.OrderStatus status);
}
