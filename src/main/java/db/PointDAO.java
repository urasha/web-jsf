package db;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import models.Point;

import jakarta.persistence.EntityManager;
import java.io.Serializable;

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
}
