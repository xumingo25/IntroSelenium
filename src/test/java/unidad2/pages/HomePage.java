package unidad2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import unidad2.utils.ClaseBase;

public class HomePage extends ClaseBase {
    //Agrupar los localizadores
    By byBtnIniciarSesion = By.xpath("//button[@data-testid=\"login-button\"]");
    By btnRegistrase = By.xpath("//button[contains(text(),'Registrarte')]");
    By byBtnTextUsername = By.xpath("//button[@data-testid='user-widget-link']");

    //Definir las acciones de la página
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //acciones
    public void irARegisterPage(){
        click(esperaExplicita(btnRegistrase));
    }

    public void irALoginPage(){
        click(esperaExplicita(byBtnIniciarSesion));
    }

    public String obtenerUsername(){
        return obtenerAtributoWebElement(esperaExplicita(byBtnTextUsername),"aria-label");
    }
}
