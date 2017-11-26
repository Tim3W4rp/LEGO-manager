package cz.fi.muni.legomanager.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * DTO for Shape entity.
 *
 * @author Lukáš Dvořák
 */
public class ShapeDTO {

    private Long id;
    private String name;
    private List<BrickDTO> bricks = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BrickDTO> getBricks() {
        return Collections.unmodifiableList(bricks);
    }

    public void addBrick(BrickDTO brick) {
        bricks.add(brick);
    }

    public void removeBrick(BrickDTO brick) {
        bricks.remove(brick);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShapeDTO)) return false;
        ShapeDTO shapeDTO = (ShapeDTO) o;
        return Objects.equals(id, shapeDTO.id) &&
                Objects.equals(name, shapeDTO.name) &&
                Objects.equals(bricks, shapeDTO.bricks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bricks);
    }

    @Override
    public String toString() {
        return "ShapeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bricks=" + bricks +
                '}';
    }

}
