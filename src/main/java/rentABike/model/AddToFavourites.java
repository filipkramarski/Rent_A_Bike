package rentABike.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class AddToFavourites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Rent> rent;

    public AddToFavourites (User user) {
        this.user = user;
        this.rent = new ArrayList<>();
    }
    public AddToFavourites () {
    }
}
