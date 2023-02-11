package deliveryapp.reviewsmanager.Service;

import deliveryapp.reviewsmanager.Model.Review;
import deliveryapp.reviewsmanager.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService{

    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<Review> findReviewsById(Integer id) {
        return reviewRepository.findByIdEntity(id);
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Integer id) {
         reviewRepository.deleteById(id);
    }

    @Override
    public List<Review> findByRange(Integer idEntity,Integer min, Integer max) {
        return findReviewsById(idEntity).stream().filter(r-> r.getNote()>min && r.getNote()<max).toList();
    }
}
