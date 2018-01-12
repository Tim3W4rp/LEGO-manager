package cz.fi.muni.legomanager.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class KitCreateDTO {

    @NotNull
    @Size(min = 1)
    private String description;

    @NotNull
    @Min(1)
    @Max(Integer.MAX_VALUE)
    private Integer price;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer ageLimit;

    @NotNull
    private CategoryDTO category;

    @NotNull
    private List<BrickDTO> bricks= new ArrayList<>();

    private List<KitBrickDTO> kitBricks = new ArrayList<>();

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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public List<BrickDTO> getBricks() {
        return bricks;
    }

    public void setBricks(List<BrickDTO> bricks) {
        this.bricks = bricks;
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

        KitCreateDTO that = (KitCreateDTO) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return ageLimit != null ? ageLimit.equals(that.ageLimit) : that.ageLimit == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (ageLimit != null ? ageLimit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "KitCreateDTO{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", ageLimit=" + ageLimit +
                '}';
    }
}
