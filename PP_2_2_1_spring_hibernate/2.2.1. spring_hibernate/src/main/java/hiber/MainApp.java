package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarServise;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      getUsersList(userService);

      userService.add(new User("UserWithCar1", "LastnameButIHaveCar1",
              "userwithcar1@mailOnlyForPeopleWhoHasACar.ru", new Car("reallyCoolCar",
              1, Long.parseLong("1"))));
      userService.add(new User("UserWithCar2", "LastnameButIHaveCar2",
              "userwithcar2@mailOnlyForPeopleWhoHasACar.ru", new Car("notSoCoolCar",
              2,Long.parseLong("2"))));



      getUsersList(userService);

      List<User> userList = userService.getUser("reallyCoolCar", 1);
      System.out.println(" Users with this car: " );
      getUserList(userList);

      context.close();
   }

   public static void getUserList(List<User> userList) {
      for (User user : userList) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
   }

   public static List<User> getUsersList(UserService userService) {
      List<User> users = userService.listUsers();
      getUserList(users);
      return users;
   }
}
