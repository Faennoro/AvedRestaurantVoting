package ru.grad.restaurantVoting.repository.dish;

import ru.grad.restaurantVoting.model.Dish;

import java.util.List;

public interface DishRepository {
    // null if not found, when updated
    Dish save(Dish dish, int restaurantId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);

    default Dish getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }

    //TODO getBetween
}
