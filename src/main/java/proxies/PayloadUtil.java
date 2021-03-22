package proxies;

import org.json.JSONObject;

/**
 * Utility class to create payloads, consider moving to builders instead of static methods as json object complexity
 * grows.
 */
public class PayloadUtil {

    /**
     * use null to skip setting json property, otherwise use JSONObject.NULL
     *
     * @param sku         the sku
     * @param description the description
     * @param price       the price
     * @return the json payload string
     */
    public static String createSkuPayload(Object sku, Object description, Object price) {
        JSONObject jsonObject = new JSONObject();
        put(jsonObject, "sku", sku);
        put(jsonObject, "description", description);
        put(jsonObject, "price", price);
        return jsonObject.toString();
    }

    /**
     * abstract skip property logic, a missing propery is not the same as setting it to null.
     * use null to skip setting json property, otherwise use JSONObject.NULL
     */
    private static JSONObject put(JSONObject jsonObject, String key, Object value) {
        if (key != null) {
            jsonObject.put(key, value);
        }
        return jsonObject;
    }
}
