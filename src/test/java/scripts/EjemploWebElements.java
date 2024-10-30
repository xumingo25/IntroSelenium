package scripts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EjemploWebElements {
    //atributos
    private static WebDriver driver;
    private static String rutaDriver = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\chromedriver.exe";
    private WebDriverWait wait;

    @BeforeEach
    public void preCondiciones(){
        //Enlazar el webDriver al browser de nuestro sistema
        System.setProperty("webdriver.chrome.driver",rutaDriver);
        //Instanciar el webdriver
        driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void posCondiciones(){
        driver.close();
    }
    @Test
    public  void CP001_ERROR_LOGIN() throws InterruptedException{
        driver.get("https://open.spotify.com/intl-es");

        WebElement btnIniciarSesion;
        By byBtnIniciarSesion = By.xpath("//button[@data-testid=\"login-button\"]");

        btnIniciarSesion = driver.findElement(byBtnIniciarSesion);
        btnIniciarSesion.click();

        driver.findElement(By.id("login-username")).sendKeys("cualquiercosa@algo.cl");

        driver.findElement(By.id("login-button")).click();

        Assertions.assertEquals(corregirEncoding("Nombre de usuario o contraseña incorrectos."),driver.findElement(By.xpath("//span[contains(text(), 'contrase')]")).getText());

    }

    @Test
    public void CP002_CreacionCtaSpotify() throws InterruptedException {
        driver.get("https://open.spotify.com/intl-es");

        driver.findElement(By.xpath("//button[contains(text(),'Registrarte')]")).click();

        driver.findElement(By.name("username")).sendKeys("UsgdsfdghdfOTEST@algo.cl");

        WebElement btnCerrarPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Cerrar']")));

        if(btnCerrarPopUp.isDisplayed()){
            btnCerrarPopUp.click();
        }

        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='submit']"))).click();

        Thread.sleep(1000);

        WebElement txtNewPassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("new-password")));
        txtNewPassword.sendKeys("1234ASDF@@");

        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='submit']"))).click();
        Thread.sleep(1000);
        //id=displayName
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("displayName"))).sendKeys("UserBCOSecurity2");
        Thread.sleep(1000);
        //id=day
        driver.findElement(By.id("day")).sendKeys("30");

        driver.findElement(By.id("year")).sendKeys("2000");

        Select ddLMonth = new Select(driver.findElement(By.id("month")));

        ddLMonth.selectByVisibleText("Junio");

        List<WebElement> generos = driver.findElements(By.xpath("//label[contains(@for,'gender_option_')]"));

        generos.get(0).click();


        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();

        List<WebElement> checks = driver.findElements(By.xpath("//label[contains(@for,'checkbox-')]"));

        checks.get(0).click();

        checks.get(1).click();

        driver.findElement(By.xpath("//button[@data-testid='submit']")).click();
        Thread.sleep(1000);

        String resultadoEsperado = "UserBCOSecurity2";

        String resultadoActual = driver.findElement(By.xpath("//button[@data-testid='user-widget-link']")).getAttribute("aria-label");

        Assertions.assertEquals(resultadoEsperado,resultadoActual);
    }

    @Test
    public void CP003_ErrorCtaCteBCI_EnvioDatos() throws InterruptedException {
        driver.get("https://www.bci.cl/personas");

        driver.findElement(By.xpath("//a[contains(text(),'Hazte Cliente')]")).click();

        WebElement iframe = driver.findElement(By.id("myFrame"));

        driver.switchTo().frame(iframe);

        driver.findElement(By.xpath("//input[@placeholder='Ingresa tu RUT']")).sendKeys("18610451-k");

        driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresa n')]")).sendKeys("256282258");

        driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresa tu t')]")).sendKeys("66557788");

        WebElement txtEmail= driver.findElement(By.xpath("//input[contains(@placeholder,'Ingresa tu email')]"));
        txtEmail.sendKeys("algo@algo.com");

        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", txtEmail);

        driver.findElement(By.xpath("//input[contains(@placeholder,'Vuelve a ingresar tu email')]")).sendKeys("algo@algo.com");

        Thread.sleep(1000);
        WebElement check = driver.findElement(By.xpath("//bci-wk-checkbox[@formcontrolname='checkPoliticas']"));
        Thread.sleep(1000);

        check.click();

        WebElement btnContinuar = driver.findElement(By.xpath("//button[contains(text(),'Continuar')]"));

        btnContinuar.click();

        Thread.sleep(1000);
        String resultadoEsperado= "Hubo un error intentando enviar tus datos";
        String resultadoActual = driver.findElement(By.xpath("//p[@class='tituloError']")).getText();

        Assertions.assertEquals(resultadoEsperado,resultadoActual);

    }

    public static String corregirEncoding(String textoIncorrecto) {
        // Convertir la cadena incorrecta de la codificación ISO-8859-1 a UTF-8
        byte[] bytes = textoIncorrecto.getBytes(StandardCharsets.ISO_8859_1);
        String textoCorregido = new String(bytes, StandardCharsets.UTF_8);

        return textoCorregido;
    }
}
