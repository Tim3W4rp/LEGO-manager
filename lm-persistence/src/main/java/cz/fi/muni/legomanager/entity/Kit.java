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
    @Column(nullable = false, name = "age_limit")
    private Integer ageLimit;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @NotNull
    @OneToMany(mappedBy = "kit", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<KitBrick> kitBricks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private SetOfKits setOfKits;

    public Kit() {
    }

    public Kit(String description, Integer price, Integer ageLimit, Category category, List<KitBrick> kitBricks, SetOfKits setOfKits) {
        this.description = description;
        this.price = price;
        this.ageLimit = ageLimit;
        this.category = category;
        this.kitBricks = kitBricks;
        this.setOfKits = setOfKits;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<KitBrick> getKitBricks() {
        return Collections.unmodifiableList(kitBricks);
    }

    public void setKitBricks(List<KitBrick> kitBricks) {
        this.kitBricks = kitBricks;
    }

    public void addKitBrick(KitBrick kitBrick) {
        kitBricks.add(kitBrick);
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SetOfKits getSetOfKits() {
        return setOfKits;
    }

    public void setSetOfKits(SetOfKits setOfKits) {
        this.setOfKits = setOfKits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kit kit = (Kit) o;

        if (id != null ? !id.equals(kit.id) : kit.id != null) return false;
        if (description != null ? !description.equals(kit.description) : kit.description != null) return false;
        if (price != null ? !price.equals(kit.price) : kit.price != null) return false;
        if (ageLimit != null ? !ageLimit.equals(kit.ageLimit) : kit.ageLimit != null) return false;
        if (category != null ? !category.equals(kit.category) : kit.category != null) return false;
        if (kitBricks != null ? !kitBricks.equals(kit.kitBricks) : kit.kitBricks != null) return false;
        return setOfKits != null ? setOfKits.equals(kit.setOfKits) : kit.setOfKits == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (ageLimit != null ? ageLimit.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (kitBricks != null ? kitBricks.hashCode() : 0);
        result = 31 * result + (setOfKits != null ? setOfKits.hashCode() : 0);
        return result;
    }
}
