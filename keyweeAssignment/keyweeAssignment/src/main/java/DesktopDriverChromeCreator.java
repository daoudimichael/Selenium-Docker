import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/***
 * This class provide a remote WebDriver using chromedriver
 */
public class DesktopDriverChromeCreator {

  private WebDriver driver = null;

  public WebDriver getNewDriver() {

    try {
      DesiredCapabilities dcap = DesiredCapabilities.chrome();
      String driverPath = System.getProperty("user.dir") + "/exe/chromedriver";
      System.setProperty("webdriver.chrome.driver", driverPath);

      // You should check the Port No here.
      URL url = new URL("http://localhost:32768/wd/hub");
      this.driver = new RemoteWebDriver(url, dcap);
    }
    catch (MalformedURLException e) {
      e.printStackTrace();
    }

    return this.driver;
  }
}
