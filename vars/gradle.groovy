/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def callBuildandTest(){
	  stage('build & test'){
	    bat './gradlew clean build'
	 }
}

def callRun(){

	  stage('run'){
	    bat 'start gradlew bootRun &'
	 }
}



return this;