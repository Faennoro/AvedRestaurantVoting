package ru.grad.restaurantVoting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.grad.restaurantVoting.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = RestaurantVoted.ALL_SORTED, query = "SELECT d FROM RestaurantVoted d ORDER BY d.date DESC"),
        @NamedQuery(name = RestaurantVoted.DELETE, query = "DELETE FROM RestaurantVoted d WHERE d.id=:id"),
        @NamedQuery(name = RestaurantVoted.GET_BETWEEN, query = "SELECT d FROM RestaurantVoted d WHERE d.id=:id AND d.date BETWEEN :minDate AND :maxDate ORDER BY d.date DESC"),
        @NamedQuery(name = RestaurantVoted.GET_BY_DATE, query = "SELECT d FROM RestaurantVoted d WHERE d.date=:date"),
        @NamedQuery(name = RestaurantVoted.GET_BY_RESTAURANT, query = "SELECT d FROM RestaurantVoted d WHERE d.restaurant=:restaurant")
})
@Entity
@Table(name = "restaurant_vote_history", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "restaurant_id"}, name = "restaurant_vote_history_unique_date_restaurant_idx")})
public class RestaurantVoted extends AbstractBaseEntity {
    public static final String ALL_SORTED = "RestaurantVoted.getAll";
    public static final String DELETE = "RestaurantVoted.delete";
    public static final String GET_BETWEEN = "RestaurantVoted.getBetween";
    public static final String GET_BY_DATE = "RestaurantVoted.getToday";
    public static final String GET_BY_RESTAURANT = "RestaurantVoted.getByRestaurant";

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Integer id;

    @Column(name = "date", nullable = false, columnDefinition = "date default now()")
    @NotNull
    private LocalDate date;

    @Column(name = "votes", nullable = false)
    @NotNull
    private Integer votes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private Restaurant restaurant;

    public RestaurantVoted() {
    }

    public RestaurantVoted(RestaurantVoted rv){
        this(rv.getId(), rv.getDate(), rv.getVotes(), rv.getRestaurant());
    }

    public RestaurantVoted(Integer id, LocalDate date, Integer votes, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.votes = votes;
        this.restaurant = restaurant;
    }

    public RestaurantVoted(LocalDate date, Integer votes, Restaurant restaurant) {
        this.date = date;
        this.votes = votes;
        this.restaurant = restaurant;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
