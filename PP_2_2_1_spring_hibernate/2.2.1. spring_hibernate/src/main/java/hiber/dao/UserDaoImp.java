package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> getUser(String model, int series) {
      TypedQuery<User> listUser = sessionFactory.getCurrentSession().createQuery(
              "select user from User user, Car car where car.model = :car_model and car.series =" +
                      ":car_series and user.userCar.id = car.id", User.class);
      listUser.setParameter("car_model", model);
      listUser.setParameter("car_series", series);
      return listUser.getResultList();
   }

}
