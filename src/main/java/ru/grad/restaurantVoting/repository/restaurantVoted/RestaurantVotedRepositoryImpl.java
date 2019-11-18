package ru.grad.restaurantVoting.repository.restaurantVoted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grad.restaurantVoting.model.RestaurantVoted;
import ru.grad.restaurantVoting.repository.restaurant.CrudRestaurantRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class RestaurantVotedRepositoryImpl implements RestaurantVotedRepository {
    private static final Sort SORT_DATE = new Sort(Sort.Direction.ASC, "date");

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
        return crudRestaurantVotedRepository.findAll(SORT_DATE);
    }

    @Override
    public Optional<RestaurantVoted> getWithRestaurantDate(Integer restaurantId, LocalDate date) {
        return crudRestaurantVotedRepository.getWithRestaurantDate(restaurantId,date);
    }

    @Override
    public void newVote(Integer restaurantId, LocalDate date) {
        RestaurantVoted restaurantVoted = crudRestaurantVotedRepository.getWithRestaurantDate(restaurantId,date).orElse(null);
        if (restaurantId!=null){
            Integer newVotes = restaurantVoted.getVotes()+1;
            restaurantVoted.setVotes(newVotes);
        } else {
            restaurantVoted = new RestaurantVoted(date,1,crudRestaurantRepository.getOne(restaurantId));
        }
        crudRestaurantVotedRepository.save(restaurantVoted);
    }

    @Override
    public void decreaseVotes(Integer restaurantId, LocalDate date) {
        RestaurantVoted restaurantVoted = crudRestaurantVotedRepository.getWithRestaurantDate(restaurantId,date).orElse(null);
        if (restaurantVoted!=null && restaurantVoted.getVotes()>1){
            Integer newVotes = restaurantVoted.getVotes()+1;
            restaurantVoted.setVotes(newVotes);
            crudRestaurantVotedRepository.save(restaurantVoted);
        } else if (restaurantVoted !=null && restaurantVoted.getVotes()==1){
            crudRestaurantVotedRepository.delete(restaurantVoted);
        }
    }
}
