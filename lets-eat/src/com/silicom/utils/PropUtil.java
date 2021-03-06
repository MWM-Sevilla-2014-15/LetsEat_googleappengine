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
	public final static String SU_E_U = "SU_E_U";
	public final static String SI_OK ="SI_OK";
	public final static String SI_E_UPE ="SI_E_UPE";
	public final static String SI_E_UNR ="SI_E_UNR";
	public final static String GP_OK ="GP_OK";
	public final static String GP_E_ENE ="GP_E_ENE";
	public final static String GP_E_IE = "GP_E_IE";
	public final static String RR_OK = "RR_OK";
	public final static String RR_E_RD = "RR_E_RD";
	public final static String RR_E_U = "RR_E_U";
	public final static String IA_OK = "IA_OK";
	public final static String IA_E = "IA_E";
	public final static String GR_I_EMP = "GR_I_EMP";
	public final static String BR_OK = "BR_OK";
	public final static String BR_E_RNF =  "BR_E_RNF";
	public final static String BR_E_U = "BR_E_U";
	public final static String BR_E_RF = "BR_E_RF";
	public final static String RT_OK = "RT_OK";
	public final static String RT_E_U = "RT_E_U";
	public final static String RT_I_NRBT = "RT_I_NRBT";

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
		propierties.put(SU_E_U,"Error en Proceso de Registro de Usuario. Error Desconocido");
		//SingIn
		propierties.put(SI_OK,"Usuario Autenticado Correctamente.");
		propierties.put(SI_E_UNR,"Error en Proceso de Autenticación. El usuario introducido no se encuentra registrado.");
		propierties.put(SI_E_UPE,"Error en Proceso de Autenticación. El usuario y password no coinciden.");
		//getPass
		propierties.put(GP_OK,"Esa dirección de email se encuentra registrada en nuestro sistema. Le hemos enviado un email a esa cuenta para recuperar su contraseña.");
		propierties.put(GP_E_ENE,"Error en Proceso de Recuperacion de Password. Lo sentimos pero esa dirección de email no se encuentra registrada en nuestro sistema.");
		propierties.put(GP_E_IE,"Error en Proceso de Recuperacion de Password. Email introducido para el envio no es válido");
		//isAdmin
		propierties.put(IA_OK,"Admin Autenticado Correctamente.");
		propierties.put(IA_E,"Admin NO Autenticado");
		//restaurantRegister
		propierties.put(RR_OK,"Restaurante Registrado Correctamente");
		propierties.put(RR_E_RD,"Error en Proceso de Registro de Restaurante. El nombre del restaurante introducido ya se encuentra registrado en la Base de Datos.");
		propierties.put(RR_E_U,"Error en Proceso de Registro de Restaurante. Error Desconocido");
		//get restaurantes
		propierties.put(GR_I_EMP,"Lista de Restaurantes vacía");
		//restaurantBooking
		propierties.put(BR_OK,"Restaurante Reservado Correctamente");
		propierties.put(BR_E_RNF,"Error en Proceso de Reserva de Restaurante. En el sistema no existe ningun restaurante con el id introducido");
		propierties.put(BR_E_U,"Error en Proceso de Reserva de Restaurante. Error Desconocido");
		propierties.put(BR_E_RF,"Error en Proceso de Reserva de Restaurante. No hay el número de mesas introducidas libres para el restaurante seleccionado");
		//reset booked tables (cron)
		propierties.put(RT_OK,"Cron Proceso de Reset de las Mesas del Restaurante realizado correctamente");
		propierties.put(RT_I_NRBT,"Aviso Cron Proceso de Reset de las Mesas del Restaurante. no hay meses reservadas");
		propierties.put(RT_E_U,"Error Cron Proceso de Reset de las Mesas del Restaurante. Error Desconocido");
	}
}
