package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import hiber.util.SeriesGenerator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        Car car = new Car("X1", SeriesGenerator.generate());
        Car car2 = new Car("X2", SeriesGenerator.generate());
        Car car3 = new Car("X3", SeriesGenerator.generate());
        Car car4 = new Car("X4", SeriesGenerator.generate());
        Car car5 = new Car("X5", SeriesGenerator.generate());

        User user0 = new User("John", "Doe", "example@gmail.com", car5);

        userService.add(user0);
        userService.add(new User("User1", "Lastname1", "user1@mail.ru", car));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(Objects.isNull(user.getCar()) ? null : user.getCar().toString());
            System.out.println();
        }

        System.out.println(userService.getUserById(1L) + "\n");

        carService.listCars().forEach(System.out::println);

        System.out.println("\n" + userService.getUserByCarModelAndSeries(car5.getModel(), car5.getSeries()));

        context.close();
    }
}
