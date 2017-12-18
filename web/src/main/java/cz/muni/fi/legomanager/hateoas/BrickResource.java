package cz.muni.fi.legomanager.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import cz.fi.muni.legomanager.dto.*;
import cz.fi.muni.legomanager.dto.ShapeDTO;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Category rendered to JSON. The @Relation annotation specifies its name in HAL rendering of collections.
 *
 * @author Michal Peška, partly
 */
@Relation(value = "brick", collectionRelation = "bricks")
@JsonPropertyOrder({"id", "name"})
public class BrickResource extends ResourceSupport {

    @JsonProperty("id") //ResourceSupport alrerady has getId() method
    private Long dtoId;
    private int red;
    private int green;
    private int blue;
    private List<KitBrickDTO> kitBricks = new ArrayList<>();
    private ShapeDTO shape;

    public BrickResource(BrickDTO dto) {
        this.dtoId = dto.getId();
        this.red = dto.getRed();
        this.green = dto.getRed();
        this.blue = dto.getBlue();
        this.kitBricks = dto.getKitBricks();
        this.shape = dto.getShape();
    }

    public Long getDtoId() {
        return dtoId;
    }

    public void setDtoId(Long dtoId) {
        this.dtoId = dtoId;
    }

    public int getDtoRed() {
        return red;
    }

    public void setDtoRed(int value) {
        this.red = value;
    }

    public int getDtoGreen() {
        return green;
    }

    public void setDtoGreen(int value) {
        this.green = value;
    }

    public int getDtoBlue() {
        return blue;
    }

    public void setDtoBlue(int value) {
        this.blue = value;
    }


    public ShapeDTO getDtoShape() {
        return shape;
    }

    public void setDtoShape(ShapeDTO shape) {
        this.shape = shape;
    }


    /*public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) { this.price = price; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }*/
}
