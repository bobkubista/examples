// TODO build parameters
// TODO maybe tar the source and archive source
//try {
// define workspace
//    ws('$JOB_NAME-$BRANCH_NAME') {
stage 'checkout and compile'
node('master') {
    // define maven tool
    ensureMaven()
    // git with submodules
    git url: 'https://github.com/bobkubista/examples.git', branch: 'master'
    def v = version()
    if (v) {
        echo "Building version ${v}"
    }
    // compile
    sh "mvn -B clean compile"
    // archive
    step([$class: 'ArtifactArchiver', artifacts: '**/target/*.?ar', fingerprint: true])
    // stash
    stash includes: '*', name: 'buildStash'
}
stage 'unit testing'
//parallel (validate: {
node('master') {
    // unstash
    unstash 'buildStash'
    ensureMaven()
    // validate
    sh "mvn -B validate"
}
//},
//unitTest: {
// unit and integration tests
node('master') {
    // unstash
    unstash 'buildStash'
    ensureMaven()
    // TODO splitTests
    sh "mvn -B test -P test"
    // archive test results
    step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
    // stash
    stash includes: '*', name: 'testStash'
}
//})
stage 'integration testing'
node('master') {
    // unstash
    unstash 'testStash'
    ensureMaven()
    retry(count:2 ) { sh "mvn -B integration-test -P integration-test" }
    // archive test results
    step([$class: 'JUnitResultArchiver', testResults: '**/target/failsafe-reports/*.xml'])
    echo 'Finished Integration tests'
    // stash
    //    stash includes: '*', name 'itTestStash'
}
stage name: 'performance and front-end tests', concurrency: 1
node('master') {
    // unstash
    //    unstash 'itTtestStash'
    ensureMaven()

    // deploy to test, should eventually be build docker image and run
    sh "mvn -f services/rest-services/spring-services/user/user-service/pom.xml cargo:undeploy cargo:deploy -X "
    sh "mvn -f services/rest-services/spring-services/todo/todo-rest-service/pom.xml cargo:undeploy cargo:deploy -X "
    sh "mvn -f services/rest-services/cdi-services/email/email-cdi-service/pom.xml cargo:undeploy cargo:deploy -X "
    sh "mvn -f services/rest-services/cdi-services/datagathering/datagathering-rest-service/pom.xml cargo:undeploy cargo:deploy -X "
    // stash
    //    stash includes: '*', name 'deployStash'
}
//parallel 'quality scan': {
node('master') {
    // unstash
    //        unstash 'deployStash'
    // jmeter
    ensureMaven()
    sh 'mvn verify -P performance-test'
    // archive test results
    step([$class: 'JUnitResultArchiver', testResults: '**/*.jtl'])
    retry(5) {
        // TODO front end tests
        // TODO archive test results
    }
    // stash
    //        stash includes: '*', name 'qualityStash'
    //}
}
//}
stage name: 'Quality', concurrency: 3
node('master') {
// unstash
//    unstash 'deployStash'
ensureMaven()
// sonarqube
sh 'mvn sonar:sonar -P sonar'

// stash
//    stash includes: '*', name 'sonarStash'
}
stage name: 'archive'
node('master') {
// unstash
//    unstash 'qualityStash'
// nexus
ensureMaven()
sh 'mvn deploy'
// stash
//    stash includes: '*', name 'archiveStash'
}
// TODO ask user if we can release
// TODO stage name: 'release'
//node('master') {
//    // unstash
//    unstash 'archiveStash'
//    // TODO release
//
//}
//    }
//} catch (e) {
//    // TODO mail
//    // emailext attachLog: 'true', subject: '', body: ''
//    // mail bcc: '', body: '', cc: '', charset: '', from: '', mimeType: '', replyTo: '', subject: '', to: ''
//}

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