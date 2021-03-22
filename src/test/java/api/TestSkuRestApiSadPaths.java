package api;

import org.testng.annotations.Test;
import pojo.sku.GetSkuResponse;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestSkuRestApiSadPaths extends AbstractTestBase {

    @Test
    public void testGetSku_skuDoesNotExist_returns200WithItemNotSet() {
        GetSkuResponse GetSkuResponse = createSkuProxy().getSku(UUID.randomUUID().toString()).then()
                .statusCode(200)
                .extract().response().as(GetSkuResponse.class);

        // assert no longer exists
        assertThat(GetSkuResponse.getItem(), nullValue());
    }

    @Test
    public void testDeleteSku_skuDoesNotExist_returns404() {
        createSkuProxy().deleteSku(UUID.randomUUID().toString()).then()
                .statusCode(404)
                .extract().response();
    }

    @Test(dataProvider = "dataSetFor400RequestForCreateSku", dataProviderClass = SkuDataProvider.class)
    public void testCreateSku_invalidData_returns400(String purpose, String payload) {
        // purpose is ignored, this describes the test and is printed out in the
        // emailable-report.html on failure. If the payload is complex, it might be hard to determine the purpose
        // of the test which is why a purpose is passed but ignored
        createSkuProxy()
                .createSku(payload).then()
                .statusCode(400)
                .extract().response();
    }
}
