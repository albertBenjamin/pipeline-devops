import org.cl.*

def call(){
	pipeline {
    agent any

    stages {
        stage('Pipeline') {
        	steps{
	            script{
			

 					 def funciones   = new Funciones()

                            stage('Inicio'){
                                println 'Inicio'
                                println 'String 1: ' + param1
                                println 'String 2: ' + param2

                                gradle.call()
                            }

                            stage('Union'){
                                println 'Union de 2 Strings: ' + funciones.unirDosStrings(param1, param2)          
                            }

                            stage('MostrarNombre'){
                                println 'Nombre obtenido desde Json: ' + funciones.mostrarNombre()
                            }

                        } catch(Exception e) {
                            error ('Ha ocurrido el siguiente error: ' + e)
                        }
/*	            	filepathString =''
	            		switch(params.buildtool){
						case'gradle':
						 filepathString = 'build/libs/DevOpsUsach2020-0.0.1.jar'
						 gradle.callBuildandTest()
						 sonar.callSonar()
						 gradle.callRun()
						break
					 filepathString = 'build/DevOpsUsach2020-0.0.1.jar'
						 maven.callBuildandTest()
						 sonar.callSonar()
						 maven.callRun()
						break 
					}
					stage('rest'){
						 sleep 10
						 bat 'curl http://localhost:8083/rest/mscovid/estadoMundial'
					}
					nexus.callNexus(filepathString)*/

	            }
	        }
        }

    }
    post {
        success{
			slackSend channel: 'U01DD0BR7H8', color: 'good', message: 'Ejecución exitosa :'+['Albert Muñoz ']+[env.JOB_NAME]+[params.buildtool], teamDomain: 'dipdevopsusach2020', tokenCredentialId: 'slack'
        }
        failure{
            slackSend channel: 'U01DD0BR7H8', color: 'danger', message: 'Ejecución fallida :'+['Albert Muñoz ']+[env.JOB_NAME]+[params.buildtool]+' en stage' + [env.STAGE_NAME], teamDomain: 'dipdevopsusach2020', tokenCredentialId: 'slack'
        }
    }
}



}

return this;
