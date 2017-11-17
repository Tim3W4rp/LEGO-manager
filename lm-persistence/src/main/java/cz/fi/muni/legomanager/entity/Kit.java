package cz.fi.muni.legomanager.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author Martin Jordán
 */
@Entity
public class Kit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idKit;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer price;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer ageLimit;

    @NotNull
    @ManyToMany(mappedBy = "kits")
    private Set<Category> categories = new HashSet<>();

    @NotNull
    @ManyToMany
    private List<Brick> bricks = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "kit")
    private List<KitBrick> kitBricks = new ArrayList<>();

    @NotNull
    @ManyToOne
    private SetOfKits setOfKits;

    public Kit() {
    }

    public Kit(Long idKit, String description, Integer price, Integer ageLimit) {
        this.idKit = idKit;
        this.description = description;
        this.price = price;
        this.ageLimit = ageLimit;
    }

    public Long getIdKit() {
        return idKit;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
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

    public void removeBrickById(long id) {
        bricks.remove(findBrickById(id));
    }

    public Brick findBrickById(long id) {
        for (Brick brick: bricks) {
            if (brick.getIdBrick() == id) {
                return brick;
            }
        }
        return null;
    }

    public SetOfKits getSetOfKits() {
        return setOfKits;
    }

    public void setSetOfKits(SetOfKits shape) {
        this.setOfKits = setOfKits;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +
                (description == null ? 0 : description.hashCode()) +
                (price == null ? 0 : price.hashCode()) +
                (ageLimit == null ? 0 : ageLimit.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Kit)) {
            return false;
        }

        Kit other = (Kit) obj;
        if (description == null || price == null || ageLimit == null) {
            if (other.getDescription() != null || other.getPrice() != null || other.getAgeLimit() != null) {
                return false;
            }
        } else if (!description.equals(other.getDescription()) ||
                !price.equals(other.getPrice()) ||
                !ageLimit.equals(other.getAgeLimit())) {
            return false;
        }
        return true;
    }

}
