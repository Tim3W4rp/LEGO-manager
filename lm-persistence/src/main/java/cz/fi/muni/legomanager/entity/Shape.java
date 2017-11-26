package cz.fi.muni.legomanager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class for representing brick shapes.
 *
 * @author Lukáš Dvořák
 */
@Entity
public class Shape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    @OneToMany(mappedBy = "shape")
    private List<Brick> bricks = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Shape)) {
            return false;
        }

        Shape other = (Shape) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        final int prime = 59;
        int result = 3;
        result = prime * result +
                (name == null ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Brick> getBricks() {
        return Collections.unmodifiableList(bricks);
    }

    public void addBrick(Brick brick) {
        bricks.add(brick);
    }

    public void removeBrick(Brick brick) {
        bricks.remove(brick);
    }
}
