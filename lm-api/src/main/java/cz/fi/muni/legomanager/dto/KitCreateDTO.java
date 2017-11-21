package cz.fi.muni.legomanager.dto;

public class KitCreateDTO {
    private String description;
    private Integer price;
    private Integer ageLimit;

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