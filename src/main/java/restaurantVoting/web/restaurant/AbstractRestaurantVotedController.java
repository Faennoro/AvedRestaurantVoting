package restaurantVoting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import restaurantVoting.model.Restaurant;
import restaurantVoting.model.RestaurantVoted;
import restaurantVoting.service.RestaurantService;
import restaurantVoting.service.RestaurantVotedService;
import restaurantVoting.util.ValidationUtil;

import java.util.List;

public abstract class AbstractRestaurantVotedController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantVotedService service;

    public RestaurantVoted get(int id, int restaurantId){
        log.info("get restaurant {}", id);
        return service.get(id, restaurantId);
    }

    public void delete(int id){
        log.info("delete dish {}", id);
        service.delete(id);
    }

    public List<RestaurantVoted> getAll(){
        log.info("getAll for restaurants");
        return service.getAll();
    }

    public RestaurantVoted create(RestaurantVoted restaurant, Integer restaurantId){
        ValidationUtil.checkNew(restaurant);
        log.info("create {}", restaurant);
        return service.create(restaurant, restaurantId);
    }

    public void update(RestaurantVoted restaurant, int id, Integer restaurantId){
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("update {}", restaurant);
        service.update(restaurant, restaurantId);
    }
}
