package components;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import lombok.Data;
import models.CheckResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
@Data
public class PointCheckBean implements Serializable {

    private double x;
    private double y;
    private int radius;
    private final List<CheckResult> results = new ArrayList<>();

    public void checkPoint() {
        boolean inside = (x * x + y * y) <= (radius * radius);
        results.add(new CheckResult(x, y, radius, inside));
    }
}
