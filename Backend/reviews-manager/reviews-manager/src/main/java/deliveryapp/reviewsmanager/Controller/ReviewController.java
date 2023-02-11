package deliveryapp.reviewsmanager.Controller;

import deliveryapp.reviewsmanager.Exception.ApiRequestException;
import deliveryapp.reviewsmanager.Model.Review;
import deliveryapp.reviewsmanager.Repository.ReviewRepository;
import deliveryapp.reviewsmanager.ResponseEntity.ResponseEntity;
import deliveryapp.reviewsmanager.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;


    @PostMapping("/add")
    public org.springframework.http.ResponseEntity<Object> add(@RequestBody Review review){
        try{
            Review createdOrder  = reviewService.addReview(review);
            return  ResponseEntity.generateResponse("The review is published.", HttpStatus.CREATED,createdOrder);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }


    @PutMapping("/update")
    public org.springframework.http.ResponseEntity<Object> update(@RequestBody Review review){
        try{
            if(!reviewRepository.existsById(review.getId())){
                throw new ApiRequestException("No matching records");
            }
            Review updatedOrder = reviewService.updateReview(review);
            return  ResponseEntity.generateResponse("The review is updated.", HttpStatus.OK,updatedOrder);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public org.springframework.http.ResponseEntity<Object> delete(@PathVariable Integer id){
        try{
            if(!reviewRepository.existsById(id)){
                throw new ApiRequestException("No matching records");
            }
            reviewService.deleteReview(id);
            return  ResponseEntity.generateResponse("The review is deleted.", HttpStatus.OK,null);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }


    @GetMapping("/list/{idEntity}")
    public org.springframework.http.ResponseEntity<Object> listByEntity(@PathVariable Integer idEntity){
        if(!reviewRepository.existsByIdEntity(idEntity)){
            throw new ApiRequestException("No matching records");
        }
        return  ResponseEntity.generateResponse("List all reviews for the entity: "+idEntity,HttpStatus.OK,reviewRepository.findByIdEntity(idEntity));
    }

    @GetMapping("/list/filter/{idEntity}/{min}/{max}")
    public org.springframework.http.ResponseEntity<Object> listByNote(@PathVariable Integer idEntity,@PathVariable Integer min,@PathVariable Integer max){
        if(!reviewRepository.existsByIdEntity(idEntity)){
            throw new ApiRequestException("No matching records");
        }
        return  ResponseEntity.generateResponse("List all reviews for the entity: "+idEntity,HttpStatus.OK,reviewService.findByRange(idEntity,min,max));
    }


}
