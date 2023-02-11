package deliveryapp.restaurantmanager.service;

import deliveryapp.restaurantmanager.model.Dish;
import deliveryapp.restaurantmanager.model.DishType;

import java.util.List;

public interface DishService {
    public Dish createDish(Dish dish);
    public Dish updateDish(Dish dish);
    public void deleteDish(Integer id);
    public Dish setAvailable(Integer id, Boolean available);
    public List<Dish> listRestaurantDishes(Integer idRestaurant);
    List<Dish> listRestaurantDishesByType(Integer idRestaurant, String dishType);

    public List<Dish> listAvailableDishPerRestaurant(Integer idRestaurant, Boolean available);
    public List<Dish> ListByType(DishType type);
}
