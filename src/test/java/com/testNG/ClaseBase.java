package com.testNG;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.NoSuchElementException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.Utilidades.GenerarReportePDF;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ClaseBase {
  
	protected AppiumDriver<MobileElement> driver;
	
	String rutaOutput;

	// CONSTRUCTOR DE CLASE
	public ClaseBase(WebDriver driver)

	{
		super();
	}

	public void setrutaOutput(String rutaOutput, AppiumDriver<MobileElement> driver2) 
	{
		this.rutaOutput = rutaOutput;
		this.driver = driver2;
	}
	
	public void intento(By locator, File rutaCarpeta){
	{
		boolean intento; 
		try { 
			driver.findElement(By.id("logoutLink")); 
			intento = true; 
			} 
		catch (NoSuchElementException e) { 
			intento = false; }
	}
}
	@SuppressWarnings("deprecation")
	public void recargarPag() {
		driver.getKeyboard().sendKeys(Keys.F5);
	}

	// METODO ALERTA

	public void alert() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			alert.accept();
			tiempoEspera(3);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
/*
	// DATOS ANDROID
	@SuppressWarnings("deprecation")
	public void sendkeyandroid(ReadExcelFile leer, Properties propiedades, String numHoja, int fil, int col)
			throws Exception {
		driver.getKeyboard()
				.sendKeys(leer.getCellValue(propiedades.getProperty("filePathExcel"), "DatosResidencia", fil, col));
	}
*/
	// INGRESAR DATOS
	public void escribir(String palabra) throws Exception {

		char[] c = palabra.toCharArray();

		for (int i = 0; i < palabra.length(); i++) {
			String C = String.valueOf(c[i]);
			((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.valueOf(C)));
		}
		driver.hideKeyboard();
	}

	// METODO DE NAVEGADOR
	@SuppressWarnings("rawtypes")
	public static AppiumDriver 
	appiumDriverConection(String platformName, String deviceName, String platformVersion, 
			String appPackage, String appActivity, String noReset, String autoGrantPermissions)
	{
		AppiumDriver _driver = null;
		try 
		{
			// CEREARLAS CAPBILITUS DEL MOVIL
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("platformName", platformName);
			caps.setCapability("deviceName", deviceName);
			caps.setCapability("platformVersion", platformVersion);
			caps.setCapability("appPackage", appPackage);
			caps.setCapability("appActivity", appActivity);
			caps.setCapability("noReset", noReset);
			caps.setCapability("autoGrantPermissions", autoGrantPermissions);
			// INSTANCIAR APPIUM DRIVER
			try {
				printConsola("Cargando Cability de appium, por favor espere...");
				_driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			} catch (MalformedURLException e) {
				printConsola(e.getMessage());
			}
			return _driver;
		} catch (Exception e) {
			printConsola(e.getMessage());
		}
		return _driver;
	}

	public String capturarValorSelenium(By locator) throws Exception
	{
		String valor = driver.findElement(locator).getText();
		return valor;
	}
	// METODO CLICK
	public void click(By locator, File rutaCarpeta, String Evidencia) throws Exception 
	{
		try 
		{
			driver.findElement(locator).click();
			tiempoEspera(1000);
			captureScreen2(rutaCarpeta, locator, Evidencia);
		}
		catch (Exception e) 
		{
			captureScreenError(rutaCarpeta, locator, Evidencia, e.toString());
			throw new InterruptedException();
		}
	}

	// METODO BORRAR
	public void borrar(By locator, File rutaCarpeta, String Evidencia) throws Exception 
	{
		try
		{
			driver.findElement(locator).clear();
			tiempoEspera(1000);
			captureScreen2(rutaCarpeta, locator, Evidencia);
		}
		catch (Exception e) 
		{
			captureScreenError(rutaCarpeta, locator, Evidencia, e.toString());
			throw new InterruptedException();
		}

	}
	
	// METODO ENVIAR TEXTO
	public void sendkey(String inputText, By locator, File rutaCarpeta, String Evidencia) throws Exception 
	{
		try
		{
			driver.findElement(locator).sendKeys(inputText);
			tiempoEspera(1000);
			captureScreen2(rutaCarpeta, locator, Evidencia);
		}
		catch (Exception e) 
		{
			captureScreenError(rutaCarpeta, locator, Evidencia, e.toString());
			throw new InterruptedException();
		}

	}

	// METODO ENTER SUBMIN
	public void submit(By locator, File rutaCarpeta, String Evidencia) throws Exception 
	{
		try
		{
			driver.findElement(locator).submit();
			captureScreen2(rutaCarpeta, locator, Evidencia);
		}
		catch (Exception e) 
		{
			captureScreenError(rutaCarpeta, locator, Evidencia, e.toString());
			throw new InterruptedException();
		}

	}

	// METODO PRINT OUT

	public static void printConsola(String texto) {
		System.out.println(texto);
	}

	// METODO CONTROL + A
	public void comando(By locator, File rutaCarpeta, String Evidencia) throws Exception 
	{
		try
		{
			driver.findElement(locator).sendKeys(Keys.CONTROL, "a");
			captureScreen2(rutaCarpeta, locator, Evidencia);
		}
		catch (Exception e) {
			captureScreenError(rutaCarpeta, locator, Evidencia, e.toString());
			throw new InterruptedException();
		}
		
	}

	// METODO CONTROL + A
	public void bspace(By locator, File rutaCarpeta, String Evidencia) throws Exception 
	{
		try
		{
			driver.findElement(locator).sendKeys(Keys.BACK_SPACE);
			captureScreen2(rutaCarpeta, locator, Evidencia);
		}
	
		catch (Exception e) {
			captureScreenError(rutaCarpeta, locator, Evidencia, e.toString());
			throw new InterruptedException();
		}
	}

	// METODO ENTER
	public void intro(By locator, File rutaCarpeta, String Evidencia) throws Exception 
	{
		try
		{
			driver.findElement(locator).sendKeys(Keys.ENTER);
			captureScreen2(rutaCarpeta, locator, Evidencia);
		}
		
		catch (Exception e) {
			captureScreenError(rutaCarpeta, locator, Evidencia, e.toString());
			throw new InterruptedException();
		}
	}

	// TAB 2.0
	@SuppressWarnings("deprecation")
	public void tab() {
		driver.getKeyboard().sendKeys(Keys.TAB);
	}

	// METODO TAB
	public void tabulador(By locator, File rutaCarpeta, String Evidencia) throws Exception 
	{
		try
		{
			driver.findElement(locator).sendKeys(Keys.TAB);
			captureScreen2(rutaCarpeta, locator, Evidencia);
		}
		catch (Exception e) {
			captureScreenError(rutaCarpeta, locator, Evidencia, e.toString());
			throw new InterruptedException();
		}
		
	}

	// METODO TIEMPO DE ESPERA
	public void tiempoEspera(long tiempo) throws InterruptedException {
		Thread.sleep(tiempo);
	}

	public static String fechaHora() {
		// TOMAMOS LA FECHA DEL SISTEMA
		LocalDateTime fechaSistema = LocalDateTime.now();
		// DEFINIR FORMATO FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmmss");
		// DAR FORMATO A LA FECHA DEL SISTEMA
		String formatFecha = fecha.format(fechaSistema);
		return formatFecha;
	}
	
	public static String fechaHora2() {
		// TOMAMOS LA FECHA DEL SISTEMA
		LocalDateTime fechaSistema = LocalDateTime.now();
		// DEFINIR FORMATO FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		// DAR FORMATO A LA FECHA DEL SISTEMA
		String formatFecha = fecha.format(fechaSistema);
		return formatFecha;
	}

	public String HoraSistema() {
		// TOMAMOS LA FECHA DEL SISTEMA
		LocalTime horaSistema = LocalTime.now();
		// DEFINIR FORMATO FECHA
		DateTimeFormatter fecha = DateTimeFormatter.ofPattern("HHmmss");
		// DAR FORMATO A LA FECHA DEL SISTEMA
		String hora = fecha.format(horaSistema);
		return hora; 
	}

	// METODO DE SCROLL BUENO

	public void scrollPage(int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + x + "," + y + ")", "");
	}

	// TOUCH EN LA PANTALLA VERTICAL

	public void scrollVertical(File rutaCarpeta, int xini, int yini, int yfinal, int iteraciones, By locator, String Evidencia) throws Exception {

		for (int i = 1; i <= iteraciones; i++) {
			@SuppressWarnings("rawtypes")
			TouchAction touch = new TouchAction(driver);
			touch.press(PointOption.point(xini, yini)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
					.moveTo(PointOption.point(xini, yfinal)).release().perform();
					}
		captureScreen2(rutaCarpeta, locator, Evidencia);
	}
	// TOUCH EN LA PANTALLA HORIZONTAL

	public void scrollHorizontal(File rutaFile, int X, int Y, int XFin, int YFin, int iteraciones) throws Exception {

		for (int i = 1; i <= iteraciones; i++) {
			@SuppressWarnings("rawtypes")
			TouchAction touch = new TouchAction(driver);
			touch.press(PointOption.point(X, Y)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
					.moveTo(PointOption.point(XFin, YFin)).release().perform();
			
		}
		captureScreen(rutaFile);
	}

	// TOCAR PANTALLA

	public void tocarPantalla(int a, int b) {
		@SuppressWarnings("rawtypes")
		TouchAction touch = new TouchAction(driver);
		touch.press(PointOption.point(a, b)).release().perform();
	}

	public void captureScreen(File rutaCarpeta) throws Exception {

		String hora = HoraSistema();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(rutaCarpeta + "\\" + hora + ".png"));

	}
	
	// CAPTURAS DE PANTALLA
	public void captureScreenError(File rutaCarpeta, By locator, String Evidencia, String msnError) throws Exception {
		
		if (Evidencia.equals("SI"))
		{
			String hora = HoraSistema();
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(rutaCarpeta+"\\"+hora+".png"));
			String rutaImagen = new File(rutaCarpeta+"\\"+hora+".png").toString();
			
			// INSTANCIAMOS LA CLASE GENERAR PDF
			GenerarReportePDF informePdf = new GenerarReportePDF();
			// SE PROCEDE A INSERTAR LOCALIZADOR E IMAGEN EN EL PDF
			informePdf.crearbodyError(locator, rutaImagen, msnError);
			// ELIMINAR IMAGEN CREADA
			eliminarArchivo(rutaImagen);
		}
		
	}
	
	// CAPTURAS DE PANTALLA2
	public void captureScreen2(File rutaCarpeta, By locator, String Evidencia) throws Exception {
		
		if (Evidencia.equals("SI"))
		{
			String hora = HoraSistema();
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(rutaCarpeta+"\\"+hora+".png"));
			String rutaImagen = new File(rutaCarpeta+"\\"+hora+".png").toString();
			
			// INSTANCIAMOS LA CLASE GENERAR PDF
			GenerarReportePDF informePdf = new GenerarReportePDF();
			// SE PROCEDE A INSERTAR LOCALIZADOR E IMAGEN EN EL PDF
			informePdf.crearbody(locator, rutaImagen);
			// ELIMINAR IMAGEN CREADA
			eliminarArchivo(rutaImagen);
		}
		
	}
	public void iniVideo() 
	{

		((CanRecordScreen) driver).startRecordingScreen
		(new AndroidStartScreenRecordingOptions().withVideoSize("1080x2340")
				.withTimeLimit(Duration.ofSeconds(200)));

	}
		
	public void finVideo(File rutaCarpeta) throws IOException 
	{
		String hora = HoraSistema();
		String video = ((CanRecordScreen) driver).stopRecordingScreen();
        byte[] decode = Base64.getDecoder().decode(video);
        FileUtils.writeByteArrayToFile(new File(rutaCarpeta+"\\"+hora+".mp4"), decode);
	}
	
	//ELIMAR ARCHIVOS
	public void eliminarArchivo(String rutaImagen) 
	{
		File fichero = new File(rutaImagen);
		fichero.delete();
	}

	public File crearCarpeta(String nomTest) {
		// ALMACENAMOS LA FECHA DEL SISTEMA
		String fecha = fechaHora();
		// CREAMOS EL NOMBRE DE LA CARPETA
		String nomCarpeta = nomTest + "-" + fecha;
		// OBTENEMOS LA RUTA DE ALOJAMIENTO DE SALIDA Y EL NOMBRE DE TEST A EJECUTAR
		File directorio = new File("./Output/" + nomCarpeta);
		// CREAMOS LA CARPETA
		directorio.mkdir();
		return directorio;
	}

	// METODO INICIAL
	public void urlAcceso(String url)

	{
		driver.get(url);
	}

}