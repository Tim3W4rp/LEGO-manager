package cz.fi.muni.legomanager.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * DTO for Brick entity.
 *
 * @author Lukáš Dvořák
 */
public class BrickDTO {

    private Long id;
    private int red;
    private int green;
    private int blue;
    private List<KitBrickDTO> kitBricks = new ArrayList<>();
    private ShapeDTO shape;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<KitBrickDTO> getKitBricks() {
        return Collections.unmodifiableList(kitBricks);
    }

    public void setKitBricks(List<KitBrickDTO> kitBricks) {
        this.kitBricks = kitBricks;
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
        if (!(o instanceof BrickDTO)) return false;
        BrickDTO brickDTO = (BrickDTO) o;
        return red == brickDTO.red &&
                green == brickDTO.green &&
                blue == brickDTO.blue &&
                Objects.equals(id, brickDTO.id) &&
                Objects.equals(shape, brickDTO.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, red, green, blue, shape);
    }

    @Override
    public String toString() {
        return "BrickDTO{" +
                "id=" + id +
                ", red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", kitBricks=" + kitBricks +
                ", shape=" + shape +
                '}';
    }

}
