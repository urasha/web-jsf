package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "point")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "x")
    @Check(name = "x", constraints = "x >= -2 AND x <= 2")
    private double x;

    @Column(name = "y")
    @Check(name = "y", constraints = "y >= -5 AND y <= 5")
    private double y;

    @Column(name = "radius")
    @Check(name = "r", constraints = "r IN (1, 2, 3, 4, 5)")
    private int radius;

    @Column(name = "inside")
    private boolean inside;

    public Point(double x, double y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}