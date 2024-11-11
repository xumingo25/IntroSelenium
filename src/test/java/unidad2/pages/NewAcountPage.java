package unidad2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import unidad2.utils.ClaseBase;

import java.util.List;

public class NewAcountPage extends ClaseBase {
    //Agrupar los localizadores
    By byTxtUsername = By.name("username");
    By byTxtBtnCerrarPopUp = By.xpath("//button[@aria-label='Cerrar']");
    By byNext = By.xpath("//button[@data-testid='submit']");
    By byTxtPassword = By.id("new-password");
    By byTxtDisplayName = By.id("displayName");
    By byTxtDia = By.id("day");
    By byTxtMes = By.id("month");
    By byTxtAnnio = By.id("year");
    By byOptionGener = By.xpath("//label[contains(@for,'gender_option_')]");
    By byOptionChecks = By.xpath("//label[contains(@for,'checkbox-')]");
    //Definir las acciones de la página
    
    public NewAcountPage(WebDriver driver) {
        super(driver);
    }

    public void crearCtaSpotify(String username,String pass,String displayName,String dia,String mes,String annio,int genero,Boolean chkMarketing, Boolean chkShareData){
        agregarTexto(esperaExplicita(byTxtUsername),username);

        if(estaDesplegado(esperaExplicita(byTxtBtnCerrarPopUp))){
            click(byTxtBtnCerrarPopUp);
        }
        esperarXSegundos(1000);
        click(esperaExplicita(byNext));
        agregarTexto(esperaExplicita(byTxtPassword),pass);
        esperarXSegundos(1000);
        click(esperaExplicita(byNext));
        agregarTexto(esperaExplicita(byTxtDisplayName),displayName);
        agregarTexto(esperaExplicita(byTxtDia),dia);
        selectDDLPorTextoVisible(esperaExplicita(byTxtMes),mes);
        agregarTexto(esperaExplicita(byTxtAnnio),annio);

        List<WebElement> generos = buscarElementosWeb(byOptionGener);
        click(generos.get(genero));
        click(esperaExplicita(byNext));

        List<WebElement> checks = buscarElementosWeb(byOptionChecks);

        if(chkMarketing){
            click(checks.get(0));
        }

        if(chkShareData){
            click(checks.get(1));
        }

        click(esperaExplicita(byNext));

    }
}
