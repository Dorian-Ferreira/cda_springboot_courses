package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.ReviewDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Review;
import fr.human_booster.dorian_ferreira.printemps.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private UserService userService;
    private LodgingService lodgingService;

    public Review create(ReviewDTO dto) {
        Review review = new Review();

        review.setCreatedAt(LocalDateTime.now());

        review.setRating(dto.getRating());
        review.setContent(dto.getContent());

        review.setUser(userService.findById(dto.getUserUuid()));
        review.setLodging(lodgingService.findById(dto.getLodgingUuid()));

        return reviewRepository.saveAndFlush(review);
    }

    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow();
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}
