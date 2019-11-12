package restaurantVoting.repository.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantVoting.model.Restaurant;
import restaurantVoting.model.Vote;
import restaurantVoting.repository.restaurant.CrudRestaurantRepository;
import restaurantVoting.repository.user.CrudUserRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class VoteRepositoryImpl implements VoteRepository {
    private static final Sort SORT_NAME_ADDRESS = new Sort(Sort.Direction.ASC, "name", "address");

    @Autowired
    private CrudVoteRepository crudRepository;

    @Override
    public Vote save(Vote vote) {
        return crudRepository.save(vote);
    }

    @Override
    public boolean deleteByDate(LocalDate date) {
        return crudRepository.deleteByDate(date) != 0;
    }

    @Override
    public boolean deleteForDates(LocalDate date) {
        return crudRepository.deleteForDates(date) != 0;
    }

    @Override
    public Vote get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Vote> getByUser(Integer userId) {
        return crudRepository.getByUser(userId);
    }

    @Override
    public Optional<Vote> getByUserDate(Integer userId, LocalDate date) {
        return crudRepository.getByUserDate(userId, date);
    }

    @Override
    public List<Vote> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public int delete(int id) {
        return crudRepository.delete(id);
    }
}
