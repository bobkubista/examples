# examples
This is my examples of code that I am trying out stuff with. It is for reference use.

##tools:
[wiki](http://192.168.1.105/mediawiki)   

[jenkins](http://build.internal.bobkubista.com:8080)

[sonar](http://sonar.internal.bobkubista.com:9000)

[nexus](http://nexus.internal.bobkubista.com:8081)

[database](http://postgres.internal.bobkubista.com)

[webservices](http://webservice.internal.bobkubista.com)

[docker](http://docker.internal.bobkubista.com)


##principals:
* SOLID -> Single responsibility, open/closed, liskov substitution, interface segregation, dependency inversion
* Code quality and test coverage 
* Agility over speed
* Design patterns
* Minimal code duplication
* [Test definition](http://stackoverflow.com/questions/520064/what-is-unit-test-integration-test-smoke-test-regression-test)
* Unit tests on 1 class only, the rest is stubbed/mocked
* Rest IT with jersey on the rest interface
* Front end tests with cucumber and selinium
* Always regression test
* [On proxies, only smoke test](https://github.com/mkotsur/restito)
* Pre-flight test is does it deploy and do you get rest definition or index page

* If no state, then default methodes in interfaces
* JPA for ORM

##versioning:
 * new functionality: major version bump
 * technical changes: minor version bump
 * bugfixes: patch version bump
 

##Todo:

### Done:
  * [add other webservices to deploy](https://codehaus-cargo.github.io/cargo/Starting+and+stopping+a+container.html)
  * replace deploy job with multi configurable job, which doesn't run parallel
  * make builds use branches
  * Make use of joins
  * add pagination
  * add search
  * add circuit breaker to clients (http://www.javaworld.com/article/2824163/application-performance/stability-patterns-applied-in-a-restful-architecture.html?page=2)
  * static methodes in interfaces
  * Asynchronous calls 
  * new Date api 
  * @queryparam
  * @cookieparam
  * @hearderparam
  * @matrixparam
  * Rest services
  * starting up service
 
### New:
* new jenkins setup with jobs as code, make sure git submodules are supported
* split everything up into git submodules
* Simian Monkey
* Chef vs Puppet vs Ansible
* file web service
* Coda hale metrics
*  Add OWASP dependency check to maven http://search.maven.org/#artifactdetails%7Corg.owasp%7Cdependency-check-maven%7C1.4.5%7Cmaven-plugin
* Create job for release with mvn release:prepare release:perform

### Existing:
* Buildstreet:
  * make the builds smaller by only building the correct modules and the modules that depend on them. Maybe with the generate maven build job automatically.
  * Use job inheritence with the maven jenkins plugin
  * build step javadoc
  * build step code coverage
  * investigate Job-dsl for jenkins (https://blog.codecentric.de/en/2015/01/continuous-delivery-microservices-jenkins-job-dsl-plugin/) (https://github.com/jenkinsci/job-dsl-plugin/wiki/Tutorial---Using-the-Jenkins-Job-DSL)
  (https://wiki.jenkins-ci.org/display/JENKINS/Job+Generator+Plugin)
  * smoke & system tests in maven build 

* Servers:

 * [fix reverse proxy problem](http://httpd.apache.org/docs/2.2/mod/mod_proxy.html) [or](https://groups.google.com/forum/#!searchin/jenkinsci-users/proxy/jenkinsci-users/jcllTOoD684/fDQQZ6WpwNwJ) [or](http://jenkins-ci.361315.n4.nabble.com/Apache-reverse-proxy-to-Jenkins-servlet-on-port-8080-td3444546.html)
 * [configure tomcat](http://www.jsf2.com/using-cdi-and-jsf-2.2-faces-flow-in-tomcat/#steps)
 * http 2
 * [setup tomcat env dev/test/prod](https://www.voxxed.com/blog/2015/10/multiple-tomcat-instances/)
 * [put right impl of JEE in tomcat lib](http://arjan-tijms.omnifaces.org/2014/05/implementation-components-used-by.html)

* Java:

 * add jmeter performance tests
 * add jmeter load test
 * take another look at future
 * optionals
 * JDeps -JDKinternals app.jar
 * [refactor converters to lambda's](http://www.petrikainulainen.net/programming/maven/integration-testing-with-maven/)
 * build SSO WebService
 * build SSO WebApp
 * Integrate Cucumber & selenium for frontend testing, maybe with auto spinup and spindown VM's
 * [cucumber + selenium + dbunit/restito for frontend acceptence tests:](http://stackoverflow.com/questions/18164579/how-do-i-specify-a-separate-maven-goal-for-running-cucumber-acceptance-tests) [or](http://stackoverflow.com/questions/22462181/how-do-i-run-a-selenium-test-using-maven-from-the-command-line) [or](https://code.google.com/p/selenium/wiki/GettingStarted)
 * refactor emails to make better use of lambda's
 * refactor generic code to make better use of lambda's
 * service circuit breakers on too many timeouts of webservices. 
 * refactor converters to lambda's
 * build common front end components and styling module
 * Non blocking IO
 * Security:
  * stateless security client side cross site request forgery tokens (CSRF tokens) in cookie and http header check in a filter/interceptor
  *spring security
  *keyclocke
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
 * Vert.x
 * Webservlet: Java EE essentials
 * SOAP
 * JSESSIONID
 * Error handling in clients
 * datagathering client
 * jsf
 * angular
 * jquery
 
 
 ### JNDI:
 in tomcat server.xml
 <Context docBase="user-service"
                    path="/user-service" reloadable="true"
                    source="org.eclipse.jst.jee.server:reporting-service">
                    <Environment name="configurationPath"
                        value="/opt/apps/config/user-service/server.properties"
                        type="java.lang.String" />
                </Context>
