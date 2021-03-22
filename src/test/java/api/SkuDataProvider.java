package api;

import org.testng.annotations.DataProvider;

import static org.json.JSONObject.NULL;
import static proxies.PayloadUtil.createSkuPayload;

public class SkuDataProvider {

    private final static String VALID_SKU = "sku";
    private final static String VALID_DESCRIPTION = "description";
    private final static int VALID_PRICE = 1;

    private final static boolean BOOL = true;
    private final static int INT = 1;
    private final static String STRING = "string";
    private final static double DOUBLE = 1.1;
    private final static Object UNDEFINED = null;

    @DataProvider(name = "dataSetFor400RequestForCreateSku", parallel = true)
    public Object[][] dataSetFor400RequestForCreateSku() {
        return new Object[][]{
                {"sku is not set", createSkuPayload(UNDEFINED, VALID_DESCRIPTION, VALID_PRICE)},
                {"sku is null", createSkuPayload(NULL, VALID_DESCRIPTION, VALID_PRICE)},
                {"sku is int", createSkuPayload(INT, VALID_DESCRIPTION, VALID_PRICE)},
                {"sku is double", createSkuPayload(DOUBLE, VALID_DESCRIPTION, VALID_PRICE)},
                {"sku is bool", createSkuPayload(BOOL, VALID_DESCRIPTION, VALID_PRICE)},
                {"description is not set", createSkuPayload(VALID_SKU, UNDEFINED, VALID_PRICE)},
                {"description is null", createSkuPayload(VALID_SKU, NULL, VALID_PRICE)},
                {"description is int", createSkuPayload(VALID_SKU, INT, VALID_PRICE)},
                {"description is double", createSkuPayload(VALID_SKU, DOUBLE, VALID_PRICE)},
                {"description is bool", createSkuPayload(VALID_SKU, BOOL, VALID_PRICE)},
                {"price is not set", createSkuPayload(VALID_SKU, VALID_DESCRIPTION, UNDEFINED)},
                {"price is null", createSkuPayload(VALID_SKU, VALID_DESCRIPTION, NULL)},
                {"price is string", createSkuPayload(VALID_SKU, VALID_DESCRIPTION, STRING)},
                {"price is bool", createSkuPayload(VALID_SKU, VALID_DESCRIPTION, BOOL)},
        };
    }
}
