package pojo.sku;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HTTPHeaders {

    @JsonProperty("date")
    private String date;

    @JsonProperty("server")
    private String server;

    @JsonProperty("content-length")
    private String contentLength;

    @JsonProperty("x-amzn-requestid")
    private String xAmznRequestid;

    @JsonProperty("content-type")
    private String contentType;

    @JsonProperty("connection")
    private String connection;

    @JsonProperty("x-amz-crc32")
    private String xAmzCrc32;

    public String getDate() {
        return date;
    }

    public String getServer() {
        return server;
    }

    public String getContentLength() {
        return contentLength;
    }

    public String getXAmznRequestid() {
        return xAmznRequestid;
    }

    public String getContentType() {
        return contentType;
    }

    public String getConnection() {
        return connection;
    }

    public String getXAmzCrc32() {
        return xAmzCrc32;
    }

    @Override
    public String toString() {
        return "HTTPHeaders{" +
                "date='" + date + '\'' +
                ", server='" + server + '\'' +
                ", contentLength='" + contentLength + '\'' +
                ", xAmznRequestid='" + xAmznRequestid + '\'' +
                ", contentType='" + contentType + '\'' +
                ", connection='" + connection + '\'' +
                ", xAmzCrc32='" + xAmzCrc32 + '\'' +
                '}';
    }
}