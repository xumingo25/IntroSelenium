package unidad2.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import unidad2.pages.HomePage;
import unidad2.pages.LoginPage;
import unidad2.pages.NewAcountPage;
import unidad2.utils.DataDriven;
import unidad2.utils.Encoding;

import java.util.ArrayList;

public class Tests {
    //Instanciar objetos de las page's
    private WebDriver driver;
    ArrayList<String> data;
    private HomePage homePage;
    private LoginPage loginPage;
    private NewAcountPage newAcountPage;

    @BeforeEach
    public void preCondiciones(){
        //preparo el driver y las page's
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        data = new ArrayList<String>();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(homePage.getDriver());
        newAcountPage = new NewAcountPage(homePage.getDriver());
        homePage.cargarSitio("https://open.spotify.com/intl-es");
        homePage.maximizarBrowser();
    }

    @AfterEach
    public void posCondiciones(){
    }

    @Test
    public void CP001_ErrorLogin(){
        data = DataDriven.getTestData("CP001_ErrorLogin");
        homePage.irALoginPage();
        loginPage.iniciarSesion(data.get(1),data.get(2));
        Assertions.assertEquals(data.get(3)
                ,loginPage.obtenerErrorLogin());
    }

    @Test
    public void CP002_CreacionCtaSpotify(){
        data = DataDriven.getTestData("CP002_CreacionCtaSpotify");
        homePage.irARegisterPage();
        newAcountPage.crearCtaSpotify(data.get(1),data.get(2),data.get(3),data.get(4),data.get(5),data.get(6), Integer.parseInt(data.get(7)), Boolean.valueOf(data.get(8)), Boolean.valueOf(data.get(9)));
        Assertions.assertEquals(Encoding.corregirEncoding(data.get(10))
                ,homePage.obtenerUsername());
    }
}
