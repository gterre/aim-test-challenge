package proxies;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public abstract class AbstractProxy {

    private final String domain;
    private final TimeUnit timeoutTimeUnit;
    private final int timeoutValue;

    /**
     * @param domain          ie... https://my.domain.com
     * @param timeoutTimeUnit time unit for timeout value
     * @param timeoutValue    timeout value
     */
    public AbstractProxy(String domain, TimeUnit timeoutTimeUnit, int timeoutValue) {
        this.domain = domain;
        this.timeoutTimeUnit = timeoutTimeUnit;
        this.timeoutValue = timeoutValue;
    }

    protected FilterableRequestSpecification createClient() {
        return (FilterableRequestSpecification) createClient(this.timeoutTimeUnit, this.timeoutValue).baseUri(domain);
    }

    protected Response execute(Function<String, Response> httpMethod, String path) {
        Response response = httpMethod.apply(path);
        response.then().log().all();
        return response;
    }

    private RestAssuredConfig createConfig(TimeUnit timeUnit, int value) {
        RestAssuredConfig config = RestAssured.config().httpClient(
                HttpClientConfig.httpClientConfig()
                        .setParam("http.conn-manager.timeout", timeUnit.toMillis(value))
                        .setParam("http.connection.timeout", (int) timeUnit.toMillis(value))
                        .setParam("http.socket.timeout", (int) timeUnit.toMillis(value))
        );
        return config;
    }

    private RequestSpecification createClient(TimeUnit timeUnit, int value) {
        return RestAssured.given()
                .config(createConfig(timeUnit, value))
                .relaxedHTTPSValidation()
                .when().log().all();
    }
}