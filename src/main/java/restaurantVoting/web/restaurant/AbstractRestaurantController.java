package restaurantVoting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import restaurantVoting.model.Restaurant;
import restaurantVoting.service.RestaurantService;
import restaurantVoting.util.ValidationUtil;

import java.util.List;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public Restaurant get(int id){
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    public void delete(int id){
        log.info("delete dish {}", id);
        service.delete(id);
    }

    public List<Restaurant> getAll(){
        log.info("getAll for restaurants");
        return service.getAll();
    }

    public Restaurant create(Restaurant restaurant){
        ValidationUtil.checkNew(restaurant);
        log.info("create {}", restaurant);
        return service.create(restaurant);
    }

    public void update(Restaurant restaurant, int id){
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("update {}", restaurant);
        service.update(restaurant);
    }
}
