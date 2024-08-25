package com.app.LMS.services;

import com.app.LMS.dto.DtoReview;
import com.app.LMS.entity.Review;
import com.app.LMS.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;


   public void save(Review review) {
       reviewRepository.save(review);
   }

    public List<DtoReview> getAllReviews() {
        return convertToDto(reviewRepository.findAll());
    }

    public List<DtoReview> convertToDto(List<Review> reviews) {
        return reviews.stream()
                .map(review -> new DtoReview(review.getId(),review.getRating(), review.getComment()))
                .collect(Collectors.toList());
    }

    public void deleteReview(long reviewId) {
       reviewRepository.deleteById(reviewId);
    }
}
