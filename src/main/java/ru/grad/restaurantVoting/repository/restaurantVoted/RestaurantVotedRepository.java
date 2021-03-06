package ru.grad.restaurantVoting.repository.restaurantVoted;

import ru.grad.restaurantVoting.model.RestaurantVoted;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RestaurantVotedRepository {
    // null if not found, when updated
    RestaurantVoted save(RestaurantVoted restaurant, int restaurantId);

    // false if not found
    boolean delete(int id);

    // null if not found
    RestaurantVoted get(int id, int restaurantId);

    List<RestaurantVoted> getAll();

    Optional<RestaurantVoted> getForDate(LocalDate date);

    Optional<RestaurantVoted> getBetweenDates(LocalDate minDate, LocalDate maxDate);

    Optional<RestaurantVoted> getWithRestaurant(Integer restaurantId);

    Optional<RestaurantVoted> getWithRestaurantDate(Integer restaurantId, LocalDate date);

    void newVote(Integer restaurantId, LocalDate date);

    void decreaseVotes(Integer restaurantId, LocalDate date);
}
