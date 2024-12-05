package managers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.PointDAO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import models.Point;
import utils.AreaHitValidator;
import utils.InputValidator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@ApplicationScoped
@Getter
@Setter
public class PointHandler implements Serializable {

    private static final Logger logger = Logger.getLogger(PointHandler.class.getName());

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
    private int radius;

    private final List<Point> results = new ArrayList<>();

    @PostConstruct
    public void init() {
        x = 0;
        y = 0;
        radius = 2;
        results.addAll(pointDAO.getAllPoints());
    }

    @Transactional
    public String handlePoint() {
        Point point = new Point(x, y, radius);

        logger.info("Handling point: " + point);

        if (!inputValidator.validate(point)) {
            responseSender.sendBadRequest();
            return null;
        }

        point.setInside(areaHitValidator.validate(point));

        results.add(point);
        pointDAO.save(point);

        return null;
    }

    public String getResultsAsJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(results);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]";
        }
    }
}
