package ru.grad.restaurantVoting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.grad.restaurantVoting.model.RestaurantVoted;
import ru.grad.restaurantVoting.model.Vote;
import ru.grad.restaurantVoting.service.RestaurantVotedService;
import ru.grad.restaurantVoting.util.ValidationUtil;
import ru.grad.restaurantVoting.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractRestaurantVotedController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantVotedService restaurantVotedService;



    public RestaurantVoted get(int id, int restaurantId){
        log.info("get restaurant {}", id);
        return restaurantVotedService.get(id, restaurantId);
    }

    public void delete(int id){
        log.info("delete dish {}", id);
        restaurantVotedService.delete(id);
    }

    public List<RestaurantVoted> getAll(){
        log.info("getAll for restaurants");
        return restaurantVotedService.getAll();
    }

    public RestaurantVoted create(RestaurantVoted restaurant, Integer restaurantId){
        ValidationUtil.checkNew(restaurant);
        log.info("create {}", restaurant);
        return restaurantVotedService.create(restaurant, restaurantId);
    }

    public void update(RestaurantVoted restaurant, int id, Integer restaurantId){
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("update {}", restaurant);
        restaurantVotedService.update(restaurant, restaurantId);
    }

    public Vote voting(int id){
        int userId = SecurityUtil.authUserId();
        log.info("voting for restaurant {} for user {}", id, userId);
        Vote vote = restaurantVotedService.voting(id,userId);
        return vote;
    }

    public Vote getVote(){
        int userId = SecurityUtil.authUserId();
        log.info("get current vote for user {}", userId);
        return restaurantVotedService.getVoteUserDate(userId, LocalDate.now());
    }
}
