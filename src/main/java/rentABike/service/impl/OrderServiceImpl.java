package rentABike.service.impl;

import org.springframework.stereotype.Service;
import rentABike.model.Order;
import rentABike.model.Rent;
import rentABike.model.User;
import rentABike.model.exceptions.AlreadyInYourOrdersException;
import rentABike.model.exceptions.BrandNotFoundException;
import rentABike.model.exceptions.UserNotFoundException;
import rentABike.repository.jpa.OrderRepository;
import rentABike.repository.jpa.UserRepository;
import rentABike.service.OrderService;
import rentABike.service.RentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RentService rentService;

    public OrderServiceImpl (OrderRepository orderRepository, UserRepository userRepository, RentService rentService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.rentService = rentService;
    }

    @Override
    public List<Rent> listAll (Long id) {
        return this.orderRepository.findById(id).get().getRent();
    }

    @Override
    public List<Order> listAllOrders () {
        return this.orderRepository.findAll();
    }

    @Override
    public List<Order> listAllOrdersAccessories () {
        return this.orderRepository.findAll();
    }

    @Override
    public Order getActiveFavourites (String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
        return this.orderRepository.findByUser(user).orElseGet(() -> {

            Order order = new Order(user);
            return this.orderRepository.save(order);
        });
    }

    @Override
    public Order addToFavourites (String username, Long rentId) {

        Rent rent = this.rentService.findById(rentId).orElseThrow(() -> new BrandNotFoundException(rentId));
        Order order = this.getActiveFavourites(username);
        if(order.getRent().stream().filter(i -> i.getId().equals(rentId)).collect(Collectors.toList()).size() > 0)
            throw new AlreadyInYourOrdersException(rentId,username);
        order.getRent().add(rent);
        return this.orderRepository.save(order);
    }
}
