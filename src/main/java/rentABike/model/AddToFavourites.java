package rentABike.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class AddToFavourites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<Rent> rent;

}
