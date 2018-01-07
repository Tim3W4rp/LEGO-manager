package cz.fi.muni.legomanager.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class KitBrickDTO {
    private Long id;

    @NotNull
    private BrickDTO brick;

    @NotNull
    private KitDTO kit;

    @NotNull
    @Min(1)
    @Max(Integer.MAX_VALUE)
    private long count;

    public BrickDTO getBrick() {
        return brick;
    }

    public void setBrick(BrickDTO brick) {
        this.brick = brick;
    }

    public KitDTO getKit() {
        return kit;
    }

    public void setKit(KitDTO kit) {
        this.kit = kit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KitBrickDTO that = (KitBrickDTO) o;

        if (count != that.count) return false;
        if (brick != null ? !brick.equals(that.brick) : that.brick != null) return false;
        if (kit != null ? !kit.equals(that.kit) : that.kit != null) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = brick != null ? brick.hashCode() : 0;
        result = 31 * result + (kit != null ? kit.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (int) (count ^ (count >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "KitBrickDTO{" +
                "brick=" + brick +
                ", kit=" + kit +
                ", id=" + id +
                ", count=" + count +
                '}';
    }
}
