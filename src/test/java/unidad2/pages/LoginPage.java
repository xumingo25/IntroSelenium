package unidad2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import unidad2.utils.ClaseBase;

public class LoginPage extends ClaseBase {
    //Agrupar los localizadores
    By byTxtUsername = By.id("login-username");
    By byBtnLogin = By.id("login-button");
    By byErrorLogin = By.xpath("//span[contains(text(), 'contrase')]");
    By byTxtLoginPass = By.id("login-password");


    //Definir las acciones de la página
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void iniciarSesion(String username,String pass){
        agregarTexto(esperaExplicita(byTxtUsername),username);
        agregarTexto(esperaExplicita(byTxtLoginPass),pass);
        click(esperaExplicita(byBtnLogin));
    }

    public String obtenerErrorLogin(){
        return obtenerTexto(esperaExplicita(byErrorLogin));
    }

}
