package cz.fi.muni.legomanager.dto;

import java.util.Objects;

/**
 * @author Lukáš Dvořák
 */
public class ShapeCreateDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShapeCreateDTO)) return false;
        ShapeCreateDTO that = (ShapeCreateDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ShapeCreateDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
