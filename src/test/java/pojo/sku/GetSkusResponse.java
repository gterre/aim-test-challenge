package pojo.sku;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetSkusResponse {

    @JsonProperty("Response")
    private List<Item> response;

    public List<Item> getResponse() {
        return response;
    }
}