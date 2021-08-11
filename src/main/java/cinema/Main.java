package cinema;

import cinema.exception.RegistrationException;
import cinema.lib.Injector;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.ShoppingCart;
import cinema.model.User;
import cinema.security.AuthenticationService;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static final Injector injector = Injector.getInstance("cinema");
    private static final MovieService movieService = (MovieService)
            injector.getInstance(MovieService.class);
    private static final CinemaHallService cinemaHallService = (CinemaHallService)
            injector.getInstance(CinemaHallService.class);
    private static final MovieSessionService movieSessionService = (MovieSessionService)
            injector.getInstance(MovieSessionService.class);
    private static final ShoppingCartService shoppingCartService = (ShoppingCartService)
            injector.getInstance(ShoppingCartService.class);
    private static final AuthenticationService authenticationService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static final UserService userService =
            (UserService) injector.getInstance(UserService.class);
    private static final OrderService orderService = (OrderService) injector
            .getInstance(OrderService.class);

    public static void main(String[] args) {

        Movie fastAndFurious = new Movie("Fast and Furious");
        fastAndFurious.setDescription("An action film about street racing, heists, and spies.");
        movieService.add(fastAndFurious);
        System.out.println(movieService.get(fastAndFurious.getId()));
        movieService.getAll().forEach(System.out::println);

        CinemaHall firstCinemaHall = new CinemaHall();
        firstCinemaHall.setCapacity(100);
        firstCinemaHall.setDescription("first hall with capacity 100");

        CinemaHall secondCinemaHall = new CinemaHall();
        secondCinemaHall.setCapacity(200);
        secondCinemaHall.setDescription("second hall with capacity 200");

        cinemaHallService.add(firstCinemaHall);
        cinemaHallService.add(secondCinemaHall);

        System.out.println(cinemaHallService.getAll());
        System.out.println(cinemaHallService.get(firstCinemaHall.getId()));

        MovieSession tomorrowMovieSession = new MovieSession();
        tomorrowMovieSession.setCinemaHall(firstCinemaHall);
        tomorrowMovieSession.setMovie(fastAndFurious);
        tomorrowMovieSession.setShowTime(LocalDateTime.now().plusDays(1L));

        MovieSession yesterdayMovieSession = new MovieSession();
        yesterdayMovieSession.setCinemaHall(firstCinemaHall);
        yesterdayMovieSession.setMovie(fastAndFurious);
        yesterdayMovieSession.setShowTime(LocalDateTime.now().minusDays(1L));

        movieSessionService.add(tomorrowMovieSession);
        movieSessionService.add(yesterdayMovieSession);

        System.out.println(movieSessionService.get(yesterdayMovieSession.getId()));
        System.out.println(movieSessionService.findAvailableSessions(
                fastAndFurious.getId(), LocalDate.now()));

        User eugen = new User();
        String email = "eugenesinica@gmail.com";
        try {
            eugen = authenticationService.register(email, "1234");
        } catch (RegistrationException e) {
            System.out.println("Can't register user with email " + email);
        }

        System.out.println();
        System.out.println(userService.findByEmail("eugenesinica@gmail.com").get());
        System.out.println();

        shoppingCartService.addSession(yesterdayMovieSession, eugen);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(eugen);
        orderService.completeOrder(shoppingCart);
        orderService.getOrdersHistory(eugen).forEach(System.out::println);
    }
}
