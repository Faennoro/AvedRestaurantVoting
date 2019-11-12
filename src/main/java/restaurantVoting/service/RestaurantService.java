package restaurantVoting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import restaurantVoting.model.Restaurant;
import restaurantVoting.repository.restaurant.RestaurantRepository;
import restaurantVoting.util.ValidationUtil;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id) { return ValidationUtil.checkNotFoundWithId(repository.get(id),id);
    }

    public void delete(int id) {ValidationUtil.checkNotFoundWithId(repository.delete(id), id);}

    public List<Restaurant> getAll(){ return repository.getAll();}

    public void update(Restaurant restaurant){
        Assert.notNull(restaurant, "restaurant must not be null");
        ValidationUtil.checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    public Restaurant create(Restaurant restaurant){
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public Restaurant getWithDishes(int id) {
        return ValidationUtil.checkNotFoundWithId(repository.getWithDishes(id), id);
    }
}
