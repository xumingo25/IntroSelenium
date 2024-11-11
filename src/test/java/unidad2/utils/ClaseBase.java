package unidad2.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ClaseBase {
    //Atributos
    protected WebDriver driver;
    private WebDriverWait wait;


    //Métodos
    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    public ClaseBase(WebDriver driver) {
        this.driver = driver;
    }


    //funciones genericas para el uso de la libreria de selenium
    public WebElement buscarElementoWeb(By localizador){
        return driver.findElement(localizador);
    }

    public List<WebElement> buscarElementosWeb(By localizador){
        return driver.findElements(localizador);
    }

    public void cargarSitio(String url){
        this.driver.get(url);
    }

    //definir una espera explicita de 30 seg consultado cada .5 segs
    public WebElement esperaExplicita(By localizador){
        wait = new WebDriverWait(this.driver,30);
        return wait.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }

    //Esperar X Segundos
    public void esperarXSegundos(int miliSegundos) {
        try {
            Thread.sleep(miliSegundos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //clicks
    public void click(By localizador){
        this.driver.findElement(localizador).click();
    }

    public void click(WebElement elemento){
        elemento.click();
    }

    //inserciones de texto
    public void agregarTexto(By localizador,String texto){
        this.driver.findElement(localizador).sendKeys(texto);
    }

    public void agregarTexto(WebElement elemento,String texto){
        elemento.sendKeys(texto);
    }

    public void agregarCombinacionTeclas(By localizador, Keys key){
        this.driver.findElement(localizador).sendKeys(key);
    }

    public void agregarCombinacionTeclas(WebElement elemento, Keys key){
        elemento.sendKeys(key);
    }

    public void cerrarBrowser(){
        this.driver.close();
    }

    public void maximizarBrowser(){
        this.driver.manage().window().maximize();
    }

    public boolean estaDesplegado(By localizador){
        try{
            return this.driver.findElement(localizador).isDisplayed();
        }catch(org.openqa.selenium.NoSuchElementException ex){
            return false;
        }
    }

    public boolean estaDesplegado(WebElement elemento){
        try{
            return elemento.isDisplayed();
        }catch(org.openqa.selenium.NoSuchElementException ex){
            return false;
        }
    }

    public void selectDDLPorTextoVisible(WebElement elemento,String texto){
        Select select = new Select(elemento);

        select.selectByVisibleText(texto);
    }

    public WebDriver conexionDriver(String browser,String ruta,String propertyDriver){
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty(propertyDriver,ruta);
            this.driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("firefox")){
            System.setProperty(propertyDriver,ruta);
            this.driver = new FirefoxDriver();
        }else if (browser.equalsIgnoreCase("edge")){
            System.setProperty(propertyDriver,ruta);
            this.driver = new EdgeDriver();
        }
        return this.driver;
    }


    public String obtenerTexto(By localizador){
        return this.driver.findElement(localizador).getText();
    }

    public String obtenerTexto(WebElement elemento){
        return elemento.getText();
    }

    public String obtenerAtributoWebElement(By localizador,String atributo){
        return this.driver.findElement(localizador).getAttribute(atributo);
    }

    public String obtenerAtributoWebElement(WebElement elemento,String atributo){
        return elemento.getAttribute(atributo);
    }

}
