/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/

def callBuildandTest(){
  

        stage('Compile Code') {
			bat './mvnw.cmd clean compile -e'         
        }
		stage('Test Code') {
            
			bat './mvnw.cmd clean test -e'  
        }
		stage('Jar Code') {
					bat './mvnw.cmd clean package -e'       
    	}

}

def callRun(){
  stage('Run Jar') {
				bat 'start mvnw.cmd spring-boot:run'
        }
}

return this;