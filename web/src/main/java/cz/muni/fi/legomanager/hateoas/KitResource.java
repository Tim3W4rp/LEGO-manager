package cz.muni.fi.legomanager.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitBrickDTO;
import cz.fi.muni.legomanager.dto.KitDTO;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.ArrayList;
import java.util.List;

/**
 * Category rendered to JSON. The @Relation annotation specifies its name in HAL rendering of collections.
 *
 * @author Michal Peška, partly
 */
@Relation(value = "kit", collectionRelation = "kits")
@JsonPropertyOrder({"id", "name"})
public class KitResource extends ResourceSupport {

    @JsonProperty("id") //ResourceSupport already has getId() method
    private Long id;
    private String description;
    private Integer price;
    private Integer ageLimit;
    private CategoryDTO category;
    private List<KitBrickDTO> kitBricks = new ArrayList<>();

    public KitResource(KitDTO dto) {
        this.id = dto.getId();
        this.description = dto.getDescription();
        this.price = dto.getPrice();
        this.ageLimit = dto.getAgeLimit();
        this.category = dto.getCategory();
        this.kitBricks = dto.getKitBricks();
    }

    public Long getDTOId() {
        return id;
    }

    public void setDTOId(Long id) {
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

    public void setPrice(Integer price) { this.price = price; }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) { this.ageLimit = ageLimit; }

}
