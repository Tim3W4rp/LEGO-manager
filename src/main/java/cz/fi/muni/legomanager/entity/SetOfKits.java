package cz.fi.muni.legomanager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Michal Peška
 */
@Entity
public class SetOfKits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String description;

    @NotNull
    private BigDecimal price;

    @ManyToMany(mappedBy = "setsOfKits")
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "setOfKits")
    private Set<Kit> kits = new HashSet<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
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

    public Set<Kit> getKits() {
        return Collections.unmodifiableSet(kits);
    }

    public void addKit(Kit kit) {
        kits.add(kit);
    }

    public void removeKit(Kit kit) {
        kits.remove(kit);
    }

    @Override
    public int hashCode() {
        int result = 0;
        int primeNumber = 13;

        if (this.description != null) {
            result = this.description.hashCode();
        }
        if (this.price != null) {
            result += this.price.hashCode() * primeNumber;
        }
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
        if (!(obj instanceof SetOfKits)) {
            return false;
        }
        SetOfKits otherObject = (SetOfKits) obj;

        if (this.price.equals(otherObject.price)
                && this.description.equals(otherObject.description)) {
            return true;
        }
        return false;
    }
}
