// TODO build parameters
// TODO maybe tar the source and archive source
try{
    checkout()
    validate()
    parallel 
	'test': {test()},
	'itTest': {itTest()}
    //test()
    //itTest()
    deploy()
    //performanceTest()
	sonar()
	nexus()
	release()
} catch(Exception ex) {
	mail()
}

def checkout() {
	stage 'checkout, merge and compile'
	node('master') {
	    // git with submodules
	    git url: 'https://github.com/bobkubista/examples.git', branch: 'master'
	    load './buildFile.groovy'
	    compile()
	    step([$class: 'ArtifactArchiver', artifacts: '**/target/*.?ar', fingerprint: true])
	    stash includes: '*', name: 'buildStash'
	}
}

/**
 * Deploy maven on slave if needed and add it to the path
 */
def ensureMaven() {
	env.Path = "${tool 'M3'}/bin:${env.PATH}"
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
	node('master') {
	    unstash 'buildStash'
	    ensureMaven()
	    // validate
	    sh "mvn -B -T 1C validate -am"
	}
}

def test() {
	node('master') {
	    unstash 'buildStash'
	    ensureMaven()
	    // TODO splitTests
	    sh "mvn -B -T 1C test -P test -am"
	    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
	    stash includes: '*', name: 'testStash'
	}
}

def itTest() {stage 'integration testing'
	node('master') {
	    unstash 'testStash'
	    ensureMaven()
	    retry(count:2 ) { sh "mvn -B integration-test -P integration-test -am" }
	    // archive test results
	    step([$class: 'JUnitResultArchiver', testResults: '**/target/failsafe-reports/*.xml'])
	    echo 'Finished Integration tests'
	}
}

def deploy() {
stage name: 'performance and front-end tests', concurrency: 1
	node('master') {
	    ensureMaven()
        // deploy to test, should eventually be build docker image and run
        sh "mvn -T 1C -f services/rest-services/spring-services/user/user-service/pom.xml cargo:undeploy cargo:deploy -X "
        // sh "mvn -T 1C -f services/rest-services/spring-services/todo/todo-rest-service/pom.xml cargo:undeploy cargo:deploy -X "
        sh "mvn -T 1C -f services/rest-services/cdi-services/email/email-cdi-service/pom.xml cargo:undeploy cargo:deploy -X "
        sh "mvn -T 1C -f services/rest-services/cdi-services/datagathering/datagathering-rest-service/pom.xml cargo:undeploy cargo:deploy -X "
	}
}

def performanceTest() {
	node('master') {
	    // jmeter
	    ensureMaven()
	    sh 'mvn verify -P performance-test -T 1C -am'
	    // archive test results
	    step([$class: 'JUnitResultArchiver', testResults: '**/*.jtl'])
	    retry(5) {
	        // TODO front end tests
	        // TODO archive test results
	    }
    }
}

def sonar() {
	stage name: 'Quality', concurrency: 3
	node('master') {
	    ensureMaven()
	    // sonarqube
	    sh 'mvn sonar:sonar -P sonar -am -T 1C '
	}
}

def mail() {
    // TODO mail
    // emailext attachLog: 'true', subject: '', body: ''
    // mail bcc: '', body: '', cc: '', charset: '', from: '', mimeType: '', replyTo: '', subject: '', to: ''
}

def nexus() {
	stage name: 'archive'
	node('master') {
	    // nexus
	    ensureMaven()
	    sh 'mvn deploy -T 1C -am'
	}
}

def release() {
	stage name: 'release'
	node('master') {
	    if (env.BRANCH_NAME == "master") {
	        // TODO Release
	        // TODO ask user if we can release
	        sh 'mvn -T 1C -am -DdryRun=true -e -X release:perform'
	    }
	
	}
}
