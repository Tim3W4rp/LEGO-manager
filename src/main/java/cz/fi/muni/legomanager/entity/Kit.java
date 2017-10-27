package cz.fi.muni.legomanager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Kit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private Integer ageLimit;

    @NotNull
    @Column
    private Integer price;

    @NotNull
    @Column
    private String description;

    public Kit() {
    }

    public Kit(Integer ageLimit, Integer price, String description) {
        this.ageLimit = ageLimit;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result +
                (ageLimit == null ? 0 : ageLimit.hashCode()) +
                (price == null ? 0 : price.hashCode()) +
                (description == null ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (! (obj instanceof Kit))
            return false;

        Kit other = (Kit) obj;
        if (ageLimit == null || price == null || description == null) {
            if (other.getAgeLimit() != null || other.getPrice() != null || other.getDescription() != null)
                return false;
        } else if (!ageLimit.equals(other.getAgeLimit()) ||
                   !price.equals(other.getPrice()) ||
                   !description.equals(other.getDescription()))
            return false;
        return true;
    }

}
