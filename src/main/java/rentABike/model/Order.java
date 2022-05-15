package rentABike.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="order_details")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Rent> rent;

    @ManyToMany
    private List<Accessories> accessories;

    public Order (User user) {
        this.user = user;
        this.rent = new ArrayList<>();
    }
    public Order () {
    }
}
