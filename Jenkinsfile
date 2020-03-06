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
      }
    }
	stage('Unit tests') {
     steps {
       sh 'mvn test -P test -e -X'
     }
     post {
       always {
         junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
       }
     }
   }
   stage('integration tests') {
     steps{
       sh 'mvn -B integration-test -P integration-test -am'
     }
     post {
       always {
         junit testResults: '**/target/failsafe-reports/*.xml', allowEmptyResults: true 
       }
     }
   }
   stage('nexus'){
     steps{
       echo 'deploy to nexus'
       // TODO enable this again, when nexus works with java 11
       //sh 'mvn deploy'
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
       sh 'mvn sonar:sonar -P sonar'
     }
   }
   stage('docker'){
     steps{
       echo 'containerize step'
     }
   }
  }
}
