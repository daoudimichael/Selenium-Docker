import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/***
 * Smoke test on a shorten service - Bitly.com
 * Test steps:
 * 1. Go to https://bitly.com/
 * 2. Shorten a link - www.google.com (default)
 * 3. Verify the result expands to original link
 */
public class BitlySmokeTest {

  private WebDriver driver;
  private BitlyPageManager bitlyPageManager;
  private PropertiesProvider propertiesProvider;

  private static final Logger LOGGER = LogManager.getLogger(BitlySmokeTest.class);
  private static final String BITLY_URL = "https://bitly.com/";

  @BeforeTest
  public void init() {
    this.driver = new DesktopDriverChromeCreator().getNewDriver();
    this.bitlyPageManager = new BitlyPageManager(this.driver);
    this.propertiesProvider = PropertiesProvider.getInstance();
  }

  @Test
  public void bitlyTest() {
    try {
      LOGGER.debug("******************** Test start ********************");
      LOGGER.debug(String.format("1. Go to [%s]", BITLY_URL));
      String currentUrl = this.bitlyPageManager.goTo(BITLY_URL);
      Assert.assertEquals(BITLY_URL, currentUrl, String.format("Bitly page should be opened! URL: [%s]", BITLY_URL));

      LOGGER.debug(String.format("2. Start to trying shorten the URL: [%s]", this.propertiesProvider.getUrlToShorten()));
      String shortenUrl = this.bitlyPageManager.shortenThisPath(this.propertiesProvider.getUrlToShorten());
      Assert.assertFalse(shortenUrl.length() == 0, "The shorten path can be empty!");
      LOGGER.debug(String.format("Got shorten URL: [%s]", shortenUrl));

      LOGGER.debug("3. Verify the result expands to original link");
      String secondCurrentUrl = this.bitlyPageManager.goTo(shortenUrl, this.propertiesProvider.getUrlToShorten());
      Assert.assertEquals(secondCurrentUrl, this.propertiesProvider.getUrlToShorten(), "Got results not as expected!");


      LOGGER.debug(String.format("Test finish successfully! got shorten URL [%s] from origin URL [%s]", shortenUrl, this.propertiesProvider.getUrlToShorten()));
    }
    catch (Exception e) {
      LOGGER.error("Exception happened during the test flow, reason: ", e);
      Assert.fail("", e);
    }
  }

  @AfterTest
  public void close() {
    this.driver.close();
    LOGGER.debug("******************** Test end ********************");
  }
}
