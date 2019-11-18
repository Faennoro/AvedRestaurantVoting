package ru.grad.restaurantVoting.to;

import java.math.BigDecimal;
import java.util.Objects;

public class DishTo extends BaseTo {

    private String description;

    private BigDecimal price;

    public DishTo() {
    }

    public DishTo(Integer id, String description, BigDecimal price) {
        super(id);
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj ==null || getClass() != obj.getClass()) return false;
        DishTo that = (DishTo) obj;
        return Objects.equals(id, that.id) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price);
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id + '\'' +
                "description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
