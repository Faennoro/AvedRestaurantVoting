package ru.grad.restaurantVoting.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT r FROM Restaurant r ORDER BY r.name, r.address"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.ALL_BY_DATE, query = "SELECT r FROM Restaurant r WHERE r.date=:date")
})
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "date"}, name = "restaurants_unique_name_date_idx")})
public class Restaurant extends AbstractNamedEntity {
    public static final String ALL_SORTED = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";
    public static final String ALL_BY_DATE = "Restaurant.allByDate";

    @NotBlank
    @Size(min = 2, max = 255)
    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("price DESC")
    protected List<Dish> dishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    protected List<RestaurantVoted> restaurantVoted;

    @Column(name = "date", nullable = false, columnDefinition = "date default now()")
    @NotNull
    private LocalDate date = LocalDate.now();



    public Restaurant() {
    }

    public Restaurant(Restaurant r){
        this(r.getId(), r.getName(), r.getAddress(), r.getDate());
    }

    public Restaurant(Integer id, String name, String address, LocalDate date) {
        super(id, name);
        this.address = address;
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<RestaurantVoted> getRestaurantVoted() {
        return restaurantVoted;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +'\'' +
                ", name='" + name + '\'' +
                ", address='" + address +
                '}';
    }
}
