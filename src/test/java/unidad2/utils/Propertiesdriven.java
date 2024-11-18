package unidad2.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Propertiesdriven {
    private static Properties props;

    public static String obtenerProperty(String key){//browser
        props = new Properties();
        String rutaArchivo = System.getProperty("user.dir")+"\\src\\test\\resources\\properties.properties";

        try{
            InputStream input =new FileInputStream(rutaArchivo);
            props.load(input);
        }catch (Exception ex){
            System.out.println("No se ha podido cargar el archivo properties....");
            System.out.println("Ruta: "+rutaArchivo);
            System.out.println(ex.getMessage());
        }

        return props.getProperty(key);
    }
}
