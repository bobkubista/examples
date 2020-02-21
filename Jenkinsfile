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
	                success {
	                    junit '**/target/surefire-reports/**/*.xml' 
	                }
	            }
            }
 	        stage('integration tests') {
            	steps{
              		sh 'mvn -B integration-test -P integration-test -am'
            	}
            	post {
	                success {
	                    junit '**/target/failsafe-reports/*.xml' 
	                }
	            }
         	}
		}        
      }
      stage('nexus'){
        steps{
        }
      }
      stage('deloyment tomcat'){
        steps{
        }
      }
      stage('jmeter'){
        steps{
        }
      }
      stage('sonar'){
        steps{
        }
      }
      stage('docker'){
        steps{
      }
    }
  }
}

