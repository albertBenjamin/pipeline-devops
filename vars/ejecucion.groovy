def call(){
	pipeline {
    agent any

    stages {
        stage('Pipeline') {
        	steps{
	            script{
	            	
	            	def filepathString =''
	            	def ejecucion = ''
	            		switch(params.buildtool){
						case'gradle':
						 ejecucion = 'gradle'
						 filepathString = 'build/libs/DevOpsUsach2020-0.0.1.jar'
						break
						default:
						 ejecucion = 'maven'
						 filepathString = 'build/DevOpsUsach2020-0.0.1.jar'
						break 
					}

					gradle.callBuildandTest()
					
					stage('sonar') {
                        def scannerHome = tool 'sonar-scanner';
                        withSonarQubeEnv('sonar') {
                            bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build" 
                         }                     
                    }
					
					gradle.callRun()

					stage('rest'){
						 sleep 10
						 bat 'curl http://localhost:8083/rest/mscovid/estadoMundial'
					}
					stage('nexus'){
						nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-nexus', 
						packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: '', 
						filePath: filepathString]], 
						mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]

					}

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