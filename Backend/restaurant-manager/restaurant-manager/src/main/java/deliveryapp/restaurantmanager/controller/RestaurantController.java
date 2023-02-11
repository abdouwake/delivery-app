package deliveryapp.restaurantmanager.controller;

import deliveryapp.restaurantmanager.Exceptions.ApiRequestException;
import deliveryapp.restaurantmanager.ResponseEntity.ResponseEntity;
import deliveryapp.restaurantmanager.dto.SuspendRequestDto;
import deliveryapp.restaurantmanager.dto.UserDto;
import deliveryapp.restaurantmanager.model.Restaurant;
import deliveryapp.restaurantmanager.repository.RestaurantRepository;
import deliveryapp.restaurantmanager.service.RestaurantService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RestaurantRepository restaurantRepository;
    private UserDto userDto=new UserDto();
    private Restaurant restaurant=new Restaurant();

    @PostMapping("/add")
    public org.springframework.http.ResponseEntity<Object> add(@RequestBody Restaurant restaurant, @RequestHeader Map<String, String> headers){
        try{
            String token = headers.get("authorization").replace("Bearer ","");
            userDto = userDto.getUserDto(token);
            restaurant.setIdOwner(userDto.getIdUser());
            Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
            return  ResponseEntity.generateResponse("The restaurant is created.", HttpStatus.CREATED,createdRestaurant);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PutMapping("/update")
    public org.springframework.http.ResponseEntity<Object> update(@RequestBody Restaurant restaurant){
        try{
            Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurant);
            return  ResponseEntity.generateResponse("The restaurant is updated.", HttpStatus.OK,updatedRestaurant);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public org.springframework.http.ResponseEntity<Object> delete(@RequestBody Restaurant restaurant){
        try{
            restaurantService.deleteRestaurant(restaurant.getId());
            return  ResponseEntity.generateResponse("The restaurant is deleted.", HttpStatus.OK,null);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/get/all")
    public org.springframework.http.ResponseEntity<Object> getAll(){
        try{
            return  ResponseEntity.generateResponse("List of all restaurants.", HttpStatus.OK,restaurantService.getAllRestaurants());
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/byid/{id}")
    public org.springframework.http.ResponseEntity<Object> getById(@PathVariable Integer id){
        if(restaurantService.existsById(id)){
            return  ResponseEntity.generateResponse("Compiled successfully.", HttpStatus.OK,restaurantRepository.findById(id));

        }else{
            throw new ApiRequestException("Non existing restaurant");
        }
    }

    @GetMapping("/get/owner/{id}")
    public org.springframework.http.ResponseEntity<Object> getByOwner(@PathVariable("id") Integer id){
       return ResponseEntity.generateResponse("Compiled successfully.", HttpStatus.OK,restaurantRepository.findByIdOwner(id));
    }


    @GetMapping("/find/{name}")
    public org.springframework.http.ResponseEntity<Object> findByName(@PathVariable("name") String name){
            return ResponseEntity.generateResponse("Compiled successfully.", HttpStatus.OK,restaurantService.findByApproximateName(name));
    }

    @GetMapping("/all/type/{type}")
    public org.springframework.http.ResponseEntity<Object> findByType(@PathVariable("type") String type){
          return ResponseEntity.generateResponse("Compiled successfully.", HttpStatus.OK,restaurantRepository.findByFoodType(type));
    }

    @PostMapping("/suspend")
    public org.springframework.http.ResponseEntity<Object> suspend(@RequestBody @NotNull SuspendRequestDto requestDto){
        if(restaurantService.existsById(requestDto.getIdRestaurant())){
            restaurantService.setSuspended(requestDto.getIdRestaurant(),requestDto.getSuspended());
        }else {
            throw new ApiRequestException("Non existing restaurant");
        }
        return  ResponseEntity.generateResponse("Updated successfully", HttpStatus.OK,restaurantRepository.findById(requestDto.getIdRestaurant()));
    }

}
