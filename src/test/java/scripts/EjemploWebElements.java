package scripts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class EjemploWebElements {

    @Test
    public  void CP001_ERROR_LOGIN() throws InterruptedException{
        String rutaProyecto = System.getProperty("user.dir");
        String rutaDriver = rutaProyecto+
                "\\src\\test\\resources\\drivers\\chromedriver.exe";

        //Enlazar el webDriver al browser de nuestro sistema
        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Instanciar un objeto que represente al browser (driver)
        WebDriver driver = new ChromeDriver();

        driver.get("https://open.spotify.com/intl-es");

        driver.manage().window().maximize();

        //espere 5 segundos
        Thread.sleep(5000);

        //clic al boton iniciar Sesión
        WebElement btnIniciarSesion;
        By byBtnIniciarSesion = By.xpath("//button[@data-testid=\"login-button\"]");

        btnIniciarSesion = driver.findElement(byBtnIniciarSesion);
        btnIniciarSesion.click();

        Thread.sleep(5000);

        driver.findElement(By.id("login-username")).sendKeys("cualquiercosa@algo.cl");

        Thread.sleep(2000);

        driver.findElement(By.id("login-button")).click();

        Thread.sleep(2000);
/*
        String resultadoEsperado = "Nombre de usuario o contraseña incorrectos.";
            String resultadoActual = driver.findElement(By.xpath("//span[contains(text(), 'contrase')]")).getText();


        if(resultadoActual.equalsIgnoreCase(resultadoEsperado)){
            System.out.println("Funciona :D");
        }else{
            System.out.println("no hay igualdad");
            System.out.println(" Resultado obtenido: "+ corregirEncoding(resultadoActual));
            System.out.println(" Resultado esperado: "+ resultadoEsperado);

        }*/

        Assertions.assertEquals(corregirEncoding("Nombre de usuario o contraseña incorrectos."),driver.findElement(By.xpath("//span[contains(text(), 'contrase')]")).getText());
        Thread.sleep(5000);
        driver.close();
    }

    @Test
    public void CP002_CreacionCtaSpotify() throws InterruptedException {
        String rutaProyecto = System.getProperty("user.dir");
        String rutaDriver = rutaProyecto+
                "\\src\\test\\resources\\drivers\\chromedriver.exe";

        //Enlazar el webDriver al browser de nuestro sistema
        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Instanciar un objeto que represente al browser (driver)
        WebDriver driver = new ChromeDriver();

        driver.get("https://open.spotify.com/intl-es");

        driver.manage().window().maximize();

        //espere 5 segundos
        Thread.sleep(5000);

        driver.findElement(By.xpath("//button[contains(text(),'Registrarte')]")).click();

        Thread.sleep(5000);

        driver.findElement(By.name("username")).sendKeys("User2q34q32BCOTEST@algo.cl");

        Thread.sleep(1000);

        WebElement btnCerrarPopUp = driver.findElement(By.xpath("//button[@aria-label='Cerrar']"));

        if(btnCerrarPopUp.isDisplayed()){
            btnCerrarPopUp.click();
        }

        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();

        Thread.sleep(1000);

        driver.findElement(By.id("new-password")).sendKeys("1234ASDF@@");

        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();
        Thread.sleep(1000);
        //id=displayName
        driver.findElement(By.id("displayName")).sendKeys("UserBCOSecurity2");
        Thread.sleep(1000);
        //id=day
        driver.findElement(By.id("day")).sendKeys("30");
        Thread.sleep(1000);

        //id=year
        driver.findElement(By.id("year")).sendKeys("2000");
        Thread.sleep(1000);

        //Creación de objeto select para representar un drop down list (combobox)
        Select ddLMonth = new Select(driver.findElement(By.id("month")));

        ddLMonth.selectByVisibleText("Junio");

        List<WebElement> generos = driver.findElements(By.xpath("//label[contains(@for,'gender_option_')]"));
        Thread.sleep(1000);
        generos.get(0).click();
        Thread.sleep(1000);
        generos.get(1).click();
        Thread.sleep(1000);
        generos.get(2).click();
        Thread.sleep(1000);
        generos.get(3).click();
        Thread.sleep(1000);
        generos.get(4).click();
        Thread.sleep(1000);
        generos.get(0).click();

        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();
        Thread.sleep(1000);

        //checkbox-
        List<WebElement> checks = driver.findElements(By.xpath("//label[contains(@for,'checkbox-')]"));
        Thread.sleep(1000);
        checks.get(0).click();
        Thread.sleep(1000);
        checks.get(1).click();
        Thread.sleep(1000);
        checks.get(0).click();
        Thread.sleep(1000);
        checks.get(1).click();
        Thread.sleep(1000);
        checks.get(0).click();
        Thread.sleep(1000);
        checks.get(1).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();
        Thread.sleep(10000);

        String resultadoEsperado = "UserBCOSecurity2";

        //aria-label="UserBCOSecurity"
        String resultadoActual = driver.findElement(By.xpath("//button[@data-testid='user-widget-link']")).getAttribute("aria-label");

        Assertions.assertEquals(resultadoEsperado,resultadoActual);
    }

    @Test
    public void CP003_ErrorCtaCteBCI() throws InterruptedException {
        String rutaProyecto = System.getProperty("user.dir");
        String rutaDriver = rutaProyecto+
                "\\src\\test\\resources\\drivers\\chromedriver.exe";

        //Enlazar el webDriver al browser de nuestro sistema
        System.setProperty("webdriver.chrome.driver",rutaDriver);

        //Instanciar un objeto que represente al browser (driver)
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.bci.cl/personas");

        driver.manage().window().maximize();

        //espere 5 segundos
        Thread.sleep(5000);


        driver.findElement(By.xpath("//a[contains(text(),'Hazte Cliente')]")).click();
        Thread.sleep(3000);

        //idFrame = myFrame
        WebElement iframe = driver.findElement(By.id("myFrame"));

        driver.switchTo().frame(iframe);

        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@placeholder='Ingresa tu RUT']")).sendKeys("25628225-9");

        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresa n')]")).sendKeys("256282258");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresa tu t')]")).sendKeys("66557788");

        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresa tu email')]")).sendKeys("algo@algo.com");

        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[contains(@placeholder,'Vuelve a ingresar tu email')]")).sendKeys("algo@algo.com");

        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) driver;



        // Scrolling down the page till the element is found
        js.executeScript("window.scrollBy(0,100)");

        Thread.sleep(1000);
        WebElement check = driver.findElement(By.id("bci-wk-checkbox0"));

        Thread.sleep(1000);

        check.click();

    }


    public static String corregirEncoding(String textoIncorrecto) {
        // Convertir la cadena incorrecta de la codificación ISO-8859-1 a UTF-8
        byte[] bytes = textoIncorrecto.getBytes(StandardCharsets.ISO_8859_1);
        String textoCorregido = new String(bytes, StandardCharsets.UTF_8);

        return textoCorregido;
    }
}
