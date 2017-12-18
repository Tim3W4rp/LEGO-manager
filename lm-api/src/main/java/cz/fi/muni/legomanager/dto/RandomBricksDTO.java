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
    private List<Long> counts = new ArrayList<>();
    private Long min;
    private Long max;

    public void setMin(Long value) { min = value; }
    public Long getMin() { return min; }

    public void setMax(Long value) { max = value; }
    public Long getMax() { return max; }

    public List<Long> getCounts() { return counts; }

    public void addCounts(Long value) {
        counts.add(value);
    }

    public void removeCounts(Long value) {
        counts.remove(value);
    }

    public List<BrickDTO> getBricks() { return bricks; }

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
        boolean equality = bricks.size() == brickDTO.bricks.size() &&
                counts.size() == brickDTO.counts.size() &&
                min == brickDTO.min && max == brickDTO.max;

        return equality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, bricks, counts);
    }

    @Override
    public String toString() {
        return "Random Brick counts";
    }

}
