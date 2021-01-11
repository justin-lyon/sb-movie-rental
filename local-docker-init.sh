#!/bin/bash
# Initial Setup
# 1. Create  Volume by name `movie-rental`
docker volume create movie-rental

# 2. Create a container and image, and run the thing in one command.
docker run --name mysql57 \
-p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=1234 \
-e MYSQL_DATABASE=MovieRental \
-e MYSQL_USER=jpa_local \
-e MYSQL_PASSWORD=1234 \
-v movie-rental:/var/lib/mysql \
-d mysql/mysql-server:5.7

# Done!
# Daily startup:
docker start mysql57