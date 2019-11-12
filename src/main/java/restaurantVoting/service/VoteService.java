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

    public Vote get(int id) { return ValidationUtil.checkNotFoundWithId(repository.get(id),id);
    }

    public void delete(int id) {ValidationUtil.checkNotFoundWithId(repository.delete(id), id);}

    public List<Vote> getAll(){ return repository.getAll();}

    public void update(Vote vote){
        Assert.notNull(vote, "vote must not be null");
        ValidationUtil.checkNotFoundWithId(repository.save(vote), vote.getId());
    }

    public Vote create(Vote vote){
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote);
    }

}
