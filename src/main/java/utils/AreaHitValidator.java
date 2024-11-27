package utils;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import models.Point;

@Named
@RequestScoped
public class AreaHitValidator implements Validator {

    public boolean validate(Point point) {
        double x = point.getX();
        double y = point.getY();
        double r = point.getRadius();

        boolean hitRect = checkRectangle(r, x, y);
        boolean hitCircle = checkCircle(r, x, y);
        boolean hitTriangle = checkTriangle(r, x, y);

        return hitRect || hitTriangle || hitCircle;
    }

    private boolean checkRectangle(double r, double x, double y) {
        return (x >= -r / 2 && x <= 0) && (y >= -r && y <= 0);
    }

    private boolean checkCircle(double r, double x, double y) {
        return ((x * x + y * y) <= (r / 2 * r / 2)) && (x >= 0 && y >= 0);
    }

    private boolean checkTriangle(double r, double x, double y) {
        boolean withinXBounds = (0 <= x) && (x <= r);
        boolean withinYBounds = (y >= -r / 2) && (y <= 0);
        boolean aboveHypotenuse = (y >= 0.5 * x - r / 2);

        return withinXBounds && withinYBounds && aboveHypotenuse;
    }
}
