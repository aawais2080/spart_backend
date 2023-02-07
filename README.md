# SPART Backend

## Prerequisites
- Docker
- Docker Compose v2+
- JVM 17


# Running the application
This project uses multiple profiles to run the application. The profiles are combined into groups:
- `development` * Used for local development
- `production` * Used for production
- `container` * Used for running the application in a container (Docker)

Each profile group requires the database to be running.

The `development` profile group is the default, to use a different profile simply run the application using one of the different profile groups. \
For example to run the application in production mode, change the value in your IDE build configuration or run the application with the `--spring.profiles.active=production` or `-Dspring-boot.run.profiles` flag.


## Run Development
Using your terminal navigate to the directory where the `docker-compose.yml` is located and run the following command:
````docker-compose up -d postgres-db```` \
This will only start the database which can be used for local development. \
The database can be reached at port: `3051`

Run the application using your preferred method. The application will by default start with the `development` profile group containing the `main` and `dev` profiles.

- Stop container(s) but keep volume data: ```docker-compose down```
- Stop container(s) and delete volume data: ```docker-compose down -v```


## Run Using Docker
*Running the application using docker does not support live-reload*

Using your terminal navigate to the directory where the `docker-compose.yml` is located and run the following commands:

````docker-compose build````
*NOTE: Re-run this when making changes to the application which need to be redeployed to the docker container*

````docker-compose up -d````
This will start the application  container and the database container.
- The database can be reached at port: `3051`
- The application can be reached at port: `3001`

The application will by default start with the `development` profile group containing the `main` and `dev` profiles. The docker profile uses the sames database
credentials as the `dev` profile. To edit these credentials, edit the `docker.env` file.

- Stop container(s) but keep volume data: ```docker-compose down```
- Stop container(s) and delete volume data: ```docker-compose down -v```

*NOTE: When building the application with docker new images will be built, keep an eye on your host system storage and
consider cleaning up old images and containers.*


## Run Production 
COMING SOON


## CI/CD 

The backend pipeline is created in `backend-ci.yml`. This pipeline has two jobs:
- build-test: compile and test
- build-and-push-image: creating an image and push to GitHub container registry (package)


## API Documentation
The API documentation is created using Swagger.
The documentation can be found at `environment-url:` + `/swagger-ui/index.html`

## Database Migration
The database migration is done with Flyway. The migration files are located in `src/main/resources/db/migration`.
- The migration files are named with the following pattern: `V<version>__<description>.sql`. The version is a number and the description is a short description of the migration.
- The migration files are executed in the order of the version number.