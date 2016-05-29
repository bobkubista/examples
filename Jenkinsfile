// TODO build parameters
// TODO maybe tar the source and archive source
stage 'checkout, merge and compile'
node('master') {
    checkout()
    load './buildFile.groovy'
    compile()
    step([$class: 'ArtifactArchiver', artifacts: '**/target/*.?ar', fingerprint: true])
    stash includes: '*', name: 'buildStash'
}
stage 'unit testing'
node('master') {
    unstash 'buildStash'
    validate()
}
node('master') {
    unstash 'buildStash'
    test()
    stash includes: '*', name: 'testStash'
}
stage 'integration testing'
node('master') {
    unstash 'testStash'
    itTesT()
}
stage name: 'performance and front-end tests', concurrency: 1
node('master') {
    deploy()
}
node('master') {
    performanceTest()
}
stage name: 'Quality', concurrency: 3
node('master') {
	sonar()
}
stage name: 'archive'
node('master') {
	nexus()
	}
	// TODO ask user if we can release
	// TODO stage name: 'release'

def checkout() {
    // git with submodules
    git url: 'https://github.com/bobkubista/examples.git', branch: 'master'
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
    // compile
    sh "mvn -B clean compile"
}

def validate() {
    ensureMaven()
    // validate
    sh "mvn -B validate"
}

def test() {
    ensureMaven()
    // TODO splitTests
    sh "mvn -B test -P test"
    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
}

def itTesT() {
    ensureMaven()
    retry(count:2 ) { sh "mvn -B integration-test -P integration-test" }
    // archive test results
    step([$class: 'JUnitResultArchiver', testResults: '**/target/failsafe-reports/*.xml'])
    echo 'Finished Integration tests'
}

def deploy() {
    ensureMaven()
    
        // deploy to test, should eventually be build docker image and run
        sh "mvn -f services/rest-services/spring-services/user/user-service/pom.xml cargo:undeploy cargo:deploy -X "
        sh "mvn -f services/rest-services/spring-services/todo/todo-rest-service/pom.xml cargo:undeploy cargo:deploy -X "
        sh "mvn -f services/rest-services/cdi-services/email/email-cdi-service/pom.xml cargo:undeploy cargo:deploy -X "
        sh "mvn -f services/rest-services/cdi-services/datagathering/datagathering-rest-service/pom.xml cargo:undeploy cargo:deploy -X "
}

def performanceTest() {
    // jmeter
    ensureMaven()
    sh 'mvn verify -P performance-test'
    // archive test results
    step([$class: 'JUnitResultArchiver', testResults: '**/*.jtl'])
    retry(5) {
        // TODO front end tests
        // TODO archive test results
    }
}

def sonar() {
    ensureMaven()
    // sonarqube
    sh 'mvn sonar:sonar -P sonar'
}

def mail() {
    // TODO mail
    // emailext attachLog: 'true', subject: '', body: ''
    // mail bcc: '', body: '', cc: '', charset: '', from: '', mimeType: '', replyTo: '', subject: '', to: ''
}

def nexus() {
    // nexus
    ensureMaven()
    sh 'mvn deploy'
}