package restaurantVoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import restaurantVoting.model.Restaurant;
import restaurantVoting.model.Vote;
import restaurantVoting.repository.restaurant.RestaurantRepository;
import restaurantVoting.repository.vote.VoteRepository;
import restaurantVoting.util.ValidationUtil;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoteService {

    private final VoteRepository repository;

    @Autowired
    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote get(int id, Integer userId, Integer restaurantId) { return ValidationUtil.checkNotFoundWithId(repository.get(id, userId, restaurantId),id);
    }

    public void delete(int id) {ValidationUtil.checkNotFoundWithId(repository.delete(id), id);}

    public List<Vote> getAll(){ return repository.getAll();}

    public void update(Vote vote, Integer userId, Integer restaurantId){
        Assert.notNull(vote, "vote must not be null");
        ValidationUtil.checkNotFoundWithId(repository.save(vote, userId, restaurantId), vote.getId());
    }

    public Vote create(Vote vote, Integer userId, Integer restaurantId){
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote, userId, restaurantId);
    }

    public List<Vote> getByUser(Integer userId){
        return repository.getByUser(userId);
    }

}
