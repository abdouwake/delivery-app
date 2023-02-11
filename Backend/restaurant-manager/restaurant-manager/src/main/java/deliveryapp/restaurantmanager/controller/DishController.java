package deliveryapp.restaurantmanager.controller;


import deliveryapp.restaurantmanager.Exceptions.ApiRequestException;
import deliveryapp.restaurantmanager.ResponseEntity.ResponseEntity;
import deliveryapp.restaurantmanager.model.Dish;
import deliveryapp.restaurantmanager.model.DishType;
import deliveryapp.restaurantmanager.repository.DishRepository;
import deliveryapp.restaurantmanager.service.DishService;
import deliveryapp.restaurantmanager.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/dish")
public class DishController {
    @Autowired
    DishService dishService;
    @Autowired
    DishRepository dishRepository;
    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/add")
    public org.springframework.http.ResponseEntity<Object> add(@RequestBody Dish dish){
        try{
            Dish createdDish  = dishService.createDish(dish);
            return  ResponseEntity.generateResponse("The dish is created.", HttpStatus.CREATED,createdDish);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @PutMapping("/update")
    public org.springframework.http.ResponseEntity<Object> update(@RequestBody Dish dish){
        try{
            if(!dishRepository.existsById(dish.getId())){
                throw new ApiRequestException("No matching records");
            }
            Dish updatedDish = dishService.updateDish(dish);
            return  ResponseEntity.generateResponse("The dish is updated.", HttpStatus.OK,updatedDish);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public org.springframework.http.ResponseEntity<Object> delete(@PathVariable Integer id){
        try{
            if(!dishRepository.existsById(id)){
                throw new ApiRequestException("No matching records");
            }
            dishService.deleteDish(id);
            return  ResponseEntity.generateResponse("The dish is deleted.", HttpStatus.OK,null);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/list/{idRestaurant}")
    public org.springframework.http.ResponseEntity<Object> getAll(@PathVariable Integer idRestaurant){
        if(!restaurantService.existsById(idRestaurant)){
            throw new ApiRequestException("No matching records");
        }
        return  ResponseEntity.generateResponse("List all dishes of this restaurant : "+idRestaurant, HttpStatus.OK,dishService.listRestaurantDishes(idRestaurant));
    }

    @GetMapping("/list/type/{dishType}")
    public org.springframework.http.ResponseEntity<Object> getByType(@PathVariable("dishType")  String dishType ){

        return  ResponseEntity.generateResponse("List all dishes with type : "+dishType, HttpStatus.OK,dishService.ListByType(DishType.valueOf(dishType)));
    }

    @GetMapping("/list/{idRestaurant}/{type}")
    public org.springframework.http.ResponseEntity<Object> getByRestByType(@PathVariable Integer idRestaurant,@PathVariable String type){
        return  ResponseEntity.generateResponse("List all "+type+" of this restaurant : "+idRestaurant, HttpStatus.OK,dishService.listRestaurantDishesByType(idRestaurant,type));
    }

    @PostMapping("/set/available/{idDish}/{available}")
    public org.springframework.http.ResponseEntity<Object> suspend(@PathVariable Integer idDish,@PathVariable Boolean available){
        Dish dish = dishRepository.findById(idDish).orElseThrow(()-> new ApiRequestException("Dish not found."));
        dish.setAvailable(available);
        dishService.updateDish(dish);
        return  ResponseEntity.generateResponse("Updated successfully", HttpStatus.OK,dish);
    }
}
