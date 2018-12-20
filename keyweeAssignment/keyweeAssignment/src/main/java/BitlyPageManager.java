import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/***
 * This class provide steps action on the browser using Bitly.com elements
 */
public class BitlyPageManager {

  private final WebDriver driver;
  private final WebDriverWait wait;

  private static final Logger LOGGER = LogManager.getLogger(BitlyPageManager.class);

  @FindBy(id = "shorten_url")
  private WebElement inputPathField;

  @FindBy(id = "shorten_btn")
  private WebElement shortenBtn;

  @FindBy(className = "short-url")
  private WebElement shortenUrl;

  public BitlyPageManager(final WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    this.wait = new WebDriverWait(driver, 30);
  }

  /**
   * Go to specific URL
   * @param path specific URL
   * @return the current URL on browser
   */
  public String goTo(String path) {
    String currentUrl = "";

    LOGGER.debug(String.format("Go to: [%s]", path));

    try {
      this.driver.get(path);
      this.wait.until(ExpectedConditions.urlToBe(path));
      currentUrl = this.driver.getCurrentUrl();
    }
    catch (Exception e) {
      LOGGER.error("Exception happened during get web page, reason: ", e);
    }

    return currentUrl;
  }

  /***
   * Go to specific URL
   * @param path specific URL
   * @param expectedUrl the URL we expect that browser will open
   * @return the current URL on browser
   */
  public String goTo(String path, String expectedUrl) {
    String currentUrl = "";

    LOGGER.debug(String.format("Go to: [%s] expected to: [%s]", path, expectedUrl));

    try {
      this.driver.get(path);
      this.wait.until(ExpectedConditions.urlToBe(expectedUrl));
      currentUrl = this.driver.getCurrentUrl();
    }
    catch (Exception e) {
      LOGGER.error("Exception happened during get origin web page, reason: ", e);
    }

    return currentUrl;
  }

  /***
   * Shorten URL using Bitly.com
   * @param originalPath the URL who be shorten
   * @return the shorten URL
   */
  public String shortenThisPath(String originalPath) {
    String shortenUrl = "";

    LOGGER.debug(String.format("Trying to shorten URL: [%s]", originalPath));

    try {
      TimeUnit.SECONDS.sleep(5);
      wait.until(ExpectedConditions.visibilityOf(this.inputPathField));
      this.inputPathField.sendKeys(originalPath);
      wait.until(ExpectedConditions.elementToBeClickable(this.shortenBtn));
      this.shortenBtn.click();

      wait.until(ExpectedConditions.visibilityOf(this.shortenUrl));
      shortenUrl = this.shortenUrl.getText();
    }
    catch (Exception e) {
      LOGGER.error("Exception happened during shorten original url, reason: ", e);
    }
    return shortenUrl;
  }

}
