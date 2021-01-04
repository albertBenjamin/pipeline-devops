package org.cl

def validarStages(String[] param){
	def listStages= ['Compile','Test','Jar','sonar','Run','nexus']
    listStages[param.size + 1 .. 5]
	def myBooleanVariable = assert listStages as Set == param as Set
	return myBooleanVariable
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
