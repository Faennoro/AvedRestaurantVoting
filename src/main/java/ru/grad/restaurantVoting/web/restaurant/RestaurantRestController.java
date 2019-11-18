package ru.grad.restaurantVoting.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.grad.restaurantVoting.model.Dish;
import ru.grad.restaurantVoting.model.Restaurant;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController extends AbstractRestaurantController {
    static final String REST_URL = "/rest/restaurants";

    @Override
    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable int id) {
        return super.getRestaurant(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable int id) {
        super.deleteRestaurant(id);
    }

    @Override
    @GetMapping
    public List<Restaurant> getRestaurants() {
        return super.getRestaurants();
    }



    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createRestaurantWithLocation(@Valid @RequestBody Restaurant restaurant) {
        Restaurant created = super.createRestaurant(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath().
                path(REST_URL+"/{id}").
                buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {
        super.updateRestaurant(restaurant, id);
    }
/*
    @Override
    @GetMapping("/{id}/dishes")
    public List<Dish> getDishes(@PathVariable int id) {
        return super.getDishes(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createDishWithLocation(@Valid @RequestBody Dish dish, int restaurantId) {
        Dish created = super.createDish(dish,restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath().
                path(REST_URL+"/{restaurantId}/dishes/{id}").
                buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{restaurantId}/dishes/{id}")
    public void updateDish(@RequestBody Dish dish, @PathVariable int id, @PathVariable int restaurantId) {
        super.updateDish(dish, id, restaurantId);
    }

    @Override
    @DeleteMapping("/{restaurantId}/dishes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable int id, @PathVariable int restaurantId) {
        super.deleteDish(id, restaurantId);
    }

    @Override
    @GetMapping("/{restaurantId}/dishes/{id}")
    public Dish getDish(@PathVariable int id, @PathVariable int restaurantId) {
        return super.getDish(id, restaurantId);
    }*/
}
