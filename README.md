# Getting Started


Spring Boot App & TypeScript Code generation with OpenApi and different Generation-Methods
https://openapi-generator.tech/
https://openapi-generator.tech/docs/usage/

### 1 TS Codegen with Maven Plugin
https://openapi-generator.tech/docs/plugins/

To generate the TypeScript run :

    mvn clean compile

### 2 TS Codegen with NPM openapi-generator-cli
https://www.npmjs.com/package/@openapitools/openapi-generator-cli

Install Node https://nodejs.org/en/download/ and install packages:

    cd /src/main/openapi/generator/; npm install

To generate the TypeScript run :

    npx openapi-generator-cli generate -i ../../../swagger/api-docs.json -g typescript-angular -o ../typescript/

or direct from url:

    `npx openapi-generator-cli generate -i http://localhost:8080/v3/api-docs -g typescript-angular -o ../typescript/`
    
