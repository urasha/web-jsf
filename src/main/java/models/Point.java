package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private double x;

    @Column(name = "y")
    private double y;

    @Column(name = "radius")
    private int radius;

    @Column(name = "inside")
    private boolean inside;

    public Point(double x, double y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}