package ru.grad.restaurantVoting.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.grad.restaurantVoting.model.Dish;
import ru.grad.restaurantVoting.model.Vote;

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

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId ORDER BY v.restaurant.name")
    Optional<List<Vote>> getByUser(@Param("userId") Integer userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId AND v.date = :date")
    Optional<Vote> findByUserAndDate(@Param("userId") Integer userId, @Param("date") LocalDate date);

    @Query("SELECT v FROM Vote v WHERE v.id= :Id")
    List<Vote> getAll();



    @Override
    void delete(Vote entity);
}
