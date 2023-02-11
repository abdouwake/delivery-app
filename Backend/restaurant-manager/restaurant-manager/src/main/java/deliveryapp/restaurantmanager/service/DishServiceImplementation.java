package deliveryapp.restaurantmanager.service;

import deliveryapp.restaurantmanager.Exceptions.ApiRequestException;
import deliveryapp.restaurantmanager.model.Dish;
import deliveryapp.restaurantmanager.model.DishType;
import deliveryapp.restaurantmanager.model.Restaurant;
import deliveryapp.restaurantmanager.repository.DishRepository;
import deliveryapp.restaurantmanager.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImplementation implements DishService{

    @Autowired
    DishRepository dishRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Dish createDish(Dish dish) {
        dish.setCreationDate(Today());
        dish.setUpdateDate(Today());
        return dishRepository.save(dish);
    }

    @Override
    public Dish updateDish(Dish dish) {
        if(!dishRepository.existsById(dish.getId())){
            throw new ApiRequestException("No matching records");
        }
        dish.setUpdateDate(Today());
        Optional<Dish> oldDish = dishRepository.findById(dish.getId());
        dish.setCreationDate(oldDish.get().getCreationDate());
        return dishRepository.save(dish);
    }

    @Override
    public void deleteDish(Integer id) {
        Dish dish = dishRepository.findById(id).orElseThrow(()-> new ApiRequestException("No matching dish."));
        dishRepository.deleteById(id);
    }

    @Override
    public Dish setAvailable(Integer id, Boolean available) {
        Dish dish = dishRepository.findById(id).orElseThrow(()-> new ApiRequestException("No matching restaurant."));
        dish.setAvailable(available);
        return dishRepository.save(dish);
    };

    @Override
    public List<Dish> listRestaurantDishes(Integer idRestaurant) {
        restaurantRepository.findById(idRestaurant).orElseThrow(()-> new ApiRequestException("No matching restaurant."));
        return restaurantRepository.findById(idRestaurant).get().getDish();
    }

    @Override
    public List<Dish> listRestaurantDishesByType(Integer idRestaurant, String dishType) {
        Restaurant restaurant = restaurantRepository.findById(idRestaurant).orElseThrow(()-> new ApiRequestException("No matching restaurant."));
        List<Dish>  dishes= restaurant.getDish().stream().filter(dish -> dish.getDishType()==dishType).toList();
        return dishes;
}

    @Override
    public List<Dish> listAvailableDishPerRestaurant(Integer idRestaurant, Boolean available) {
        List<Dish> dishes = listRestaurantDishes(idRestaurant);
        dishes= dishes.stream().filter(Dish::getAvailable).toList();
        return dishes;
    }

    @Override
    public List<Dish> ListByType(DishType type) {
        return dishRepository.findByDishType(type);
    }


    public Date Today(){
        // DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return(date);
    }

}
