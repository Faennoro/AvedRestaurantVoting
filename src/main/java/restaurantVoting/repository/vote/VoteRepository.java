package restaurantVoting.repository.vote;

import restaurantVoting.model.Restaurant;
import restaurantVoting.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteRepository {
    // null if not found, when updated
    Vote save(Vote vote, Integer userId, Integer restaurantId);

    // false if not found
    boolean deleteByDate(LocalDate date);

    boolean deleteForDates(LocalDate date);

    // null if not found
    Vote get(int id, int userId, int restaurantId);

    List<Vote> getByUser(Integer userId);

    Vote getByUserDate(Integer userId, LocalDate date);

    List<Vote> getAll();

    int delete(int id);
}
