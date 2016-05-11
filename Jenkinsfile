load 'buildFile.groovy'
// TODO build parameters
// TODO maybe tar the source and archive source
//try {
// define workspace
//    ws('$JOB_NAME-$BRANCH_NAME') {
stage 'checkout, merge and compile'
node('master') {
    checkout()
    compile()
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
    validate()
}
//},
//unitTest: {
// unit and integration tests
node('master') {
    // unstash
    unstash 'buildStash'
    test()
    // stash
    stash includes: '*', name: 'testStash'
}
//})
// TODO merge to master
stage 'integration testing'
node('master') {
    // unstash
    unstash 'testStash'
    itTesT()
    // stash
    //    stash includes: '*', name 'itTestStash'
}
stage name: 'performance and front-end tests', concurrency: 1
node('master') {
    // unstash
    //    unstash 'itTtestStash'
    deploy()
    // stash
    //    stash includes: '*', name 'deployStash'
}
//parallel 'quality scan': {
node('master') {
    // unstash
    //        unstash 'deployStash'
    performanceTest()
    // stash
    //        stash includes: '*', name 'qualityStash'
    //}
}
//}
stage name: 'Quality', concurrency: 3
node('master') {
	// unstash
	//    unstash 'deployStash'
	sonar()
	
	// stash
	//    stash includes: '*', name 'sonarStash'
}
stage name: 'archive'
node('master') {
	// unstash
	//    unstash 'qualityStash'
	// nexus()
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
//    mail()
//}