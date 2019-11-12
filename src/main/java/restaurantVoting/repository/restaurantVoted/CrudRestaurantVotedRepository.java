package restaurantVoting.repository.restaurantVoted;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import restaurantVoting.model.Dish;
import restaurantVoting.model.Restaurant;
import restaurantVoting.model.RestaurantVoted;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantVotedRepository extends JpaRepository<RestaurantVoted, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    RestaurantVoted save(RestaurantVoted restaurant);

    @Query("SELECT r FROM RestaurantVoted r ORDER BY r.votes ASC")
    List<RestaurantVoted> getAll();

    @Query("SELECT r FROM RestaurantVoted r WHERE r.date=:date")
    List<RestaurantVoted> getForDate(@Param("date")LocalDate date);

    @Query("SELECT r FROM RestaurantVoted r WHERE r.id=:id AND r.date BETWEEN :minDate AND :maxDate ORDER BY r.date DESC")
    List<RestaurantVoted> getBetweenDates(@Param("date")LocalDate date);

    @Query("SELECT d FROM RestaurantVoted d WHERE d.restaurant=:restaurant")
    List<RestaurantVoted> getByRestaurant(@Param("restaurantId") Integer restaurantId);

}
