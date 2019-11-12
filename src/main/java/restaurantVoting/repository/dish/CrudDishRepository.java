package restaurantVoting.repository.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import restaurantVoting.model.Dish;

import java.math.BigDecimal;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Override
    @Transactional
    Dish save(Dish dish);

    @Query("SELECT m FROM Dish m WHERE m.restaurant.id=:restaurantId ORDER BY m.price DESC")
    List<Dish> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT d from Dish d WHERE d.restaurant.id=:restaurantId AND d.price BETWEEN :minPrice AND :maxPrice ORDER BY d.price DESC")
    List<Dish> getBetween(@Param("minPrice")BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, @Param("restaurantId") int restaurantId);

    @Query("SELECT d from Dish d JOIN fetch d.restaurant WHERE d.id = ?1 and d.restaurant.id = ?2")
    Dish getWithRestaurant(int id, int restaurantId);


}
