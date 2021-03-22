package proxies;

import io.restassured.response.Response;

import java.util.concurrent.TimeUnit;

public class SkuProxy extends AbstractProxy {

    private static String BASE_PATH = "/dev/skus";

    /**
     * @param domain  ie... https://1ryu4whyek.execute-api.us-west-2.amazonaws.com
     * @param timeout timeout value in seconds
     */
    public SkuProxy(String domain, int timeout) {
        super(domain, TimeUnit.SECONDS, timeout);
    }

    /**
     * get all skus
     *
     * @return {@link Response}
     */
    public Response getSkus() {
        return execute(createClient()::get, BASE_PATH);
    }

    /**
     * get the specified sku
     *
     * @param sku the sku
     * @return {@link Response}
     */
    public Response getSku(String sku) {
        return execute(createClient()::get, BASE_PATH + "/" + sku);
    }

    /**
     * create sku
     *
     * @param payload the json string
     * @return {@link Response}
     */
    public Response createSku(String payload) {
        return execute(createClient().body(payload)::post, BASE_PATH);
    }

    /**
     * delete the specified sku
     *
     * @param sku the sku
     * @return {@link Response}
     */
    public Response deleteSku(String sku) {
        return execute(createClient()::delete, BASE_PATH + "/" + sku);
    }
}
