package cz.fi.muni.legomanager.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Martin Jordán
 */
public class KitDTO {
    private Long id;
    private String description;
    private Integer price;
    private Integer ageLimit;
    private Set<CategoryDTO> categories = new HashSet<>();
    private List<KitBrickDTO> kitBricks = new ArrayList<>();

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<KitBrickDTO> getKitBricks() {
        return kitBricks;
    }

    public void setKitBricks(List<KitBrickDTO> kitBricks) {
        this.kitBricks = kitBricks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KitDTO kitDTO = (KitDTO) o;

        if (id != null ? !id.equals(kitDTO.id) : kitDTO.id != null) return false;
        if (description != null ? !description.equals(kitDTO.description) : kitDTO.description != null) return false;
        if (price != null ? !price.equals(kitDTO.price) : kitDTO.price != null) return false;
        if (ageLimit != null ? !ageLimit.equals(kitDTO.ageLimit) : kitDTO.ageLimit != null) return false;
        if (categories != null ? !categories.equals(kitDTO.categories) : kitDTO.categories != null) return false;
        return kitBricks != null ? kitBricks.equals(kitDTO.kitBricks) : kitDTO.kitBricks == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (ageLimit != null ? ageLimit.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + (kitBricks != null ? kitBricks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "KitDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", ageLimit=" + ageLimit +
                ", categories=" + categories +
                ", kitBricks=" + kitBricks +
                '}';
    }
}