package pojo.sku;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("price")
    private Object price;

    @JsonProperty("description")
    private Object description;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("updatedAt")
    private String updatedAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public Object getPrice() {
        return price;
    }

    public Object getDescription() {
        return description;
    }

    public String getSku() {
        return sku;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Item{" +
                "createdAt='" + createdAt + '\'' +
                ", price=" + price +
                ", description=" + description +
                ", sku='" + sku + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}