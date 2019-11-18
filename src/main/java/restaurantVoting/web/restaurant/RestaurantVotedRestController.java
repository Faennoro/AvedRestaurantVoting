package restaurantVoting.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurantVoting.model.Restaurant;
import restaurantVoting.model.RestaurantVoted;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantVotedRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantVotedRestController extends AbstractRestaurantVotedController {
    static final String REST_URL = "/history_rest";

    @Override
    @GetMapping("/{id}")
    public RestaurantVoted get(@PathVariable int id, @RequestParam int restaurantId) {
        return super.get(id, restaurantId);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<RestaurantVoted> getAll() {
        return super.getAll();
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantVoted> createWithLocation(@RequestBody RestaurantVoted restaurant) {
        RestaurantVoted created = super.create(restaurant, restaurant.getRestaurant().getId());
        //TODO fix
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath().path(REST_URL).buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{restaurantId}/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody RestaurantVoted restaurant, @PathVariable int id, @PathVariable Integer restaurantId) {
        super.update(restaurant, id, restaurantId);
    }
}
