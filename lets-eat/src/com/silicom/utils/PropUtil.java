package com.silicom.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropUtil {
	//Note: Ideally doing it throught a property file.
	
	HashMap <String, String> propierties;

	public HashMap<String, String> getPropierties() {
		return propierties;
	}
	
	public PropUtil (){
		propierties = new HashMap <String, String>();
		propierties.put("SU_OK","Usuario Registrado Correctamente");
		propierties.put("SU_E_UD","Error en Proceso de Registro de Usuario. El nombre del usuario introducido ya se encuentra registrado en la Base de Datos");
		propierties.put("SI_OK","Usuario Autenticado Correctamente");
		propierties.put("SI_E_UNR","Error en Proceso de Autenticacion. El usuario introducido no se encuentra registrado");
		propierties.put("SI_E_UPE","Error en Proceso de Autenticacion. El usuario y password no coinciden");
	}
	
	
	
}
