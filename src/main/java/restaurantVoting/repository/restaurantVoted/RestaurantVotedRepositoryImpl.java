package restaurantVoting.repository.restaurantVoted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurantVoting.model.RestaurantVoted;
import restaurantVoting.repository.restaurant.CrudRestaurantRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class RestaurantVotedRepositoryImpl implements RestaurantVotedRepository {
    private static final Sort SORT_NAME_ADDRESS = new Sort(Sort.Direction.ASC, "name", "address");

    @Autowired
    private CrudRestaurantVotedRepository crudRestaurantVotedRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Override
    public RestaurantVoted save(RestaurantVoted restaurant, int restaurantId) {
        if (!restaurant.isNew() && get(restaurant.getId(), restaurantId)== null){
            return null;
        }
        return crudRestaurantVotedRepository.save(restaurant);
    }

    @Override
    public Optional<RestaurantVoted> getForDate(LocalDate date) {
        return crudRestaurantVotedRepository.getForDate(date);
    }

    @Override
    public Optional<RestaurantVoted> getBetweenDates(LocalDate minDate, LocalDate maxDate) {
        return crudRestaurantVotedRepository.getBetweenDates(minDate, maxDate);
    }

    @Override
    public Optional<RestaurantVoted> getWithRestaurant(Integer restaurant_id) {
        return crudRestaurantVotedRepository.getWithRestaurant(restaurant_id);
    }

    @Override
    public boolean delete(int id) {
        return crudRestaurantVotedRepository.delete(id) != 0;
    }

    @Override
    public RestaurantVoted get(int id, int restaurantId) {
        return crudRestaurantVotedRepository.findById(id).filter(restaurantVoted -> restaurantVoted.getRestaurant().getId()== restaurantId).orElse(null);
    }

    @Override
    public List<RestaurantVoted> getAll() {
        return crudRestaurantVotedRepository.findAll(SORT_NAME_ADDRESS);
    }
}
