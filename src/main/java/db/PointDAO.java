package db;

import models.Point;

import jakarta.persistence.EntityManager;
import java.io.Serializable;

public class PointDAO implements Serializable {

    public void save(Point point) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(point);
//        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
