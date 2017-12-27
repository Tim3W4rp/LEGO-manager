package cz.fi.muni.legomanager.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * DTO for Brick entity.
 *
 * @author Michal Peška
 */
public class RandomBricksDTO {

    private List<BrickDTO> bricks = new ArrayList<>();
    private int min;
    private int max;

    public void setMin(int value) {
        min = value;
    }

    public int getMin() {
        return min;
    }

    public void setMax(int value) {
        max = value;
    }

    public int getMax() {
        return max;
    }

    public List<BrickDTO> getBricks() {
        return Collections.unmodifiableList(bricks);
    }

    public void addBrick(BrickDTO brick) {
        bricks.add(brick);
    }

    public void removeBrick(BrickDTO brick) {
        bricks.remove(brick);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RandomBricksDTO)) return false;
        RandomBricksDTO brickDTO = (RandomBricksDTO) o;

        return bricks.size() == brickDTO.bricks.size() &&
                min == brickDTO.min && max == brickDTO.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, bricks);
    }

    @Override
    public String toString() {
        return "Random Brick counts";
    }

}
