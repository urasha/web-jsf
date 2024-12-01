package db;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import models.Point;

import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PointDAO implements Serializable {

    public void save(Point point) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(point);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Point> getAllPoints() {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        entityManager.getTransaction().begin();
        List<Point> points = entityManager.createQuery("SELECT p FROM Point p", Point.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return points;
    }
}
