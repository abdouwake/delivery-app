package deliveryapp.ordermanager.Controller;

import deliveryapp.ordermanager.Exception.ApiRequestException;
import deliveryapp.ordermanager.Model.Order;
import deliveryapp.ordermanager.Repository.OrderRepository;
import deliveryapp.ordermanager.ResponseEntity.ResponseEntity;
import deliveryapp.ordermanager.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;


    @PostMapping("/add")
    public org.springframework.http.ResponseEntity<Object> add(@RequestBody Order order){
        try{
            Order createdOrder  = orderService.Create(order);
            return  ResponseEntity.generateResponse("The order is created.", HttpStatus.CREATED,createdOrder);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }


    @PutMapping("/update")
    public org.springframework.http.ResponseEntity<Object> update(@RequestBody Order order){
        try{
            if(!orderRepository.existsById(order.getId())){
                throw new ApiRequestException("No matching records");
            }
            Order updatedOrder = orderService.Update(order);
            return  ResponseEntity.generateResponse("The order is updated.", HttpStatus.OK,updatedOrder);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public org.springframework.http.ResponseEntity<Object> delete(@PathVariable Integer id){
        try{
            if(!orderRepository.existsById(id)){
                throw new ApiRequestException("No matching records");
            }
            orderService.delete(id);
            return  ResponseEntity.generateResponse("The order is deleted.", HttpStatus.OK,null);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @GetMapping("/list/{idUser}")
    public org.springframework.http.ResponseEntity<Object> listByUser(@PathVariable Integer idUser){
        if(!orderRepository.existsByIdUser(idUser)){
            throw new ApiRequestException("No matching records");
        }
        return  ResponseEntity.generateResponse("List all orders of the user : ",HttpStatus.OK,orderRepository.findByIdUser(idUser));
    }

    @GetMapping("/find/{idOrder}")
    public org.springframework.http.ResponseEntity<Object> findByIdOrder(@PathVariable Integer idOrder){
        if(!orderRepository.existsById(idOrder)){
            throw new ApiRequestException("No matching records");
        }
        return  ResponseEntity.generateResponse("List all orders of the user : ",HttpStatus.OK,orderRepository.findById(idOrder));
    }

    @GetMapping("/list/Restaurant/{idRestaurant}")
    public org.springframework.http.ResponseEntity<Object> listByRestaurant(@PathVariable Integer idRestaurant){
          try{
              return  ResponseEntity.generateResponse("List all orders of the restaurant",HttpStatus.OK,orderRepository.findByIdRestaurant(idRestaurant));
          }catch (Exception e){
              throw new ApiRequestException(e.getMessage());

          }
    }

}
