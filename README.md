# Getting Started


Spring Boot App & TypeScript Code generation with OpenApi and different Generation-Methods
https://openapi-generator.tech/
https://openapi-generator.tech/docs/usage/
https://openapi-generator.tech/docs/generators/typescript-angular/
https://openapi-generator.tech/docs/configuration/
https://github.com/OpenAPITools/openapi-generator/tree/master/modules/openapi-generator-maven-plugin
### 1 TS Codegen with Maven Plugin
https://openapi-generator.tech/docs/plugins/

To generate the TypeScript run :

    mvn clean compile

### 2 TS Codegen with NPM openapi-generator-cli
https://www.npmjs.com/package/@openapitools/openapi-generator-cli

Install Node https://nodejs.org/en/download/ and install packages:

    cd /src/main/openapi/generator/; npm install

To generate the TypeScript run :

    npx openapi-generator-cli generate -i ../../../openapi/api-docs.json -g typescript-angular -o ../angular-app/src/generated/ --additional-properties=enumPropertyNaming=UPPERCASE

or direct from url:

    `npx openapi-generator-cli generate -i http://localhost:8080/v3/api-docs -g typescript-angular -o ../angular-ui/src/generated`

### 3 Angular Integration

It is important to set the basePath to "" in the app.modules.ts. Other-ways you will have some CORS Errors when you run the app under port 4200. 
In the newer Angular Versions  (17+) is this not possible anymore, so you have to set Server Url via OpenAPIDefinition Annotation in Spring Boot. 
The other significant thing is to set the media type to json in the spring controller or via the springdoc.default-produces-media-type=application/json in the application.properties.
If the mediatype is not set the content is * in the api-docs file and angular return a blob back. 

