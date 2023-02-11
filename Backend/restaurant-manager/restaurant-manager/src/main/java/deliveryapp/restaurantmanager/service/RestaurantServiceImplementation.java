package deliveryapp.restaurantmanager.service;

import deliveryapp.restaurantmanager.Exceptions.ApiRequestException;
import deliveryapp.restaurantmanager.model.Restaurant;
import deliveryapp.restaurantmanager.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImplementation implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        try{
            if(findByName(restaurant.getName()).isPresent()){
                restaurant.setCreationDate(Today());
                restaurant.setLastUpdate(Today());
                throw new ApiRequestException("this name is already attributed to a restaurant.");
            }
            return restaurantRepository.save(restaurant);
        }catch(Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        Optional<Restaurant> existing = findByName(restaurant.getName());
        if(findByName(existing.get().getName()).isPresent() && existing.get().getId()!=restaurant.getId()){
            throw new ApiRequestException("this name is already attributed to a restaurant.");
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Integer id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()-> new ApiRequestException("No matching restaurant."));
        restaurantRepository.deleteById(id);
    }

    @Override
    public void setSuspended(Integer id,Boolean suspended) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()-> new ApiRequestException("No matching restaurant."));
        restaurant.setSuspended(suspended);
        restaurantRepository.save(restaurant);
    }

    @Override
    public Boolean existsById(Integer id) {
        return restaurantRepository.existsById(id);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }


    public Optional<Restaurant> findByName(String name){
        return restaurantRepository.findByName(name);
    }


    public List<Restaurant> findByApproximateName(String name){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return  restaurants.stream().filter(r->r.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }


    public Optional<Restaurant> findById(Integer id){
        return restaurantRepository.findById(id);
    }

    public List<Restaurant> findByIdOwner(Integer idOwner){
        return restaurantRepository.findByIdOwner(idOwner);
    }

    public List<Restaurant> findByFoodType(String foodType){
        return  restaurantRepository.findByFoodType(foodType);
    }

    public List<Restaurant> findBySuspended(Boolean suspended){
        return restaurantRepository.findBySuspended(suspended);
    }

    public Date Today(){
       // DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return(date);
    }




}
