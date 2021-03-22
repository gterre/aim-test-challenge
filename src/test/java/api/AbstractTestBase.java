package api;

import config.TestConfig;
import org.testng.annotations.Listeners;
import org.testng.reporters.EmailableReporter2;
import proxies.SkuProxy;

@Listeners(EmailableReporter2.class)
public abstract class AbstractTestBase {

    protected SkuProxy createSkuProxy() {
        return new SkuProxy(TestConfig.SKU_API_DOMAIN, TestConfig.SKU_PROXY_TIMEOUT_SEC);
    }
}
