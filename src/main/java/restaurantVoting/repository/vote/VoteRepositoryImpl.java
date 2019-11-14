package restaurantVoting.repository.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantVoting.model.Vote;
import restaurantVoting.repository.restaurant.CrudRestaurantRepository;
import restaurantVoting.repository.user.CrudUserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class VoteRepositoryImpl implements VoteRepository {
    private static final Sort SORT_NAME_ADDRESS = new Sort(Sort.Direction.ASC, "name", "address");

    @Autowired
    private CrudVoteRepository crudVoteRepository;
    @Autowired
    private CrudUserRepository crudUserRepository;
    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Vote save(Vote vote, Integer userId, Integer restaurantId) {
        if (!vote.isNew() && get(vote.getId(),userId,restaurantId)==null){return null;}
        vote.setUser(crudUserRepository.getOne(userId));
        vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudVoteRepository.save(vote);
    }

    @Override
    public boolean deleteByDate(LocalDate date) {
        return crudVoteRepository.deleteByDate(date) != 0;
    }

    @Override
    public boolean deleteForDates(LocalDate date) {
        return crudVoteRepository.deleteForDates(date) != 0;
    }

    @Override
    public Vote get(int id, int userId, int restaurantId) {
        return crudVoteRepository.findById(id).filter(vote -> vote.getRestaurant().getId()== restaurantId && vote.getUser().getId() == userId).orElse(null);
    }

    @Override
    public Optional<Vote> getByUser(Integer userId) {
        return crudVoteRepository.getByUser(userId);
    }

    @Override
    public Optional<Vote> getByUserDate(Integer userId, LocalDate date) {
        return crudVoteRepository.getByUserDate(userId, date);
    }

    @Override
    public List<Vote> getAll() {
        return crudVoteRepository.findAll();
    }

    @Override
    public int delete(int id) {
        return crudVoteRepository.delete(id);
    }
}
