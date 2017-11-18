package cz.fi.muni.legomanager.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Martin Jordán
 */
@Entity
@Table(name = "kitBricks")
public class KitBrick implements Serializable {

    @ManyToOne
    @JoinColumn(name = "idBrick")
    private Brick brick;

    @ManyToOne
    @JoinColumn(name = "idKit")
    private Kit kit;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count")
    private long count;

    public KitBrick() {
    }

    public KitBrick(Brick brick, Kit kit, long count) {
        this.brick = brick;
        this.kit = kit;
        this.count = count;
    }

    public Brick getBrick() {
        return brick;
    }

    public Kit getKit() {
        return kit;
    }

    public long getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KitBrick kitBrick = (KitBrick) o;

        if (count != kitBrick.count) return false;
        if (!brick.equals(kitBrick.brick)) return false;
        return kit.equals(kitBrick.kit);
    }

    @Override
    public int hashCode() {
        int result = brick.hashCode();
        result = 31 * result + kit.hashCode();
        result = 31 * result + (int) (count ^ (count >>> 32));
        return result;
    }

    public void increaseCountByOne() {
        count++;
    }

    public void decreaseCountByOne() {
        if (count == 0) {
            throw new RuntimeException("Count is zero");
        }
        count--;
    }

}
