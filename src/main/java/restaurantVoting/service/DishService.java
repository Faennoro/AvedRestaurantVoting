package restaurantVoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import restaurantVoting.model.Dish;
import restaurantVoting.repository.dish.DishRepository;
import restaurantVoting.util.ValidationUtil;

import java.util.List;

@Service
public class DishService {


    private final DishRepository repository;

    @Autowired
    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish create(Dish dish, int restaurantId){
        return ValidationUtil.checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    public Dish update(Dish dish, int restaurantId){
        Assert.notNull(dish, "dish must not be null");
        return ValidationUtil.checkNotFoundWithId(repository.save(dish, restaurantId), dish.getId());
    }

    public void delete(int id, int restaurantId){
        ValidationUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    public Dish get(int id, int restaurantId){
        return ValidationUtil.checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    public List<Dish> getAll(int restaurantId){return repository.getAll(restaurantId);}


}
