package deliveryapp.reviewsmanager.Repository;

import deliveryapp.reviewsmanager.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    public List<Review> findByIdEntity(Integer id);
    public boolean existsByIdEntity(Integer id);
}
