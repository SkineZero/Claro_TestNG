package com.testNG;

import org.testng.annotations.Test;
import com.PagsObjet.PagsObjetAddDir;
import com.PagsObjet.PagsObjetCloseDir;
import com.PagsObjet.PagsObjetCloseFact;
import com.PagsObjet.PagsObjetFact;
import com.PagsObjet.PagsObjetInicioDir;
import com.Utilidades.ExcelUtilidades;
import com.Utilidades.GenerarReportePDF;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.File;
import org.testng.annotations.AfterClass;

public class RunTestNG 
{
	
	ClaseBase clasebase;
	GenerarReportePDF generarReportePdf;
	File rutaCarpeta = null;
	// DIRECCION
	PagsObjetInicioDir inicioDireccion;
	PagsObjetAddDir agregarDireccion;
	PagsObjetCloseDir cerrarSesion;
	// FACTURA
	PagsObjetFact pagoFactura;
	PagsObjetCloseFact cerrarFactura;
	// VERIFICACIÓN
	boolean valor=true;
	
	
	private AppiumDriver<MobileElement> driver;
	@SuppressWarnings("unchecked")
	@BeforeClass
	@Parameters({ "rutaOutput", "rutaImagenReporte", "platformName", "deviceName","platformVersion",
		"appPackage","appActivity","noReset","autoGrantPermissions"})
	public void beforeClass(
			@Optional("default") String rutaOutput,	
			@Optional("default") String rutaImagenReporte,
			@Optional("default") String platformName,
			@Optional("default") String deviceName,
			@Optional("default") String platformVersion,
			@Optional("default") String appPackage,
			@Optional("default") String appActivity,
			@Optional("default") String noReset,
			@Optional("default") String autoGrantPermissions)

	{
		
		// ASIGNAMOS LAS OPCIONES Y LA CONFIGURACION DEL NAVEGADOR A LA VARIABLE DRIVER
		driver = ClaseBase.appiumDriverConection(platformName,deviceName,platformVersion,appPackage,
				appActivity,noReset,autoGrantPermissions);
		
		// INSTANCIAR LA CLASE PAGEOBJET
		generarReportePdf = new GenerarReportePDF();
		clasebase = new ClaseBase(driver);
		// DIRECCION
		inicioDireccion = new PagsObjetInicioDir(driver);
		agregarDireccion = new PagsObjetAddDir(driver);
		cerrarSesion = new PagsObjetCloseDir(driver);
		// FACTURA
		pagoFactura = new PagsObjetFact(driver);
		cerrarFactura = new PagsObjetCloseFact(driver);
		// SETTTIAR LAS CLASES
		generarReportePdf.setRutaImagen(rutaImagenReporte);
		clasebase.setrutaOutput(rutaOutput,driver);
		
	}

	@Test(dataProvider = "Ingreso", description = "CAMBIO DE DIRECCIÓN")
	public void Ingresar_Direccion(String Ejecutar, String Evidencia, String Usuario, String Contrasena, String Departamento, 
			String Ciudad, String Barrio, String TipoCalle, String NumPrincipal, String Sufijo1, String NumSecundario, 
			String Sufijo2,	String NumComponente, String Complemento, String NumComplemento, String Validador1, 
			String Validador2, String Validador3) throws Exception {
		
		if (Ejecutar.equals("SI")) {
			// OBTENER EL NOMBRE DEL MÉTODO A EJECUTAR
			String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();	
			// ESTO ES PARA GENERAR UNA HOJA EN BLANCO CUANDO SEAN DOS O MÁS TEST EN UNO SOLO.
			// generarReportePdf.setImg(0);
			
			if (Evidencia.equals("SI"))
				
			{
				
			// CREAR CARPETA
			rutaCarpeta = clasebase.crearCarpeta(nomTest);
			// INICIA VIDEO
			clasebase.iniVideo();		
			// INICIA CREACIÓN DE REPORTE PDF
			generarReportePdf.crearPlantilla(nomTest, rutaCarpeta);
			// INICIO DE ACCIONES
			// INICIO
			valor=inicioDireccion.IngresoClaro(Evidencia, Usuario, Contrasena,rutaCarpeta);
			// AGREGAR DIRECCIÓN
			valor=agregarDireccion.CamDireccion(Evidencia,Departamento,Ciudad, Barrio, TipoCalle, 
					NumPrincipal, Sufijo1, NumSecundario, Sufijo2, NumComponente, Complemento, 
					NumComplemento, rutaCarpeta);
			// CERRAR SESIÓN
			// valor=cerrarSesion.CerrarClaro(Evidencia,rutaCarpeta);
			// FIN VIDEO
			clasebase.finVideo(rutaCarpeta);
			// FINALIZA CREACIÓN DE REPORTE PDF
			generarReportePdf.cerrarPlantilla();
			}
			else {
				// INICIO
				valor=inicioDireccion.IngresoClaro(Evidencia, Usuario, Contrasena,rutaCarpeta);
				// AGREGAR DIRECCIÓN
				valor=agregarDireccion.CamDireccion(Evidencia,Departamento,Ciudad, Barrio, TipoCalle, NumPrincipal, 
						Sufijo1, NumSecundario, Sufijo2, NumComponente, Complemento, NumComplemento, rutaCarpeta);
				// CERRAR SESIÓN
				// valor=cerrarSesion.CerrarClaro(Evidencia,rutaCarpeta);
			}
			//assert.fail();
			assertEquals(valor, true);

		}	
	}
	
	@Test(dataProvider = "PagoFactu", description = "CAMBIO DE DIRECCIÓN")
	public void Pagar_Factura(String Ejecutar, String Evidencia, String Usuario, String Contrasena, 
			String NumTarjeta, String MesV,String YearV, String CVV, String Cuotas, String NomTarjeta, 
			String TipoID, String NumID, String TEL, String Email) throws Exception {
		if(Ejecutar.equals("SI")) {		
		// OBTENER EL NOMBRE DEL MÉTODO A EJECUTAR
		String nomTest = Thread.currentThread().getStackTrace()[1].getMethodName();
		if(Evidencia.equals("SI")) {
		// CREAR CARPETA
		rutaCarpeta = clasebase.crearCarpeta(nomTest);
		// INICIA VIDEO
		clasebase.iniVideo();
		// INICIA CREACIÓN DE REPORTE PDF
		generarReportePdf.crearPlantilla(nomTest, rutaCarpeta);
		// INICIO DE ACCIONES
		// INICIO
		inicioDireccion.IngresoClaro(Evidencia, Usuario, Contrasena,rutaCarpeta);
		// PAGAR FACTURA
		pagoFactura.PagoFactura(Evidencia,NumTarjeta, MesV, YearV, CVV, Cuotas, NomTarjeta, TipoID, NumID, TEL, Email, rutaCarpeta);
		// CERRAR SESIÓN
		cerrarFactura.CerrarClaroFactura(Evidencia,rutaCarpeta);
		// FINALIZA CREACIÓN DE REPORTE PDF
		generarReportePdf.cerrarPlantilla();
		// FIN VIDEO
		clasebase.finVideo(rutaCarpeta);
		}
		else 
		{
			// INICIO
			inicioDireccion.IngresoClaro(Evidencia, Usuario, Contrasena,rutaCarpeta);
			// PAGAR FACTURA
			pagoFactura.PagoFactura(Evidencia,NumTarjeta, MesV, YearV, CVV, Cuotas, NomTarjeta, TipoID, NumID, TEL, Email, rutaCarpeta);
			// CERRAR SESIÓN
			cerrarFactura.CerrarClaroFactura(Evidencia,rutaCarpeta);
		}
		}
		
	}
	
	@DataProvider(name = "Ingreso")
	public Object[][] Loging() throws Exception {
		Object[][] arreglo = ExcelUtilidades.getTableArray("./Data/ClaroTestNG.xlsx", "Direccion");
		return arreglo;
	}
	
	@DataProvider(name = "PagoFactu")
	public Object[][] PagoFactura() throws Exception {
		Object[][] arreglo = ExcelUtilidades.getTableArray("./Data/ClaroTestNG.xlsx", "PagoFactura");
		return arreglo;
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
