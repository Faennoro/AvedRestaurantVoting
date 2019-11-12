package restaurantVoting.repository.restaurantVoted;

import restaurantVoting.model.Restaurant;
import restaurantVoting.model.RestaurantVoted;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantVotedRepository {
    // null if not found, when updated
    RestaurantVoted save(RestaurantVoted restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    RestaurantVoted get(int id);

    List<RestaurantVoted> getAll();

    List<RestaurantVoted> getForDate(LocalDate date);

    List<RestaurantVoted> getBetweenDates(LocalDate date);

    List<RestaurantVoted> getByRestaurant(Integer restaurantId);
}
