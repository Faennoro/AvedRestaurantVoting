package ru.grad.restaurantVoting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.grad.restaurantVoting.model.Restaurant;
import ru.grad.restaurantVoting.service.RestaurantService;
import ru.grad.restaurantVoting.service.VoteService;
import ru.grad.restaurantVoting.util.ValidationUtil;

import java.util.List;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private VoteService VoteService;

    public Restaurant get(int id){
        log.info("get restaurant {}", id);
        return restaurantService.get(id);
    }

    public void delete(int id){
        log.info("delete dish {}", id);
        restaurantService.delete(id);
    }

    public List<Restaurant> getAll(){
        log.info("getAll for restaurants");
        return restaurantService.getAll();
    }

    public Restaurant create(Restaurant restaurant){
        ValidationUtil.checkNew(restaurant);
        log.info("create {}", restaurant);
        return restaurantService.create(restaurant);
    }

    public void update(Restaurant restaurant, int id){
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("update {}", restaurant);
        restaurantService.update(restaurant);
    }
}
