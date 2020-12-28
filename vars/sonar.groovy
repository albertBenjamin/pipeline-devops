
def callSonar(){
        def scannerHome = tool 'sonar-scanner';
        withSonarQubeEnv('sonar') {
            bat "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build" 
         }                     
   }

return this;