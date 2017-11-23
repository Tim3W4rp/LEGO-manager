package cz.fi.muni.legomanager.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetOfKitsDTO {
    private Long id;
    private String description;
    private BigDecimal price;
    private List<KitDTO> kits = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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


    public List<KitDTO> getKits() {
        return kits;
    }

    public void setKits(List<KitDTO> kits) {
        this.kits = kits;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SetOfKitsDTO that = (SetOfKitsDTO) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SetOfKitsDTO{" +
                "description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
