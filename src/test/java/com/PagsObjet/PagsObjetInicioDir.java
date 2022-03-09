package com.PagsObjet;

import java.io.File;
import com.MapsObjet.MapsObjetInicioDir;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PagsObjetInicioDir extends MapsObjetInicioDir{
	// CREAR CONSTRUCTOR DE LA CLASE
		public PagsObjetInicioDir(AppiumDriver<MobileElement> driver)

		
		{
			super(driver);
			this.driver = driver;
		}

		// METODO PRIMERA PRUEBA - INGRESAR A LA PAG WEB DE PRUEBA
		public boolean IngresoClaro(String Evidencia, String Usuario, String Contrasena, File rutaCarpeta) throws Exception

		{
			boolean valor=true;
			try
			{
			// INGRESA A LA PÁGINA WEB
			tiempoEspera(6000);
			click(btnIngreso, rutaCarpeta, Evidencia);
			tiempoEspera(1000);
			
			// INGRESO DATOS
			click(btnIngCorreo, rutaCarpeta, Evidencia);
			tiempoEspera(1000);
			sendkey(Usuario, txtCorreo, rutaCarpeta, Evidencia);
			sendkey(Contrasena, txtContrasena, rutaCarpeta, Evidencia);
			click(btnVerifyContra, rutaCarpeta, Evidencia);
			
			// INICIO SESIÓN 
			click(btnIniSesion, rutaCarpeta, Evidencia);
			tiempoEspera(1000);
			
			// CANCELAR INICIO CON HUELLA
			click(btnCancelar, rutaCarpeta, Evidencia);
			tiempoEspera(3000);
			}
			catch (Exception e) 
			{
				System.out.println(e);
				valor = false;
				printConsola("SE HA GENERADO UN ERROR... CERRANDO APLICACIÓN");
			}
			// String valor = capturarValorSelenium(btnCancelar);
			return valor;
		}
}
