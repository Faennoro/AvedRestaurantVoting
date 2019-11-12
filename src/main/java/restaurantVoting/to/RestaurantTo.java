package restaurantVoting.to;

import restaurantVoting.HasId;

import java.util.Objects;

public class RestaurantTo extends BaseTo {

    private String name;

    private String address;

    private Integer votes;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, String address, Integer votes) {
        super(id);
        this.name = name;
        this.address = address;
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", votes=" + votes +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, votes);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RestaurantTo that = (RestaurantTo) obj;
        return votes == that.votes &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(address, that.address);
    }
}
