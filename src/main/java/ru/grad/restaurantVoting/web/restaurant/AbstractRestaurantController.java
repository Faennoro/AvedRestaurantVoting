package ru.grad.restaurantVoting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.grad.restaurantVoting.model.Dish;
import ru.grad.restaurantVoting.model.Restaurant;
import ru.grad.restaurantVoting.service.DishService;
import ru.grad.restaurantVoting.service.RestaurantService;
import ru.grad.restaurantVoting.service.VoteService;
import ru.grad.restaurantVoting.util.ValidationUtil;

import java.util.List;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    public Restaurant getRestaurant(int id){
        log.info("get restaurant {}", id);
        return restaurantService.get(id);
    }

    public void deleteRestaurant(int id){
        log.info("delete restaurant {}", id);
        restaurantService.delete(id);
    }



    public List<Restaurant> getRestaurants(){
        log.info("getAll for restaurants");
        return restaurantService.getAll();
    }

    public Restaurant createRestaurant(Restaurant restaurant){
        ValidationUtil.checkNew(restaurant);
        log.info("create {}", restaurant);
        return restaurantService.create(restaurant);
    }

    public void updateRestaurant(Restaurant restaurant, int id){
        ValidationUtil.assureIdConsistent(restaurant, id);
        log.info("update {}", restaurant);
        restaurantService.update(restaurant);
    }
/*
    public Dish createDish(Dish dish, int restaurantId){
        ValidationUtil.checkNew(dish);
        log.info("create {}", dish);
        return dishService.create(dish, restaurantId);
    }

    public List<Dish> getDishes(int restaurantId){
        log.info("getAll dishes for restaurant");
        return dishService.getAll(restaurantId);
    }

    public void updateDish(Dish dish, int id, int restaurantId){
        ValidationUtil.assureIdConsistent(dish,id);
        log.info("update {} for restaurant {}", dish, restaurantId);
        dishService.update(dish,restaurantId);
    }

    public Dish getDish(int id, int restaurantId){
        log.info("get dish {} for restaurant {}", id, restaurantId);
        return dishService.get(id, restaurantId);
    }

    public void deleteDish(int id, int restaurantId){
        log.info("delete dish {} for restaurant {}", id, restaurantId);
        dishService.delete(id);
    }*/
}
