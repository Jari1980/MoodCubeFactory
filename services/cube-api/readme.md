# Cube API Service

## Overview
The Cube API Service owns all cube-related operations in Mood Cube Factory.

Responsibilities:

* Read cube data from PostgreSQL
* Update cube state
* Expose cube data through HTTP endpoints
* Provide cube data to the frontend

This service is the owner of the cubes table.

## Local Run Configuration
Use `local.settings.example.json` as a template.
Copy it to `local.settings.json` before running the project.

To run the Cube API locally on a fixed port:

### IntelliJ
Run → Edit Configurations → Azure Functions
Set:
--port 7071

## Endpoints
### Get All Cubes
Returns all cubes ordered by ID.

GET http://localhost:7071/api/cubes

Example response:
```json
[
{
"id": 1,
"color": "green",
"mood": "balanced",
"energy": 75,
"updatedAt": "2026-06-16T12:00:00"
}
]
```
### Get Cube By Id
Returns a single cube.

GET http://localhost:7071/api/cubes/{id}

Example:

GET http://localhost:7071/api/cubes/42
### Update Cube
Updates a cube's state.

PUT http://localhost:7071/api/cubes/{id}

Example request:
```json
{
"color": "red",
"mood": "angry",
"energy": 95
}
```

PUT http://localhost:7071/api/cubes/42
## Database
Owned table:

cubes

Columns:

* id
* color
* mood
* energy
* updated_at

Current seed data contains 100 cubes for frontend visualization.
## Repository Layer
Interface:

CubeRepository

Methods:

* getAll()
* getById(int id)
* update(Cube cube)

Implementation:

PostgresCubeRepository
## Local Development
Start infrastructure:

docker compose up -d

Run Azure Functions:

mvn azure-functions:run

Test endpoints using Postman or curl.
## Service Ownership
Cube API is the only service that owns and manages the cubes table.

Other services should communicate through APIs, queues, or events rather than directly modifying cube records.