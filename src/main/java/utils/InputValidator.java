package utils;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import models.Point;

import java.util.Arrays;

@Named
@RequestScoped
public class InputValidator implements Validator {

    public boolean validate(Point point) {
        int[] validRValues = { 1, 2, 3, 4, 5 };
        if (Arrays.stream(validRValues).noneMatch(v -> v == point.getRadius())) {
            return false;
        }

        if (point.getX() < -2 || point.getX() > 2) {
            return false;
        }

        if (point.getY() < -5 || point.getY() > 5) {
            return false;
        }

        return true;
    }
}
