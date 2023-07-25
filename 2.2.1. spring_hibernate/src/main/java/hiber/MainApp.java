package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
//      userService.add(new User("User5", "Lastname5", "user5@mail.ru",
//              new Car("Nissan", 555)));
//      userService.add(new User("User6", "Lastname6", "user6@mail.ru",
//              new Car("Nissan", 555)));
//      userService.add(new User("User7", "Lastname7", "user7@mail.ru",
//              new Car("Mercedes", 111)));

        List<User> users = userService.getListUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
                System.out.println("Car Model = " + user.getCar().getModel());
                System.out.println("Car series = " + user.getCar().getSeries());
            }
            System.out.println("_____________");
        }

//      String carName = "Mercedes";
//      int series = 111;
        String carName = "Nissan";
        int series = 555;

        try {
            User userByCar = userService.getUserByCar(carName, series);
            System.out.println("********************");
            System.out.println(userByCar.toString());
            System.out.println("********************");
        } catch (Exception e) {
            List<User> usersByCar = userService.getUsersByCar(carName, series);

            for (User user : usersByCar) {
                System.out.println("+++++++++++++++++++++++++++");
                System.out.println("Id = " + user.getId());
                System.out.println("First Name = " + user.getFirstName());
                System.out.println("Last Name = " + user.getLastName());
                System.out.println("Email = " + user.getEmail());
                System.out.println("Car Model = " + user.getCar().getModel());
                System.out.println("Car series = " + user.getCar().getSeries());
                System.out.println("+++++++++++++++++++++++++++");
            }
        }

        context.close();
    }
}
