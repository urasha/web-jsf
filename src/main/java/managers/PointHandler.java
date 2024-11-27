package managers;

import db.PointDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import models.Point;
import utils.AreaHitValidator;
import utils.InputValidator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
@Getter
@Setter
public class PointHandler implements Serializable {

    @Inject
    private ResponseSender responseSender;

    @Inject
    private AreaHitValidator areaHitValidator;

    @Inject
    private PointDAO pointDAO;

    @Inject
    private InputValidator inputValidator;

    private double x;
    private double y;
    private int radius = 2;

    private final List<Point> results = new ArrayList<>();

    public void handlePoint() {
        Point point = new Point(x, y, radius);

        if (!inputValidator.validate(point)) {
            responseSender.sendBadRequest();
            return;
        }

        point.setInside(areaHitValidator.validate(point));

        results.add(point);
        pointDAO.save(point);

        responseSender.sendRedirect("main.xhtml");
    }
}
