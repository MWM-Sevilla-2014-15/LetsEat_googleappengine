package com.silicom.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropUtil {
	//Note: Ideally doing it throught a property file.
	
	private static HashMap <String, String> propierties;

	public static HashMap<String, String> getPropierties() {
		return propierties;
	}
	
	public static void init() {
		propierties = new HashMap <String, String>();
		//SingUp
		propierties.put("SU_OK","Usuario Registrado Correctamente");
		propierties.put("SU_E_UD","Error en Proceso de Registro de Usuario. El nombre del usuario introducido ya se encuentra registrado en la Base de Datos.");
		propierties.put("SU_E_ED","Error en Proceso de Registro de Usuario. El email introducido ya se encuentra registrado en la Base de Datos.");
		//SingIn
		propierties.put("SI_OK","Usuario Autenticado Correctamente.");
		propierties.put("SI_E_UNR","Error en Proceso de Autenticación. El usuario introducido no se encuentra registrado.");
		propierties.put("SI_E_UPE","Error en Proceso de Autenticación. El usuario y password no coinciden.");
		//getPass
		propierties.put("GP_OK","Esa dirección de email se encuentra registrada en nuestro sistema. Le hemos enviado un email a esa cuenta para recuperar su contraseña.");
		propierties.put("GP_E_ENE","Lo sentimos pero esa dirección de email no se encuentra registrada en nuestro sistema.");
	}
	
}
