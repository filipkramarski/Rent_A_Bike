package rentABike.service;

import rentABike.model.Order;
import rentABike.model.Rent;

import java.util.Collection;
import java.util.List;

public interface OrderService {

    List<Rent> listAll(Long orderId);
    Collection<Order> listAllOrders();
    Collection<Order> listAllOrdersAccessories();
    Order getActiveFavourites (String username);
    Order addToFavourites(String username, Long productId);

}
