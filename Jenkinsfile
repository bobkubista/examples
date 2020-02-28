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
       echo 'integration tests need to be fixed. Database connection is not right yet'
       // sh 'mvn -B integration-test -P integration-test -am'
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
       sh 'mvn cargo:deploy -X'
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
