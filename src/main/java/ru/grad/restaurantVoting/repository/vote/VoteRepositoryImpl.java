package ru.grad.restaurantVoting.repository.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grad.restaurantVoting.model.Vote;
import ru.grad.restaurantVoting.repository.restaurant.CrudRestaurantRepository;
import ru.grad.restaurantVoting.repository.user.CrudUserRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class VoteRepositoryImpl implements VoteRepository {

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
    public List<Vote> getByUser(Integer userId) {
        return crudVoteRepository.getByUser(userId).orElse(null);
    }

    @Override
    public Vote getByUserDate(Integer userId, LocalDate date) {
        return crudVoteRepository.getByUserDate(userId, date).orElse(null);
    }

    @Override
    public List<Vote> getAll() {
        return crudVoteRepository.findAll();
    }

    @Override
    public void delete(Vote vote){crudVoteRepository.delete(vote);
    }
}
