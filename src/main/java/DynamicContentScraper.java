import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;


public class DynamicContentScraper
{
    public String getDynamicContentFromUrl(String url){
        // Selenium
        WebDriver driver = new FirefoxDriver(createFirefoxProfile());
        driver.get(url);

        try {
            Thread.sleep(2000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        String html_content = driver.getPageSource();
        driver.close();

        return html_content;
    }

    private static FirefoxProfile createFirefoxProfile() {
        File profileDir = new File("/tmp/firefox-profile-dir");
        if (profileDir.exists())
            return new FirefoxProfile(profileDir);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        File dir = firefoxProfile.layoutOnDisk();
        try {
            profileDir.mkdirs();
            FileUtils.copyDirectory(dir, profileDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firefoxProfile;
    }
}
