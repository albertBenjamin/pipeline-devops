import org.cl.*

def call(){
	pipeline {
    agent any

    		parameters {
			choice(
				name: 'Herramienta',
				choices: ['Gradle', 'Maven'],
				description: 'Selección herramienta de construcción')
			string(
				name: 'Stage',
				defaultValue: '',
				description:
				'''Selección de stage.
				Opciones para Gradle: Build; Sonar; Run; Test; Nexus. 
				Opciones para Maven: Compile; Unit; Jar; Sonar; Test.''')
							}

    stages {
        stage('Pipeline') {
        	steps{
	            script{
					
					// Captura herramienta de construcción seleccionada.
					tool = params.Herramienta;
					// Inicializa env.stage global para capturar nombre de etapa.
					env.stage = '';
					// Asigna las etapas seleccionadas a variable global env.stagesString.
					env.stagesString = params.Stage.toLowerCase();
					// Transforma stagesString en array.
					String[] stagesList = env.stagesString.split(';');

					def funciones   = new Funciones()

					if(funciones.validarStages(stagesList)){
						println 'Funcionó'
					}

	            }
	        }
        }

    }

}



}

return this;
