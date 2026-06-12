# Mood Cube Factory — Local Setup Guide

This project runs fully locally using Docker.

It includes:
- PostgreSQL database
- pgAdmin (database UI)
- Azurite (Azure Storage emulator)

---

# Requirements

Install:
- Docker Desktop
- Git

Optional:
- IntelliJ IDEA (recommended for Java services)

---

# Start the project

From the project root:

```bash
docker compose up -d
```

This starts:
* Postgres database
* pgAdmin UI
* Azurite storage emulator

# Service overview

PostgreSQL
* Host: localhost
* Port: 5432
* Database: moodcube
* Username: moodcube
* Password: moodcube

pgAdmin (Database UI)
Open in browser:
```bash
http://localhost:5050
```
Login:
* Email: admin@example.com
* Password: admin

Connect to database inside pgAdmin:
adding a server:
* Host: postgres
* Port: 5432
* Database: moodcube
* Username: moodcube
* Password: moodcube

Azurite (Azure Storage Emulator)
Used for:
* Queue triggers
* Blob triggers
Endpoints:
* Blob: http://localhost:10000
* Queue: http://localhost:10001
* Table: http://localhost:10002

# Reset everything
```bash
docker compose down -v
docker compose up -d
```

# Verify all is running
```bash
docker ps
```
You should see:
* moodcube-postgres
* moodcube-pgadmin
* moodcube-azurite