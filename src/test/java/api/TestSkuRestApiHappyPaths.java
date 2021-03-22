package api;

import org.testng.annotations.Test;
import pojo.sku.GetSkuResponse;
import pojo.sku.Item;
import pojo.sku.ResponseMetadata;
import proxies.SkuProxy;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static proxies.PayloadUtil.createSkuPayload;

public class TestSkuRestApiHappyPaths extends AbstractTestBase {

    private final static String NEW_SKU = UUID.randomUUID().toString();
    private final static String NEW_SKU_DESCRIPTION = "random UUID sku";
    private final static String NEW_SKU_PRICE = "9.99";

    @Test
    public void testCreateSku() {
        String payload = createSkuPayload(NEW_SKU, NEW_SKU_DESCRIPTION, NEW_SKU_PRICE);
        createSkuProxy()
                .createSku(payload).then()
                .statusCode(200)
                .extract().response();
    }

    @Test(dependsOnMethods = "testCreateSku")
    public void testGetSku() {
        Instant start = Instant.now();
        final int expectedStatusCode = 200;

        GetSkuResponse getSkuResponse = createSkuProxy()
                .getSku(NEW_SKU).then()
                .statusCode(expectedStatusCode)
                .body(matchesJsonSchemaInClasspath("schemas/GetSkuResponseSchema.json"))
                .extract().response().as(GetSkuResponse.class);

        // assert
        verifyItem(getSkuResponse.getItem(), start);

        // assert non-input other values
        ResponseMetadata responseMetadata = getSkuResponse.getResponseMetadata();
        assertThat(responseMetadata.getHTTPStatusCode(), equalTo(expectedStatusCode));
        // TODO : add pattern value checking in schema and more assertions - omitting for test-challenge
    }

    @Test(dependsOnMethods = "testCreateSku")
    public void testGetSkus() {
        Instant start = Instant.now();

        Item[] items = createSkuProxy()
                .getSkus().then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/GetSkusResponseSchema.json"))
                .extract().response().as(Item[].class);

        List<Item> actual = Arrays.stream(items).filter(x -> x.getSku().equals(NEW_SKU)).collect(Collectors.toList());
        assertThat(actual, hasSize(1));
        verifyItem(actual.get(0), start);
    }

    @Test(dependsOnMethods = {"testGetSkus", "testGetSku", "testCreateSku"})
    public void testDeleteSku() {
        final int expectedStatusCode = 200;
        SkuProxy skuProxy = createSkuProxy();

        // first delete
        skuProxy.deleteSku(NEW_SKU).then()
                .statusCode(expectedStatusCode)
                .extract().response();

        // then get sku
        GetSkuResponse getSkuResponse = skuProxy.getSku(NEW_SKU).then()
                .statusCode(expectedStatusCode)
                .extract().response().as(GetSkuResponse.class);

        // assert no longer exists
        assertThat(getSkuResponse.getItem(), nullValue());
    }

    private void verifyItem(Item item, Instant start) {
        assertThat(item, notNullValue());
        assertThat(item.getSku(), equalTo(NEW_SKU));
        assertThat(item.getDescription(), equalTo(NEW_SKU_DESCRIPTION));
        assertThat(item.getPrice(), equalTo(NEW_SKU_PRICE));
        // TODO : non-input values, ie... created / updated datetime values, omitting for test-challenge
        // sudo code for now
        // value pattern already checked in schema validator,
        // compare created / updated value is between start and Instant.now()
    }
}