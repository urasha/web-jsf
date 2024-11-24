package components;

import db.PointDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import models.Point;
import utils.AreaHitChecker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
@Getter
@Setter
public class PointCheckBean implements Serializable {

    private PointDAO pointDAO = new PointDAO();

    private double x;
    private double y;
    private int radius;

    private final List<Point> results = new ArrayList<>();

    public void checkPoint() {
        Point point = new Point(x, y, radius);
        point.setInside(AreaHitChecker.checkHit(point));
        results.add(point);
        pointDAO.save(point);
    }
}
