# examples
This is my examples of code that I am trying out stuff with. It is for reference use.

##tools:
wiki : http://192.168.1.105/mediawiki   

jenkins: http://192.168.1.108:8080

sonar: http://192.168.1.108:9000

nexus: http://192.168.1.108:8081

webservices: http://192.168.1.110

database: http://192.168.1.109


##principals:
* SOLID -> Single responsibility, open/closed, liskov substitution, interface segregation, dependency inversion
* Code quality and test coverage 
* Agility over speed
* Design patterns
* Minimal code duplication
* Test definition http://stackoverflow.com/questions/520064/what-is-unit-test-integration-test-smoke-test-regression-test
* Unit tests on 1 class only, the rest is stubbed/mocked
* Rest IT with jersey on the rest interface
* Front end tests with cucumber and selinium
* Always regression test
* On proxies, only smoke test, maybe use https://github.com/mkotsur/restito
* Pre-flight test is does it deploy and do you get rest definition or index page

* If no state, then default methodes in interfaces
* JPA for ORM

##Todo:
Document https://github.com/cko/predefined_maven_properties/blob/master/README.md

### Done:

### New:
* NEW buildname plugin
* NEW add pagination
* NEW add search

### Existing:
* add other webservices to deploy (https://codehaus-cargo.github.io/cargo/Starting+and+stopping+a+container.html)
* replace deploy job with multi configurable job, which doesn't run parallel
* make builds use branches
* make the builds smaller by only building the correct modules and the modules that depend on them. Maybe with * the generate maven build job automatically.
* Use job inheritence with the maven jenkins plugin
* Make use of joins
* fix reverse proxy problem (http://httpd.apache.org/docs/2.2/mod/mod_proxy.html)
(https://groups.google.com/forum/#!searchin/jenkinsci-users/proxy/jenkinsci-users/jcllTOoD684/fDQQZ6WpwNwJ)
(http://jenkins-ci.361315.n4.nabble.com/Apache-reverse-proxy-to-Jenkins-servlet-on-port-8080-td3444546.html)
* build step javadoc
* build step code coverage
* build automatic VM scripts (maybe jenkins job) May need to buy stuff, so no
* configure tomcat (http://www.jsf2.com/using-cdi-and-jsf-2.2-faces-flow-in-tomcat/#steps)
* refactor emails to make better use of lambda's
* take another look at future
* optionals
* http 2
* JDeps -JDKinternals app.jar
* refactor converters to lambda's
* investigate Job-dsl for jenkins
* setup tomcat env dev/test/prod (https://www.voxxed.com/blog/2015/10/multiple-tomcat-instances/)
* smoke & system tests in maven build 
(http://www.petrikainulainen.net/programming/maven/integration-testing-with-maven/)(http://www.petrikainulainen.net/programming/maven/integration-testing-with-maven/)
* put right impl of JEE in tomcat lib (http://arjan-tijms.omnifaces.org/2014/05/implementation-components-used-by.html)
* build SSO WebService
* build SSO WebApp
* Integrate Cucumber & selenium for frontend testing, maybe with auto spinup and spindown VM's
* cucumber + selenium + dbunit/restito for frontend acceptence tests: http://stackoverflow.com/questions/18164579/how-do-i-specify-a-separate-maven-goal-for-running-cucumber-acceptance-tests
http://stackoverflow.com/questions/22462181/how-do-i-run-a-selenium-test-using-maven-from-the-command-line
https://code.google.com/p/selenium/wiki/GettingStarted
* proxy tests with https://github.com/mkotsur/restito
* refactor emails to make better use of lambda's
* refactor generic code to make better use of lambda's
* take another look at future
* service circuit breakers on too many timeouts of webservices. 
* refactor converters to lambda's
* build common front end components and styling module
* static methodes in interfaces
* Asynchronous calls 
* Non blocking IO
* Security:
** stateless security client side cross site request forgery tokens (CSRF tokens) in cookie and http header check in a filter/interceptor
**spring security
**keyclocke
* apache kafka
* spring data
* Websockets
* Spring boot
* Docker
* Jetty
* Message driven beans
* EJB
* JTA
* JMS
* Batch processing
* Lambda logging
* new Date api 
* Vert.x
* Webservlet: Java EE essentials
* SOAP
* Rest services
* starting up service
* @queryparam
* @cookieparam
* @hearderparam
* @matrixparam
* JSESSIONID
* Error handling in clients
* datagathering client
* jsf
* angular
* jquery
