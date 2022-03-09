package com.PagsObjet;

import java.io.File;
import org.openqa.selenium.By;
import com.MapsObjet.MapsObjetAddDir;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PagsObjetAddDir extends MapsObjetAddDir

{
	
	// CREAR CONSTRUCTOR DE LA CLASE
		public PagsObjetAddDir(AppiumDriver<MobileElement> driver)

		{
			super(driver);
			this.driver = driver;
		}

		// METODO PRIMERA PRUEBA - INGRESAR A LA PAG WEB DE PRUEBA
		public boolean CamDireccion(String Evidencia, String Departamento, String Ciudad, String Barrio, 
				String TipoCalle, String NumPrincipal, String Sufijo1, String NumSecundario, String Sufijo2, 
				String NumComponente, String Complemento, String NumComplemento, File rutaCarpeta) throws Exception
		{		
			boolean valor = true;
			try {
			// ACTUALIZACIÓN DE DATOS
			click(btnImgUsuario,rutaCarpeta, Evidencia);
			click(btnConfiCuenta, rutaCarpeta, Evidencia);
			click(btnActuDatos, rutaCarpeta, Evidencia);
			click(btnSelCuenta, rutaCarpeta, Evidencia);
			tiempoEspera(1000);
			
			// MODIFICAR DATOS
			scrollVertical(rutaCarpeta, 504, 1192, 938, 2, btnActuDatos, Evidencia);
			tiempoEspera(1000);
			click(btnEditarDir, rutaCarpeta, Evidencia);
			tiempoEspera(4000);  
		
			// SELECCIONAR DEPARTAMENTO
			click(btnDpto, rutaCarpeta, Evidencia);
			String btnDepartamento = Departamento;
			String txtDepartamento = departamento + btnDepartamento + "']";
			driver.findElement(By.xpath(txtDepartamento)).click();
			
			tiempoEspera(4000);
			
			// SELECCIONAR CIUDAD
			click(btnCiudadBog, rutaCarpeta, Evidencia);
			String btnCiudad = Ciudad;
			String txtCity = ciudad + btnCiudad + "']";
			driver.findElement(By.xpath(txtCity)).click();
			
			// INGRESAR BARRIO
			sendkey(Barrio, txtBarrio, rutaCarpeta, Evidencia);
			
			// INGRESAR TIPO - CALLE O CARRERA
			click(selTipo, rutaCarpeta, Evidencia);
			scrollVertical(rutaCarpeta, 504, 1792, 438, 1, selTipo, txtCity);
			String btnTipo = TipoCalle;
			String txtTipo = tipo + btnTipo + "']";
			driver.findElement(By.xpath(txtTipo)).click();
			scrollVertical(rutaCarpeta, 504, 1192, 938, 3, selTipo, txtTipo);
			
			// INGRESA NUMERO PRINCIPAL
			sendkey(NumPrincipal, intPrincipal, rutaCarpeta, Evidencia);
			
			// INGRESAR SUFIJO 1
			click(selSufijo, rutaCarpeta, Evidencia);
			scrollVertical(rutaCarpeta, 504, 1192, 938, 2, selSufijo, txtTipo);
			String btnSufijo = Sufijo1;
			String txtSufijo = sufijo1 + btnSufijo + "']";
			driver.findElement(By.xpath(txtSufijo)).click();
			scrollVertical(rutaCarpeta, 504, 1192, 938, 2, selSufijo, txtSufijo);
			
			// INGRESAR NUMERO SECUNDARIO
			sendkey(NumSecundario, intSecundario, rutaCarpeta, Evidencia);
			
			// INGRESAR SUFIJO 2
			click(btnSufijo2,rutaCarpeta, Evidencia);
			scrollVertical(rutaCarpeta, 504, 1792, 438, 8, btnSufijo2, txtSufijo);
			String btnSufijo2 = Sufijo2;
			String txtSufijo2 = sufijo2 + btnSufijo2 + "']";
			driver.findElement(By.xpath(txtSufijo2)).click();
			
			// INGRESAR NUMERO COMPLEMENTARIO
			sendkey(NumComponente, intComplementaio, rutaCarpeta, Evidencia);
			
			// SELECCIONAR COMPLEMENTO
			click(selComplemento, rutaCarpeta, Evidencia);
			String btnComplemento = Complemento;
			String txtComplemento = complemento + btnComplemento + "']";
			driver.findElement(By.xpath(txtComplemento)).click();

			// INGRESAR NUMERO COMPLEMENTO
			sendkey(NumComplemento, intNum, rutaCarpeta, Evidencia);
			
			// ACCIONES VARIAS
			scrollVertical(rutaCarpeta, 504, 1192, 938, 2, btnValidar, txtComplemento);
			click(btnValidar, rutaCarpeta, Evidencia);
			click(btnCancelar, rutaCarpeta, Evidencia);
			tiempoEspera(2000);
			}
			catch (Exception e) 
			{
				System.out.println(e);
				valor = false;
			}
			return valor;
	}
}


//catch (InterruptedException e) {
//System.out.println("Falla ejecutando");
//driver.close();
//}