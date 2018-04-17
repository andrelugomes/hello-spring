# Connection Pool Monitoring

## wrk benchmark

```bash
git clone git@github.com:wg/wrk.git
cd wrk
make
sudo cp wrk /usr/local/bin
```
$ wrk -t10 -c100 -d30s -s create-notes.lua  http://127.0.0.1:8080/notes


## mysql

```bash
docker run -it --rm mysql:tag --verbose --help
```

max-connections      151

```bazaar

docker run --name mysql_notes_app -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=notes_app -p 3306:3306 -d mysql

mysql -h 0.0.0.0 -u root -p    

mysql> show variables like "max_connections";
+-----------------+-------+
| Variable_name   | Value |
+-----------------+-------+
| max_connections | 151   |
+-----------------+-------+
1 row in set (0,00 sec)


docker run --name mysql_notes_app_200 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=notes_app -p 3306:3306 -d mysql --max-connections=200

mysql -h 0.0.0.0 -u root -p           

mysql> show variables like "max_connections";
+-----------------+-------+
| Variable_name   | Value |
+-----------------+-------+
| max_connections | 200   |
+-----------------+-------+
1 row in set (0,00 sec)
```

## actuator

```text
http://localhost:8080/actuator/metrics

http://localhost:8080/actuator/metrics/hikaricp.connections.active
```

## jconsole

# Connection Pool

- https://tomcat.apache.org/tomcat-8.0-doc/jdbc-pool.html#Common_Attributes

- https://github.com/brettwooldridge/HikariCP#configuration-knobs-baby

- https://github.com/brettwooldridge/HikariCP/wiki/About-Pool-Sizing

