package cz.fi.muni.legomanager.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Martin Jordán
 */
@Entity(name = "kitBricks")
public class KitBrick implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idBrick")
    private Brick brick;

    @Id
    @ManyToOne
    @JoinColumn(name = "idKit")
    private Kit kit;

    @Column(name = "count")
    private long count;

    public KitBrick() {
    }

    public KitBrick(Brick brick, Kit kit, long count) {
        this.brick = brick;
        this.kit = kit;
        this.count = count;
    }
}
