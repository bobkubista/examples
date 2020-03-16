pipeline { 
  agent any  
  tools { 
    maven 'Maven' 
    jdk '1.8' 
  }
  stages { 
    stage('Compile') { 
      steps { 
        sh 'mvn clean compile'
        sh 'mvn validate'
      }
    }
	stage('Unit tests') {
     steps {
       sh 'mvn test -P test'
     }
     post {
       always {
         junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
       }
     }
   }
   stage('integration tests') {
     steps{
       sh 'mvn integration-test -P integration-test'
     }
     post {
       always {
         junit testResults: '**/target/failsafe-reports/*.xml', allowEmptyResults: true 
       }
     }
   }
   stage('nexus'){
     steps{
       sh 'mvn deploy'
     }
   }
   stage('deloyment tomcat'){
     steps{
     	echo 'deploy to tomcat'
       //sh 'mvn cargo:deploy -f services/rest-services/spring-services/user/user-service/pom.xml'
	   //sh 'mvn cargo:deploy -f services/rest-services/cdi-services/datagathering/datagathering-rest-service/pom.xml'
     }
   }
   stage('performance'){
     steps{
       echo 'performance step'
       // Need to fix deployment first
       //sh 'mvn verify -P performance-test'
     }
     post {
 	   always {
         junit testResults: '**/*.jtl;**/TEST-*.xml', allowEmptyResults: true 
       }
     }
   }
   stage('sonar'){
     steps{
       sh 'mvn sonar:sonar -P sonar -e'
     }
   }
   stage('docker'){
     steps{
       echo 'containerize step'
     }
   }
  }
}
