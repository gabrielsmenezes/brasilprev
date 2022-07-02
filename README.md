![GitHub Workflow Status](https://img.shields.io/github/workflow/status/gabrielsmenezes/brasilprev/Java%20CI%20with%20Maven) 
![GitHub branch checks state](https://img.shields.io/github/checks-status/gabrielsmenezes/brasilprev/main) ![GitHub issues](https://img.shields.io/github/issues/gabrielsmenezes/brasilprev) ![GitHub pull requests](https://img.shields.io/github/issues-pr/gabrielsmenezes/brasilprev) ![GitHub deployments](https://img.shields.io/github/deployments/gabrielsmenezs/brasilprev/production) ![GitHub top language](https://img.shields.io/github/languages/top/gabrielsmenezes/brasilprev) ![GitHub](https://img.shields.io/github/license/gabrielsmenezes/brasilprev)


How to run?
- Download project
- run maven install and package
- docker build -t brasilprev .
- docker run -p 8080:8080 brasilprev

Features
- Customer registration
  - a customer can insert his/her name, cpf, address and password
  - curl --location --request POST 'localhost:8080/customers' \
    --data-raw '{
    "name": "John Doe",
    "cpf": "268.261.660-70",
    "address": "Avenue Street n 192, USA",
    "password": "123456789"
    }'
- Customer login
  - a customer already saved, can login in with cpf and password and receive the JWT to future features
  - curl --location --request POST 'localhost:8080/login' \
    --data-raw '{
    "username": "268.261.660-70",
    "password": "123456789"
    }'

Technologies
- Spring Boot
- Bean Validation
- Spring JPA
- Spring Security with JWT
- Docker


- Bean Validation