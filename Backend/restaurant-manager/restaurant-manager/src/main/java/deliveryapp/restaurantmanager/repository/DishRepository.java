package deliveryapp.restaurantmanager.repository;

import deliveryapp.restaurantmanager.model.Dish;
import deliveryapp.restaurantmanager.model.DishType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish,Integer> {

    public List<Dish> findByName(String name);
    public Optional<Dish> findById(Integer id);
    public List<Dish> findByDishType(DishType type);

}
