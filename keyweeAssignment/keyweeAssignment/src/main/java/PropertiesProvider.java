import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/***
 * This class manage and provide properties's test.
 * - Singleton Class
 */
public class PropertiesProvider {

  private String localPort;
  private String ipAddress;
  private String urlToShorten;

  private static PropertiesProvider _instance = null;

  private PropertiesProvider() {
    try {

      InputStream input = this.getClass().getResourceAsStream("/environment.properties");

      Properties prop = new Properties();
      prop.load(input);
      this.localPort = prop.getProperty("local.port");
      this.ipAddress = prop.getProperty("ip.address");
      this.urlToShorten = prop.getProperty("url.to.shorten");

    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static synchronized PropertiesProvider getInstance() {
    if (_instance == null) {
      _instance = new PropertiesProvider();
    }
    return _instance;
  }

  public String getLocalPort() {
    return localPort;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public String getUrlToShorten() {
    return urlToShorten;
  }
}
