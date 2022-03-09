package com.PagsObjet;

import java.io.File;
import com.MapsObjet.MapsObjetCloseFact;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PagsObjetCloseFact extends MapsObjetCloseFact

{
	// CREAR CONSTRUCTOR DE LA CLASE
	public PagsObjetCloseFact(AppiumDriver<MobileElement> driver)

	{
		super(driver);
		this.driver = driver;
	}


	
	// METODO PRIMERA PRUEBA - INGRESAR A LA PAG WEB DE PRUEBA
	public void CerrarClaroFactura(String Evidencia, File rutaCarpeta) throws Exception	{
		try {			
		click(btnImgUsuario, rutaCarpeta, Evidencia);
		
		// SALIR
		click(btnCerrarSesion, rutaCarpeta, Evidencia);
		click(btnConfirmar, rutaCarpeta, Evidencia);
		tiempoEspera(1000);		
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}

