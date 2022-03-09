package com.PagsObjet;

import java.io.File;
import org.openqa.selenium.By;
import com.MapsObjet.MapsObjetFactura;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class PagsObjetFact extends MapsObjetFactura

{
	// CREAR CONSTRUCTOR DE LA CLASE
	public PagsObjetFact(AppiumDriver<MobileElement> driver)

	{
		super(driver);
		this.driver = driver;
	}

	// METODO SEGUNDA PRUEBA - PAGAR FACTURA
	public void PagoFactura(String Evidencia, String NumTarjeta, String MesV, String YearV, String CVV, 
			String Cuotas, String NomTarjeta, String TipoID, String NumID, String TEL, String Email, File rutaCarpeta) throws Exception	{
		try {
		// CLICK EN PAGOS
		click(btnPagos, rutaCarpeta, Evidencia);
		tiempoEspera(2000);
		
		// CLICK EN PAGAR FACTURA AQUÍ;
		click(btnPagaAqui, rutaCarpeta, Evidencia);
		
		//VENTANA DE CONFIMACIÓN DE MONTO
		click(btnTotal, rutaCarpeta, Evidencia);
		click(btnPagar, rutaCarpeta, Evidencia);
		tiempoEspera(5000);
		click(btnMPago, rutaCarpeta, Evidencia); 
		tocarPantalla(504, 1371);
		//click(btnTC, rutaCarpeta);
		
		//ACÁ O EN EL ANTERIOR DEBE SELECCIONAR EL MEDIO DE PAGO, TARJETA DE CREDITO
		click(btnContinuar, rutaCarpeta, Evidencia);
		tiempoEspera(10000);
		scrollVertical(rutaCarpeta, 504, 1692, 638, 2, btnContinuar, Evidencia);
		
		//INGRESO DATOS DE TARJETA DE CRÉDITO
		//NUMERO DE TARJETA
		sendkey(NumTarjeta, intNumTC, rutaCarpeta, Evidencia);
		
		// INGRESAR MES DE TARJETA
		click(selMonth, rutaCarpeta, Evidencia);
		String btnMes = MesV;
		String txtMes = mes + btnMes + "']";
		driver.findElement(By.xpath(txtMes)).click();
		
		// INGRESAR AÑO DE TARJETA
		click(selYear, rutaCarpeta, Evidencia);
		String btnAno = YearV;
		String txtAno = ano + btnAno + "']";
		driver.findElement(By.xpath(txtAno)).click();
		scrollVertical(rutaCarpeta, 504, 1292, 738, 1, selYear, Evidencia);
		
		// INGRESAR CVV
		sendkey(CVV, intCVV, rutaCarpeta, Evidencia);
		
		// INGRESAR CUOTAS
		click(selCuotas,rutaCarpeta, Evidencia);
		String btnCuotas = Cuotas;
		String txtCuotas = cuotas + btnCuotas + "']";
		driver.findElement(By.xpath(txtCuotas)).click();
		
		// INGRESAR NOMBRE DE LA TARJETA
		sendkey(NomTarjeta, txtNombreTC, rutaCarpeta, Evidencia);;
		scrollVertical(rutaCarpeta, 504, 1292, 738, 1, txtNombreTC, txtCuotas);
		
		// SELECCIONAR TIPO DE DOCUMENTO
		click(selTipoID, rutaCarpeta, Evidencia);
		String btnID = TipoID;
		String txtID = tipoID + btnID + "']";
		driver.findElement(By.xpath(txtID)).click();
		
		// INGRESAR NUMERO DE DOCUMENTO
		sendkey(NumID, intNumID, rutaCarpeta, Evidencia);
		
		// INGRESAR NUMERO DE TELEFONO
		sendkey(TEL, intNumTel, rutaCarpeta, Evidencia);
		scrollVertical(rutaCarpeta, 504, 1592, 738, 1, intNumTel, txtID);
		
		// INGRESAR CORREO
		sendkey(Email, txtEmail, rutaCarpeta, Evidencia);
		driver.hideKeyboard();
		
		// PAGAR O CANCELAR
		click(btnCancePago, rutaCarpeta, Evidencia);
		tiempoEspera(2000);
		
		// DEVOLVERSE
		click(btnAtrás, rutaCarpeta, Evidencia);
		tiempoEspera(1000);
		}
		catch (Exception e) {
			System.out.println(e);
		}		
	}
}
