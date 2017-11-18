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
    private Long id;

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
    @OneToMany(mappedBy = "kit")
    private List<KitBrick> kitBricks = new ArrayList<>();

    @NotNull
    @ManyToOne
    private SetOfKits setOfKits;

    public Kit() {
    }

    public Kit(Long id, String description, Integer price, Integer ageLimit) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.ageLimit = ageLimit;
    }

    public Long getId() {
        return id;
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

    //todo test
    public void addBrick(Brick brick) {
        for (KitBrick kitBrick : kitBricks) {
            if (kitBrick.getBrick().equals(brick)) {
                kitBrick.increaseCountByOne();
                return;
            }
        }
        kitBricks.add(new KitBrick(brick, this, 1));
    }

    //todo test
    public void removeBrick(Brick brick) {
        for (KitBrick kitBrick : kitBricks) {
            if (kitBrick.getBrick().equals(brick)) {
                kitBrick.decreaseCountByOne();
                return;
            }
        }
        throw new RuntimeException("Such brick does not exist in this kit");
    }

    //todo test
    public void removeAllBricksOfThisType(Brick brick) {
        for (KitBrick kitBrick : kitBricks) {
            if (kitBrick.getBrick().equals(brick)) {
                kitBricks.remove(kitBrick);
                return;
            }
        }
        throw new RuntimeException("Such brick does not exist in this kit");
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
