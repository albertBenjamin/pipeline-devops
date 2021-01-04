package org.cl

def validarstages(String[] param){
	return true;
}

def validarOrderStages(){
	return false;
}


def unirDosStrings(String param1, String param2){
	return param1 + param2
}

def mostrarNombre(){
	//lectua de archivo a json, lo convierte en mapa
	// archivos en crpeta resource con el class path correspondiente
	def request = libraryResource 'org/cl/nombres.json'
	def json = readJSON text : request

	return json.nombre
}

return this;