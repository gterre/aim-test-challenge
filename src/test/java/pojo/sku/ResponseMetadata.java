package pojo.sku;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseMetadata {

    @JsonProperty("HTTPHeaders")
    private HTTPHeaders hTTPHeaders;

    @JsonProperty("RequestId")
    private String requestId;

    @JsonProperty("HTTPStatusCode")
    private int hTTPStatusCode;

    @JsonProperty("RetryAttempts")
    private int retryAttempts;

    public HTTPHeaders getHTTPHeaders() {
        return hTTPHeaders;
    }

    public String getRequestId() {
        return requestId;
    }

    public int getHTTPStatusCode() {
        return hTTPStatusCode;
    }

    public int getRetryAttempts() {
        return retryAttempts;
    }

    @Override
    public String toString() {
        return "ResponseMetadata{" +
                "hTTPHeaders=" + hTTPHeaders +
                ", requestId='" + requestId + '\'' +
                ", hTTPStatusCode=" + hTTPStatusCode +
                ", retryAttempts=" + retryAttempts +
                '}';
    }
}