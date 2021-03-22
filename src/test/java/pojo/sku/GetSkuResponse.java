package pojo.sku;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetSkuResponse {

    @JsonProperty("Item")
    private Item item;

    @JsonProperty("ResponseMetadata")
    private ResponseMetadata responseMetadata;

    public Item getItem() {
        return item;
    }

    public ResponseMetadata getResponseMetadata() {
        return responseMetadata;
    }

    @Override
    public String toString() {
        return "GetSkuResponse{" +
                "item=" + item +
                ", responseMetadata=" + responseMetadata +
                '}';
    }
}