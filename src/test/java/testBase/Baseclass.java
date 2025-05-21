package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Baseclass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        String executionEnv = p.getProperty("execution_env").toLowerCase();

        if (executionEnv.equals("remote")) {
            // Remote driver setup with Docker-safe Chrome options
            if (br.equalsIgnoreCase("chrome")) {
            
            	ChromeOptions options = new ChromeOptions();
            	options.addArguments("--remote-allow-origins=*");

            	driver = new ChromeDriver(options);  // Not RemoteWebDriver
               

                if (os != null && !os.isEmpty()) {
                    options.setPlatformName(os.toLowerCase());
                }

                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            } else if (br.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();
                if (os != null && !os.isEmpty()) {
                    options.setPlatformName(os.toLowerCase());
                }
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
            } else {
                throw new IllegalArgumentException("Unsupported browser for remote: " + br);
            }

        } else if (executionEnv.equals("local")) {
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser for local: " + br);
            }
        } else {
            throw new IllegalArgumentException("Invalid execution_env: " + executionEnv);
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(p.getProperty("appURL"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomAlphanumeric() {
        return randomString() + "@" + randomNumber();
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        Path targetPath = Paths.get(System.getProperty("user.dir"), "screenshots",
                tname + "_" + timeStamp + ".png");
        Files.createDirectories(targetPath.getParent());
        Files.copy(sourceFile.toPath(), targetPath);

        return targetPath.toString();
    }
}
