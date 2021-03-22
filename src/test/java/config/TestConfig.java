package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class TestConfig {

    // create resolved config to extract properties, load application.conf as defaults then override with env.conf
    private final static Config globalConfig = ConfigFactory.load();
    private final static Config resolvedConfig = ConfigFactory.load(globalConfig.getString("env.conf"))
            .withFallback(globalConfig)
            .resolve();

    public static String SKU_API_DOMAIN = resolvedConfig.getString("sku.api.domain");
    public static int SKU_PROXY_TIMEOUT_SEC = resolvedConfig.getInt("sku.proxy.timeout.sec");
}
