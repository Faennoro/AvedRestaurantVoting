package ru.grad.restaurantVoting.repository.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.grad.restaurantVoting.model.Dish;
import ru.grad.restaurantVoting.repository.restaurant.CrudRestaurantRepository;

import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository{
    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(),restaurantId)==null){return null;}
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return crudDishRepository.findById(id).filter(dish -> dish.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public Dish getWithRestaurant(int id, int restaurantId) {
        return crudDishRepository.getWithRestaurant(id, restaurantId);
    }

    @Override
    public List<Dish> getAll(int id) {
        return crudDishRepository.getAll(id);
    }


}
