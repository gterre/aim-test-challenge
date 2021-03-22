package api;

import org.testng.annotations.Test;

import java.util.UUID;

import static proxies.PayloadUtil.createSkuPayload;

public class TestSkuRestApiHappyPaths extends AbstractTestBase {

    private final static String NEW_SKU = UUID.randomUUID().toString();

    @Test
    public void testCreateSku() {
        String payload = createSkuPayload(NEW_SKU, "random UUID sku", "9.99");
        createSkuProxy()
                .createSku(payload).then()
                .statusCode(200)
                .extract().response();
    }

    @Test(dependsOnMethods = "testCreateSku")
    public void testGetSku() {
        createSkuProxy()
                .getSku(NEW_SKU).then()
                .statusCode(200)
                .extract().response();
    }

    @Test(dependsOnMethods = "testCreateSku")
    public void testGetSkus() {
        createSkuProxy()
                .getSkus().then()
                .statusCode(200)
                .extract().response();
    }

    @Test(dependsOnMethods = {"testGetSku", "testGetSkus"})
    public void testDeleteSku() {
        createSkuProxy()
                .deleteSku(NEW_SKU).then()
                .statusCode(200)
                .extract().response();
    }
}