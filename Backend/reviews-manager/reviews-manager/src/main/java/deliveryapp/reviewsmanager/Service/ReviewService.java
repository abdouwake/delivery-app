package deliveryapp.reviewsmanager.Service;

import deliveryapp.reviewsmanager.Model.Review;

import java.util.List;

public interface ReviewService {

    public List<Review> findReviewsById(Integer id);
    public Review  addReview(Review review);
    public Review updateReview(Review review);
    public void deleteReview(Integer id);
    public List<Review> findByRange(Integer idEntity,Integer min,Integer max);
}
