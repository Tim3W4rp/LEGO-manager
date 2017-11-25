package cz.fi.muni.legomanager.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Martin Jordán
 */
@Entity
@Table(name = "kit_bricks")
public class KitBrick implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "brick")
    private Brick brick;

    @ManyToOne
    @JoinColumn(name = "kit")
    private Kit kit;

    @Column(nullable = false, name = "count")
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

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KitBrick kitBrick = (KitBrick) o;

        return count == kitBrick.count && brick.equals(kitBrick.brick) && kit.equals(kitBrick.kit);
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
