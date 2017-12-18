package cz.muni.fi.legomanager.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import cz.fi.muni.legomanager.dto.CategoryDTO;
import cz.fi.muni.legomanager.dto.KitSimpleDTO;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.util.ArrayList;
import java.util.List;

/**
 * Category rendered to JSON. The @Relation annotation specifies its name in HAL rendering of collections.
 *
 * @author Štěpán Granát
 */
@Relation(value = "category", collectionRelation = "categories")
@JsonPropertyOrder({"id", "name"})
public class CategoryResource extends ResourceSupport {

    @JsonProperty("id") //ResourceSupport alrerady has getId() method
    private long dtoId;
    private String name;
    private String description;
    private List<KitSimpleDTO> kits;

    public CategoryResource(CategoryDTO dto) {
        this.dtoId = dto.getId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.kits = dto.getKits();
    }

    public long getDtoId() {
        return dtoId;
    }

    public void setDtoId(long dtoId) {
        this.dtoId = dtoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<KitSimpleDTO> getKits() {
        return kits;
    }

    public void setKits(List<KitSimpleDTO> kits) {
        this.kits = kits;
    }
}
