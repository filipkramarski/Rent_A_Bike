package rentABike;

import lombok.Getter;
import org.springframework.stereotype.Component;
import rentABike.model.Category;
import rentABike.model.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class DataHolder {

    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        categories.add(new Category("MTB", "Mountain bike"));
        categories.add(new Category("RB", "Road bike"));
    }

}
