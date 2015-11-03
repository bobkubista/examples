# examples
This is my examples of code that I am trying out stuff with. It is for reference use.

principals:
- SOLID -> Single responsibility, open/closed, liskov substitution, interface segregation, dependency inversion
- Code quality and test coverage 
- Agility over speed
- Design patterns
- Minimal code duplication

- Test definition http://stackoverflow.com/questions/520064/what-is-unit-test-integration-test-smoke-test-regression-test
- Unit tests on 1 class only, the rest is stubbed/mocked
- Rest IT with jersey on the rest interface
- Front end tests with cucumber and selinium
- Always regression test
- On proxies, only smoke test
- Pre-flight test is does it deploy and do you get rest definition or index page

- If no state, then default methodes in interfaces
- JPA for ORM


Todo:
- DONE buy server
- DONE build VM base (http://www.vmware.com/products/vsphere-hypervisor/ http://www.pcworld.com/article/201408/how_to_build_a_virtualization_server.html)
- setup build street (jenkins, sonar, nexus on 1 VM, all port 80, but different url)
- build automatic VM scripts (maybe jenkins job)
- investigate Job-dsl for jenkins
- setup tomcat env dev/test/prod (https://www.voxxed.com/blog/2015/10/multiple-tomcat-instances/)
- put right impl of JEE in tomcat lib (http://arjan-tijms.omnifaces.org/2014/05/implementation-components-used-by.html)
- build SSO WebService
- build SSO WebApp
- Integrate Cucumber & selenium for frontend testing, maybe with auto spinup and spindown VM's
- build common front end components and styling module


Want Todo:
backend:
- static methodes in interfaces
- Asynchronous calls 
- Non blocking IO
- Security:
	- stateless security client side cross site request forgery tokens (CSRF tokens) in cookie and http header check in a filter/interceptor
	- spring security
	- keyclocke
- apache kafka
- spring data
- Websockets
- Spring boot
- Docker
- Jetty
- Message driven beans
- EJB
- JTA
- JMS
- Batch processing
- Lambda logging
- new Date api 
- try with resources
- Vert.x
- Webservlet: Java EE essentials
- SOAP
- Rest services
	- starting up service
	- @queryparam
	- @cookieparam
	- @hearderparam
	- @matrixparam
	- JSESSIONID
- Error handling in clients
- datagathering client


frontend:
- jsf
- angular
- jquery
- cucumber + selenium + dbunit for frontend acceptence tests: http://stackoverflow.com/questions/18164579/how-do-i-specify-a-separate-maven-goal-for-running-cucumber-acceptance-tests
http://stackoverflow.com/questions/22462181/how-do-i-run-a-selenium-test-using-maven-from-the-command-line
https://code.google.com/p/selenium/wiki/GettingStarted
