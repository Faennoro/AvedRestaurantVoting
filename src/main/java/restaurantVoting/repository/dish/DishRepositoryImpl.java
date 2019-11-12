package restaurantVoting.repository.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import restaurantVoting.model.Dish;

import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository{
    @Autowired
    private CrudDishRepository crudRepository;

    @Override
    public Dish save(Dish dish, int restaurantId) {
        return crudRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return crudRepository.findById(id).filter(dish -> dish.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public Dish getWithRestaurant(int id, int restaurantId) {
        return crudRepository.getWithRestaurant(id, restaurantId);
    }

    @Override
    public List<Dish> getAll(int id) {
        return crudRepository.getAll(id);
    }
}
