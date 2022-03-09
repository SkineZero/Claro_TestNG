package com.PagsObjet;

import java.io.File;
import com.MapsObjet.MapsObjetCloseDir;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PagsObjetCloseDir extends MapsObjetCloseDir {
  
	// CREAR CONSTRUCTOR DE LA CLASE
	public PagsObjetCloseDir(AppiumDriver<MobileElement> driver)

	{
		super(driver);
		this.driver = driver;
	}

	// METODO PRIMERA PRUEBA - INGRESAR A LA PAG WEB DE PRUEBA
	public String CerrarClaro(String Evidencia, File rutaCarpeta) throws Exception{
		try {
		
		// IR A INICIO
		click(btnInicio, rutaCarpeta, Evidencia);
		click(btnImgUsuario, rutaCarpeta, Evidencia);
		
		// SALIR
		click(btnCerrarSesion, rutaCarpeta, Evidencia);
		click(btnConfirmar, rutaCarpeta, Evidencia);
		tiempoEspera(4000);	
		
		}
		catch (Exception e) {
			System.out.println(e);
		}
		String valor = capturarValorSelenium(verifiHola);
		return valor;
	}
}
