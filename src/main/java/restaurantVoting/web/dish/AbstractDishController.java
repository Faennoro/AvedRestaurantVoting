package restaurantVoting.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import restaurantVoting.model.Dish;
import restaurantVoting.service.DishService;
import restaurantVoting.to.DishTo;
import restaurantVoting.util.ValidationUtil;

import java.util.List;

public abstract class AbstractDishController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public Dish get(int id, int restaurantId){
        log.info("get dish {} for restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    public void delete(int id){
        log.info("delete dish {}", id);
        service.delete(id);
    }

    public List<Dish> getAll(int restaurantId){
        log.info("getAll for restaurant {}", restaurantId);
        return service.getAll(restaurantId);
    }

    public Dish create(Dish dish, int restaurantId){
        ValidationUtil.checkNew(dish);
        log.info("create {} for restaurant {}", dish, restaurantId);
        return service.create(dish, restaurantId);
    }

    public void update(Dish dish, int id, int restaurantId){
        ValidationUtil.assureIdConsistent(dish, id);
        log.info("update {} for restaurant {}", dish, restaurantId);
        service.update(dish, restaurantId);
    }
}
