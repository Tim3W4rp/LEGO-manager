package cz.fi.muni.legomanager.dto;

import java.util.Objects;

/**
 * DTO for Shape entity.
 *
 * @author Lukáš Dvořák
 */
public class ShapeDTO {

    private Long id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShapeDTO)) return false;
        ShapeDTO shapeDTO = (ShapeDTO) o;
        return Objects.equals(id, shapeDTO.id) &&
                Objects.equals(name, shapeDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ShapeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
