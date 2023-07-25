package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getListUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsersByCar(String model, int series) {
        String queryString = "from User where car.model = :model and car.series =:series";
        TypedQuery<User> query = (TypedQuery<User>) sessionFactory.getCurrentSession().createQuery(queryString)
                .setParameter("model", model).setParameter("series", series);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, int series) {
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User where car.model = :model and car.series =:series", User.class)
                .setParameter("model", model).setParameter("series", series);
        User result = query.uniqueResult();
        return result;
    }
}
