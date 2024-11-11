package unidad2.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import unidad2.pages.HomePage;
import unidad2.pages.LoginPage;
import unidad2.pages.NewAcountPage;
import unidad2.utils.Encoding;

public class Tests {
    //Instanciar objetos de las page's
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private NewAcountPage newAcountPage;
    static String rutaDriver = System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe";

    @BeforeEach
    public void preCondiciones(){
        //preparo el driver y las page's
        homePage = new HomePage(driver);
        homePage.conexionDriver("Chrome",rutaDriver,"webdriver.chrome.driver");
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
        homePage.irALoginPage();
        loginPage.iniciarSesion("usuariuoalgo@sdfsda.com","");
        Assertions.assertEquals(Encoding.corregirEncoding("Nombre de usuario o contraseña incorrectos.")
                ,loginPage.obtenerErrorLogin());
    }

    @Test
    public void CP002_CreacionCtaSpotify(){
        homePage.irARegisterPage();
        newAcountPage.crearCtaSpotify("userSecurity009@gmail.com","asdf@123456","User Prueba","31","Diciembre","1990",0,true,true);
        Assertions.assertEquals(Encoding.corregirEncoding("User Prueba")
                ,homePage.obtenerUsername());
    }
}
