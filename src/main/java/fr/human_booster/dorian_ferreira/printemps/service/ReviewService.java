package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.ReviewDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Review;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
import fr.human_booster.dorian_ferreira.printemps.repository.ReviewRepository;
import fr.human_booster.dorian_ferreira.printemps.service.interfaces.ServiceDtoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService implements ServiceDtoInterface<Review, ReviewDTO> {

    private ReviewRepository reviewRepository;
    private UserService userService;
    private LodgingService lodgingService;

    public Review create(ReviewDTO dto) {
        Review review = dtoToObject(dto, new Review());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.saveAndFlush(review);
    }

    public Review update(ReviewDTO dto, Long id) {
        Review review = dtoToObject(dto, findById(id));
        review.setUpdatedAt(LocalDateTime.now());

        return reviewRepository.saveAndFlush(review);
    }

    public Review createInit(ReviewDTO dto) {
        Review review = dtoToObject(dto, new Review());
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    public void flush() {
        reviewRepository.flush();
    }

    @Override
    public  Review dtoToObject(ReviewDTO reviewDTO, Review review) {
        try {

            review.setRating(reviewDTO.getRating());
            review.setContent(reviewDTO.getContent());

            review.setUser(userService.findById(reviewDTO.getUserUuid()));
            review.setLodging(lodgingService.findById(reviewDTO.getLodgingUuid()));

            return review;
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public void delete(Long id) {
        Review review = findById(id);

        review.setContent("Deleted");
        review.setDeletedAt(LocalDateTime.now());

        reviewRepository.saveAndFlush(review);
    }

    public Review findById(Long id) throws EntityNotFoundException {
        return reviewRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Review"));
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public long count() {
        return reviewRepository.count();
    }
}
