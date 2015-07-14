package com.silicom.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropUtil {
	//Note: Ideally doing it throught a property file.
	
	//Messages Codes
	public final static String SU_OK ="SU_OK";
	public final static String SU_E_UD ="SU_E_UD";
	public final static String SU_E_ED ="SU_E_ED";
	public final static String SI_OK ="SI_OK";
	public final static String SI_E_UPE ="SI_E_UPE";
	public final static String SI_E_UNR ="SI_E_UNR";
	public final static String GP_OK ="GP_OK";
	public final static String GP_E_ENE ="GP_E_ENE";
	public final static String GP_E_IE = "GP_E_IE";
	public final static String RR_OK = "RR_OK";
	public final static String RR_E_RD = "RR_E_RD";
	public final static String IA_OK = "IA_OK";
	public final static String IA_E = "IA_E";
	//URL redrirect
	public final static String RESTAURANT_REGIST_URL = "/resgis_resta.jsp";
	public final static String ADMIN_URL = "/index.jsp";
	
	//Messages Codes Translate
	private static HashMap <String, String> propierties;

	public static HashMap<String, String> getPropierties() {
		return propierties;
	}
	
	public static void init() {
		propierties = new HashMap <String, String>();
		//SingUp
		propierties.put(SU_OK,"Usuario Registrado Correctamente");
		propierties.put(SU_E_UD,"Error en Proceso de Registro de Usuario. El nombre del usuario introducido ya se encuentra registrado en la Base de Datos.");
		propierties.put(SU_E_ED,"Error en Proceso de Registro de Usuario. El email introducido ya se encuentra registrado en la Base de Datos.");
		//SingIn
		propierties.put(SI_OK,"Usuario Autenticado Correctamente.");
		propierties.put(SI_E_UNR,"Error en Proceso de Autenticación. El usuario introducido no se encuentra registrado.");
		propierties.put(SI_E_UPE,"Error en Proceso de Autenticación. El usuario y password no coinciden.");
		//getPass
		propierties.put(GP_OK,"Esa dirección de email se encuentra registrada en nuestro sistema. Le hemos enviado un email a esa cuenta para recuperar su contraseña.");
		propierties.put(GP_E_ENE,"Error en Proceso de Recuperacion de Password. Lo sentimos pero esa dirección de email no se encuentra registrada en nuestro sistema.");
		propierties.put(GP_E_IE,"Error en Proceso de Recuperacion de Password. Email introducido para el envio no es válido");
		//isAdmin
		propierties.put(SI_OK,"Admin Autenticado Correctamente.");
		propierties.put(SI_E_UNR,"Admin NO Autenticado");
		//restaurantRegister
		propierties.put(RR_OK,"Restaurante Registrado Correctamente");
		propierties.put(RR_E_RD,"Error en Proceso de Registro de Restaurante. El nombre del restaurante introducido ya se encuentra registrado en la Base de Datos.");
	}
}
