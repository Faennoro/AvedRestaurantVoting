package ru.grad.restaurantVoting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import ru.grad.restaurantVoting.View;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NamedQueries({
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId ORDER BY d.price DESC"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId"),
        @NamedQuery(name = Dish.GET_BETWEEN, query = "SELECT d FROM Dish d " +
                "WHERE d.restaurant.id=:restaurantId AND d.price BETWEEN :minPrice AND :maxPrice ORDER BY d.price DESC"),
})
@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "description"}, name = "dishes_unique_restaurant_description_idx")})
public class Dish extends AbstractBaseEntity{
    public static final String ALL_SORTED = "Dish.getAll";
    public static final String DELETE = "Dish.delete";
    public static final String GET_BETWEEN = "Dish.getBetween";

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 1, max = 500000)
    private BigDecimal price;

    @Column(name = "description", nullable = false)
    @NotNull
    @Size(min = 2, max = 55)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull(groups = View.Persist.class)
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Integer id, String description, BigDecimal price) {
        super(id);
        this.description = description;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id = " + id +
                ", description=" + description +
                ", price=" + price +
                '}';
    }
}
