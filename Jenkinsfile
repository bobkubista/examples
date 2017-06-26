#!groovy
try{
    checkout()
    validate()

    test()
    itTest()
    deploy()
    //performanceTest()
    sonar()
    nexus()
} catch(Exception ex) {
	currentBuild.result = 'FAILED'
	//mail()
	throw ex
}

def checkout() {
	stage 'checkout, merge and compile'
	node {
		checkout([$class: 'GitSCM', branches: [[name: '*']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '15e8261d-cf9d-4d33-ae9e-04d7d33c851d', url: 'https://github.com/bobkubista/examples.git']]])	    
		compile()
	    step([$class: 'ArtifactArchiver', artifacts: '**/target/*.?ar', fingerprint: true])
	    stash includes: '*', name: 'source'
	}
}

/**
 * Deploy maven on slave if needed and add it to the path
 */
def ensureMaven() {
	tool name: 'Maven', type: 'maven'
}

def version() {
	def matcher = readFile('pom.xml') =~ '</version>'
	matcher ? matcher[0][1] : null
}

def compile() {
    ensureMaven()
    // compile TODO add -pl, see: http://zeroturnaround.com/rebellabs/your-maven-build-is-slow-speed-it-up/?utm_source=twitter&utm_medium=social&utm_campaign=rebellabs
    sh "mvn -B compile -am"
}

def validate() {
	stage 'unit testing'
	node {
	    unstash 'source'
	    ensureMaven()
	    // validate
	    sh "mvn -B -T 1C validate -am"
	}
}

def test() {
	node {
	    unstash 'source'
	    ensureMaven()
	    // TODO splitTests
	    sh "mvn test -Ptest -e -X"
	    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
	    stash includes: '*', name: 'source'
	}
}

def itTest() {
	node {
	    unstash 'source'
	    ensureMaven()
	    retry(count:2 ) { sh "mvn -B integration-test -P integration-test -am" }
	    // archive test results
	    step([$class: 'JUnitResultArchiver', testResults: '**/target/failsafe-reports/*.xml'])
	    stash includes: '*', name: 'source'
	    echo 'Finished Integration tests'
	}
}

def deploy() {
stage name: 'performance and front-end tests', concurrency: 1
	timeout(time:1, unit:'HOURS') {
	    input 'Do you approve release candidate?'
	}
	node {
	ensureMaven()
        // deploy to test, should eventually be build docker image and run
        sh "mvn -T 1C -f services/rest-services/spring-services/user/user-service/pom.xml cargo:undeploy cargo:deploy -X "
        // sh "mvn -T 1C -f services/rest-services/spring-services/todo/todo-rest-service/pom.xml cargo:undeploy cargo:deploy -X "
        // sh "mvn -T 1C -f services/rest-services/cdi-services/email/email-cdi-service/pom.xml cargo:undeploy cargo:deploy -X "
        sh "mvn -T 1C -f services/rest-services/cdi-services/datagathering/datagathering-rest-service/pom.xml cargo:undeploy cargo:deploy -X "
	}
}

def performanceTest() {
	node {
	    // jmeter
	    ensureMaven()
	    sh 'mvn verify -P performance-test -T 1C -am'
	    // archive test results
	    step([$class: 'JUnitResultArchiver', testResults: '**/*.jtl'])
	    retry(5) {
	        // TODO front end tests
	        // TODO archive test results
	    }
	    stash includes: '*', name: 'source'
    }
}

def sonar() {
	stage name: 'Quality', concurrency: 3
	node {
	    ensureMaven()
	    // sonarqube
	    sh 'mvn sonar:sonar -P sonar -am -T 1C '
	}
}

def mail() {
	mail to: '<to>', subject: '<subject>', body: '<body>', attachLog: true
}

def nexus() {
	stage name: 'archive'
	node {
	    // nexus
	    ensureMaven()
	    sh 'mvn deploy -T 1C -am -X -e'
	}
}

def release() {
	if (env.BRANCH_NAME == "master") {
	    stage name: 'release'
	    timeout(time:5, unit:'DAYS') {
	    	input 'Do you approve release candidate?'
	    }
	    node {
	        // TODO Release
	        // TODO ask user if we can release
		sh 'mvn -T 1C -am -e -X release:prepare'
	        sh 'mvn -T 1C -am -DdryRun=true -e -X release:perform'
	    }
	
	}
}
