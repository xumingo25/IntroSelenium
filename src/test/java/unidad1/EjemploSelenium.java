package unidad1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EjemploSelenium {
    public static void main(String[] args) throws InterruptedException{

        String rutaProyecto = System.getProperty("user.dir");
        String rutaDriver = rutaProyecto+
                "\\src\\test\\resources\\drivers\\chromedriver.exe";

        //Enlazar el webDriver al browser de nuestro sistema
        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Instanciar un objeto que represente al browser (driver)
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        System.out.println("La url de la pagina es: "+driver.getCurrentUrl());
        System.out.println("El titulo de la página es: "+driver.getTitle());

        driver.manage().window().maximize();

        Thread.sleep(5000);

        driver.navigate().to("https://www.tsoftglobal.com");

        Thread.sleep(5000);

        System.out.println("La url de la pagina es: "+driver.getCurrentUrl());
        System.out.println("El titulo de la página es: "+driver.getTitle());

        Thread.sleep(5000);

        driver.navigate().refresh();

        Thread.sleep(5000);

        driver.navigate().back();

        Thread.sleep(5000);

        System.out.println("La url de la pagina es: "+driver.getCurrentUrl());
        System.out.println("El titulo de la página es: "+driver.getTitle());

        driver.close();
    }
}
