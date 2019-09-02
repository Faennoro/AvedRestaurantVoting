package restaurantVoting.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Dish.ALL_SORTED, query = "SELECT m FROM Dish m WHERE m.restaurant.id=:restaurantId ORDER BY m.date DESC"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish m WHERE m.id=:id AND m.restaurant.id=:restaurantId"),
})
@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date"}, name = "meals_unique_restaurant_date_idx")})
public class Dish extends AbstractBaseEntity{
    public static final String ALL_SORTED = "Dish.getAll";
    public static final String DELETE = "Dish.delete";

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN)
    private LocalDate date;
}
