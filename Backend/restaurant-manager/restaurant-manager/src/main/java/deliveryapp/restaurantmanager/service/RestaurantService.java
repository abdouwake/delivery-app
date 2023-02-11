package deliveryapp.restaurantmanager.service;

import deliveryapp.restaurantmanager.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(Restaurant restaurant);
    public Restaurant updateRestaurant(Restaurant restaurant);
    public void deleteRestaurant(Integer id);
    public void setSuspended(Integer id, Boolean suspended);
    public Boolean existsById(Integer id);
    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> findByApproximateName(String name );
}
