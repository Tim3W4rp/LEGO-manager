package cz.fi.muni.legomanager.dto;

import java.util.Objects;

/**
 * @author Lukáš Dvořák
 */
public class BrickCreateDTO {

    private int red;
    private int green;
    private int blue;
    private ShapeDTO shape;

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public ShapeDTO getShape() {
        return shape;
    }

    public void setShape(ShapeDTO shape) {
        this.shape = shape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BrickCreateDTO)) return false;
        BrickCreateDTO that = (BrickCreateDTO) o;
        return red == that.red &&
                green == that.green &&
                blue == that.blue &&
                Objects.equals(shape, that.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(red, green, blue, shape);
    }

    @Override
    public String toString() {
        return "BrickCreateDTO{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", shape=" + shape +
                '}';
    }
}
