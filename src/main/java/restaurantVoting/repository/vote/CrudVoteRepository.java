package restaurantVoting.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import restaurantVoting.model.Dish;
import restaurantVoting.model.Restaurant;
import restaurantVoting.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.date=:date")
    int deleteByDate(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.date<:date")
    int deleteForDates(@Param("date") LocalDate date);

    @Override
    @Transactional
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v WHERE v.user.id =: userId ORDER BY v.restaurant.name")
    Optional<List<Vote>> getByUser(@Param("userId") Integer userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id =: userId AND v.date =: date ORDER BY v.restaurant.name")
    Optional<Vote> getByUserDate(@Param("userId") Integer userId, @Param("date") LocalDate date);

    @Query("SELECT r FROM Restaurant r WHERE r.id=:Id ORDER BY r.name ASC")
    List<Dish> getAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);
}
