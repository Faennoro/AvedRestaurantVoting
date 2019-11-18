package ru.grad.restaurantVoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.grad.restaurantVoting.model.Vote;
import ru.grad.restaurantVoting.repository.vote.VoteRepository;
import ru.grad.restaurantVoting.util.ValidationUtil;

import java.time.LocalTime;
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

    public List<Vote> getAll(){ return repository.getAll();}

    public void update(Vote vote, Integer userId, Integer restaurantId){
        Assert.notNull(vote, "vote must not be null");
        ValidationUtil.checkNotFoundWithId(repository.save(vote, userId, restaurantId), vote.getId());
    }

    public Vote create(Vote vote, Integer userId, Integer restaurantId){
        Assert.notNull(vote, "vote must not be null");
        LocalTime timeNow = LocalTime.now();
        if (timeNow.isBefore(Vote.STOP_VOTING)) {
            return repository.save(vote, userId, restaurantId);
        } else return vote;
    }

    public List<Vote> getByUser(Integer userId){
        return repository.getByUser(userId);
    }

}
