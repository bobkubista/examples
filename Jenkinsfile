pipeline { 
    agent any  
    tools { 
        maven 'Maven' 
        jdk '1.8' 
    }
    stages { 
        stage('Compile') { 
            steps { 
               sh 'mvn -B clean compile -am'
               sh 'mvn -B -T 1C validate -am'
        //validate
            }
        }
        stage('Test'){
          parallel {
			stage('Unit tests') {
	        	steps {
	        		sh 'mvn test'
	        	}
	        	post {
	                always {
	                    junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
	                }
	            }
            }
 	        stage('integration tests') {
            	steps{
              		//sh 'mvn -B integration-test -P integration-test -am'
              		echo 'integration tests'
            	}
//           	post {
//	                always {
//	                    junit '**/target/failsafe-reports/*.xml' 
//	                }
//	            }
        	}
		}        
      }
      stage('nexus'){
        steps{
          echo 'nexus step'
        }
      }
      stage('deloyment tomcat'){
        steps{
        echo 'deploy step'
        }
      }
      stage('performance'){
        steps{
        echo 'performance step'
        }
      }
      stage('sonar'){
        steps{
        echo 'sonar step'
        }
      }
      stage('docker'){
        steps{
        echo 'containerize step'
      }
    }
  }
}

