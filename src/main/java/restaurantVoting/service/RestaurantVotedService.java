package restaurantVoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import restaurantVoting.model.Restaurant;
import restaurantVoting.model.RestaurantVoted;
import restaurantVoting.repository.restaurant.RestaurantRepository;
import restaurantVoting.repository.restaurantVoted.RestaurantVotedRepository;
import restaurantVoting.util.ValidationUtil;

import java.util.List;

@Service
public class RestaurantVotedService {

    private final RestaurantVotedRepository repository;

    @Autowired
    public RestaurantVotedService(RestaurantVotedRepository repository) {
        this.repository = repository;
    }

    public RestaurantVoted get(int id, int restaurantId) { return ValidationUtil.checkNotFoundWithId(repository.get(id, restaurantId),id);
    }

    public void delete(int id) {ValidationUtil.checkNotFoundWithId(repository.delete(id), id);}

    public List<RestaurantVoted> getAll(){ return repository.getAll();}

    public void update(RestaurantVoted restaurant, Integer restaurantId){
        Assert.notNull(restaurant, "restaurantVoted must not be null");
        ValidationUtil.checkNotFoundWithId(repository.save(restaurant, restaurantId),restaurantId);
    }

    public RestaurantVoted create(RestaurantVoted restaurant, Integer restaurantId){
        Assert.notNull(restaurant, "restaurantVoted must not be null");
        return repository.save(restaurant, restaurantId);
    }

    public RestaurantVoted getWithRestaurant(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.getWithRestaurant(id), id).orElse(null);
    }
}
