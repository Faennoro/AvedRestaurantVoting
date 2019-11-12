package restaurantVoting.repository.restaurantVoted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantVoting.model.Restaurant;
import restaurantVoting.model.RestaurantVoted;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class RestaurantVotedRepositoryImpl implements RestaurantVotedRepository {
    private static final Sort SORT_NAME_ADDRESS = new Sort(Sort.Direction.ASC, "name", "address");

    @Autowired
    private CrudRestaurantVotedRepository crudRepository;

    @Override
    public RestaurantVoted save(RestaurantVoted restaurant) {
        if (!restaurant.isNew() && get(restaurant.getId())== null){
            return null;
        }
        return crudRepository.save(restaurant);
    }

    @Override
    public Optional<RestaurantVoted> getForDate(LocalDate date) {
        return crudRepository.getForDate(date);
    }

    @Override
    public Optional<RestaurantVoted> getBetweenDates(LocalDate minDate, LocalDate maxDate) {
        return crudRepository.getBetweenDates(minDate, maxDate);
    }

    @Override
    public Optional<RestaurantVoted> getByRestaurant(Integer restaurant_id) {
        return crudRepository.getByRestaurant(restaurant_id);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public RestaurantVoted get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<RestaurantVoted> getAll() {
        return crudRepository.findAll(SORT_NAME_ADDRESS);
    }
}
