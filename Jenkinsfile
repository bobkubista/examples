try {
  // TODO define workspace
  stage 'build'
    node('master') { 
      // define maven tool
      def mvnHome = tool 'M3'
      env.PATH = "${mvnHome}/bin:${env.PATH}"
  	  // git with submodules
  	  git credentialsId: 'e9d3c47c-244a-4be0-80a7-492a01628556', url: 'https://github.com/bobkubista/examples.git', branch: 'master'
  	  // compile
  	  sh "mvn -B compile"
  	  // stash
  	  stash includes: '*', name: 'buildStash'
  	}
  	//parallel 'testing' 
  	  node('master') {
  	    // unstash
  	    unstash 'buildStash' 
  	    withEnv(["PATH+MAVEN=${tool 'M3'}/bin"]) {
  	      // validate
  	      sh "mvn -B validate"
  	      // TODO unit and integration tests
  	      sh "mvn -B test -P test"
  	      sh "mvn -B integration-test -P integration-test"
  	    }
  	    // stash
  	    stash includes: '**', name 'testStash'
  	  }    
  stage name: 'deployed-test', concurrency: 1
    node('master') { 
  	  // unstash
  	  unstash 'testStash' withEnv(["PATH+MAVEN=${tool 'm3'}/bin"])
  	  
  	  // TODO deploy to test, should eventually be build docker image and run
    	// TODO stash
      }
  	  parallel 'quality scan': {
  	    node('master') {
  	      // TODO unstash 
	  	  // TODO jmeter
	  	  retry(5) {
	  	    // TODO front end tests
	  	    // TODO stash
	    	}
	   }
    }
  stage name: 'Quality', concurrency: 3
    node('master') {
      // TODO unstash
      // TODO sonarqube
      // TODO stash
    }  
  stage name: 'archive'
    node('master') {
      // TODO unstash
      // TODO nexus
      // TODO stash
    }
  stage name: 'release'
    node('master') {
      // TODO unstash
      // TODO release
    }
  } catch (e) {
  // TODO mail 
}