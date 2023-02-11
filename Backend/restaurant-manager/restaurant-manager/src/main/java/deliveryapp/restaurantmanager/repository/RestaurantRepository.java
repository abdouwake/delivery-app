package deliveryapp.restaurantmanager.repository;

import deliveryapp.restaurantmanager.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    public Optional<Restaurant> findByName(String name);
    public Boolean existsByName(String name);

    public Optional<Restaurant> findById(Integer id);
    public List<Restaurant> findByIdOwner(Integer idOwner);
    public List<Restaurant> findByFoodType(String foodType);
    public List<Restaurant> findBySuspended(Boolean suspended);

}
