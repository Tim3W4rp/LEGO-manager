package cz.fi.muni.legomanager.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Class for representing bricks.
 *
 * @author Lukáš Dvořák
 */
@Entity
public class Brick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(0)
    @Max(255)
    @Column(nullable = false)
    private int red;

    @NotNull
    @Min(0)
    @Max(255)
    @Column(nullable = false)
    private int green;

    @NotNull
    @Min(0)
    @Max(255)
    @Column(nullable = false)
    private int blue;

    @NotNull
    @OneToMany(mappedBy = "brick")
    private List<KitBrick> kitBricks = new ArrayList<>();

    @NotNull
    @ManyToOne
    private Shape shape;

    public Long getId() {
        return id;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public List<KitBrick> getKitBricks() {
        return Collections.unmodifiableList(kitBricks);
    }

    public void addKitBrick(KitBrick kitBrick) {
        kitBricks.add(kitBrick);
    }

    public void removeKitBrick(KitBrick kitBrick) {
        kitBricks.remove(kitBrick);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Brick)) {
            return false;
        }

        Brick other = (Brick) obj;
        return Objects.equals(shape, other.shape) &&
                Objects.equals(red, other.red) &&
                Objects.equals(green, other.green) &&
                Objects.equals(blue, other.blue);
    }

    @Override
    public int hashCode() {
        final int prime = 59;
        int result = 3;
        result = prime * result +
                (red + green + blue) +
                (shape == null ? 0 : shape.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Brick{" +
                "id=" + id +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", shape=" + shape +
                '}';
    }
}
