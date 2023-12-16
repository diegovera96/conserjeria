# DEPENDENCIES
### MAKE SURE YOU HAVE INSTALLED
- [DOCKER ^24.0.7](https://docs.docker.com/engine/install/)

- [DOCKER-COMPOSE ^1.29.2 ](https://docs.docker.com/compose/install/)

- [MARIADB ^10.6.12](https://mariadb.org/download/)

# CLONE REPOSITORY
```bash
git@github.com:diegovera96/conserjeria.git
```

# DOCKER IMAGE AND COMPOSE
```bash
docker-compose up -d
```
# DOCKER MARIADB
```bash
docker-compose -f docker-compose-mariadb.yml up -d
```

> NOTE:
>
> MARIADB IS SET PORT: 3308:3306
>
> API IS SET PORT: 7071:7070
>
> IF YOU WANT MODIFY PORTS, YOU CAN DO IT IN docker-compose-mariadb.yml

> NOTE:
> 
> GRPC IS SET PORT: 50123:50123
> 
> IF YOU WANT MODIFY PORTS, YOU CAN DO IT IN Main.java

# IF REQUEIRED
```bash
docker-compose up --build
```
# OPEN DOCKER HUB

1. Expland conserjeria
2. Press port where app is executed
![Alt text](https://github.com/diegovera96/conserjeria/blob/main/conserjeria/assets/DOCKERHUB.png?raw=true "Title")
3. Now you can use differents URL
![Alt text](https://github.com/diegovera96/conserjeria/blob/main/conserjeria/assets/WEBPAGE.png?raw=true "Title")

### Endpoints
   4. http://localhost:7071/ -> API
   5. http://localhost:7071/personas/ -> GET ALL PERSONAS
   6. http://localhost:7071/persona/rut/{rut} -> GET PERSONA BY RUT
   7. http://localhost:7071/api/grpc/persona/rut/19100636-4 -> GET PERSONA BY RUT USING GRPC
