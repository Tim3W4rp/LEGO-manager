package cz.fi.muni.legomanager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @Column(nullable = false)
    private Integer price;

    @NotNull
    @Column(nullable = false)
    private Integer ageLimit;

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

        if (! (obj instanceof Kit)) {
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
