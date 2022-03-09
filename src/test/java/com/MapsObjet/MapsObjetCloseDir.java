package com.MapsObjet;

import org.openqa.selenium.By;
import com.testNG.ClaseBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class MapsObjetCloseDir extends ClaseBase {

	// CONSTRUCTOR DE LA CLASE
	public MapsObjetCloseDir(AppiumDriver<MobileElement> driver)

	{
		super(driver);
		this.driver = driver;
	}
		
	// INGRESAR DATOS
	protected By btnInicio = By.xpath("//android.widget.Button[@resource-id='com.clarocolombia.miclaro.debug:id/btnInicio']");
	protected By btnInicio2 = By.xpath("//android.widget.Button[@resource-id='com.clarocolombia.miclaro.debug:id/btnInicio']");
	protected By btnImgUsuario = By.xpath("//android.widget.ImageView[@resource-id='com.clarocolombia.miclaro.debug:id/imgPerfil']");
	protected By btnCerrarSesion= By.xpath("//android.widget.TextView[@resource-id='com.clarocolombia.miclaro.debug:id/tvCerrarSesion']");
	protected By btnConfirmar = By.xpath("//android.widget.Button[@resource-id = 'com.clarocolombia.miclaro.debug:id/btn_aceptar']");
	
	// VERIFICADOR
	protected By verifiHola = By.xpath("//android.widget.TextView [@text = '¡Hola!']");
}
