package cz.muni.fi.legomanager.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

/**
 * Shape rendered to JSON. The @Relation annotation specifies its name in HAL rendering of collections.
 *
 * @author Lukáš Dvořák
 */
@Relation(value = "shape", collectionRelation = "shapes")
@JsonPropertyOrder({"id", "name"})
public class ShapeResource extends ResourceSupport {

    @JsonProperty("id") //ResourceSupport already has getId() method
    private long dtoId;
    private String name;


}
