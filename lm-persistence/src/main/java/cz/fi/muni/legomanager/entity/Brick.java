package cz.fi.muni.legomanager.entity;

import cz.fi.muni.legomanager.enums.Color;

import javax.persistence.*;
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
    private Long idBrick;

    @NotNull
    private int red;

    @NotNull
    private int green;

    @NotNull
    private int blue;

    @NotNull
    @OneToMany(mappedBy = "brick")
    private List<KitBrick> kitBricks = new ArrayList<>();

    @NotNull
    @ManyToOne
    private Shape shape;

    public Long getIdBrick() {
        return idBrick;
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
        return getIdBrick() != null &&
                Objects.equals(idBrick, other.idBrick) &&
                Objects.equals(shape, other.shape) &&
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
                "idBrick=" + idBrick +
                ", red=" + red +
                ", greem=" + green +
                ", blue=" + blue +
                ", kitBricks=" + kitBricks +
                ", shape=" + shape +
                '}';
    }
}
