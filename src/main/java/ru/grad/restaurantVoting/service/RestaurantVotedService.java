package ru.grad.restaurantVoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.grad.restaurantVoting.model.RestaurantVoted;
import ru.grad.restaurantVoting.model.Vote;
import ru.grad.restaurantVoting.repository.restaurantVoted.RestaurantVotedRepository;
import ru.grad.restaurantVoting.repository.vote.VoteRepository;
import ru.grad.restaurantVoting.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class RestaurantVotedService {

    private final RestaurantVotedRepository restaurantVotedRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public RestaurantVotedService(RestaurantVotedRepository restaurantVotedRepository, VoteRepository voteRepository) {
        this.restaurantVotedRepository = restaurantVotedRepository;
        this.voteRepository = voteRepository;
    }

    public RestaurantVoted get(int id, int restaurantId) { return ValidationUtil.checkNotFoundWithId(restaurantVotedRepository.get(id, restaurantId),id);
    }

    public void delete(int id) {ValidationUtil.checkNotFoundWithId(restaurantVotedRepository.delete(id), id);}

    public List<RestaurantVoted> getAll(){ return restaurantVotedRepository.getAll();}

    public void update(RestaurantVoted restaurant, Integer restaurantId){
        Assert.notNull(restaurant, "restaurantVoted must not be null");
        ValidationUtil.checkNotFoundWithId(restaurantVotedRepository.save(restaurant, restaurantId),restaurantId);
    }

    public RestaurantVoted create(RestaurantVoted restaurant, Integer restaurantId){
        Assert.notNull(restaurant, "restaurantVoted must not be null");
        return restaurantVotedRepository.save(restaurant, restaurantId);
    }

    public RestaurantVoted getWithRestaurant(int id) {
        return ValidationUtil.checkNotFoundWithId(restaurantVotedRepository.getWithRestaurant(id), id).orElse(null);
    }

    public Vote voting(Integer restaurantId, Integer userId){
        Assert.notNull(restaurantId, "restaurantId must not be null");
        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();
        Vote vote = null;
        if (time.isBefore(Vote.STOP_VOTING)){
            vote = getVoteUserDate(userId, date);
            if (vote != null){
                voteRepository.delete(vote);
                Integer savedRestaurantId = vote.getRestaurant().getId();
                restaurantVotedRepository.decreaseVotes(savedRestaurantId,date);
            }
            vote = new Vote(date);
            voteRepository.save(vote,userId,restaurantId);
            restaurantVotedRepository.newVote(restaurantId,date);
        }

        return vote;
    }

    public List<Vote> getVoteUser (Integer userId){
        return voteRepository.getByUser(userId);
    }

    public Vote getVoteUserDate(Integer userId, LocalDate localDate){
        return voteRepository.findByUserAndDate(userId, localDate);
    }
}
