This is web application example with microservice architecture.
Using Angular as front end and Spring boot (Cloud Api) as backend.

### There are 4 projects involved on this example
* azure-login-angular [Front end]
* azure-login-backend-gateway [API Gateway]
* azure-login-backend-demo [Backend - no need authentication]
* azure-login-backend-vigilante [Bckend - need authentication]

### Run the front end application
Change the \<\<Client ID>> and \<\<Tenant ID>> to your azure subscription id in `/azure-login-angular/src/app/environments/environment.ts`

### Run the back end application
Change the \<\<Tenant ID>> in the `/azure-login-backend-gateway/src/main/resources/application.yml` to your subscription id
Change the \<\<Tenant ID>> in the `/azure-login-backend-vigilante/src/main/resources/application.properties` to your subscription id

* sequence dopesn't matter, run all three backend application include gateway before start testing from front end

