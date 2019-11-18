package ru.grad.restaurantVoting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "user_vote_history")
@NamedQueries({
        @NamedQuery(name = Vote.DELETE_BY_DATE, query = "DELETE FROM Vote v WHERE v.date =: date"),
        @NamedQuery(name = Vote.DELETE_FOR_DATES, query = "DELETE FROM Vote v WHERE v.date <: date"),
        @NamedQuery(name = Vote.GET_BY_USER, query = "SELECT v FROM Vote v WHERE v.user.id =: userId ORDER BY v.restaurant.name"),
        @NamedQuery(name = Vote.GET_BY_USER_DATE, query = "SELECT v FROM Vote v WHERE v.user.id =: userId AND v.date =: date ORDER BY v.restaurant.name")
})
public class Vote extends AbstractBaseEntity{

    public static final LocalTime STOP_VOTING = LocalTime.of(11, 00);
    public static final String DELETE_BY_DATE = "Vote.deleteByDate";
    public static final String DELETE_FOR_DATES = "Vote.deleteForDates";
    public static final String GET_BY_USER = "Vote.getByUser";
    public static final String GET_BY_USER_DATE = "Vote.getByUserDate";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    private LocalDate date = LocalDate.now();

    public Vote(){}

    public Vote(Integer id, User user, Restaurant restaurant, LocalDate date){
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.date = date;
    }

    public Vote(LocalDate date){
        this.date=date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }
}
