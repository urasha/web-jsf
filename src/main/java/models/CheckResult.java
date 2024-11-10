package models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "check_results")
@Data
@NoArgsConstructor
public class CheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "x")
    private double x;

    @Column(name = "y")
    private double y;

    @Column(name = "radius")
    private int radius;

    @Column(name = "inside")
    private boolean inside;

    public CheckResult(double x, double y, int radius, boolean inside) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.inside = inside;
    }
}