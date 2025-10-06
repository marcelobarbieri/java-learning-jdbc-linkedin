# java-learning-jdbc-linkedin

```
$ chmod +x dat/load-db.sh
$ dat/load-db.sh
$ psql -U $POSTGRES_USER -d $POSTGRES_DB -h localhost
# select * from wisdom.services;
```

```
mvn archetype:generate -DgroupId=com.frankmoley.lil -DartifactId=learning-jdbc -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false

mvn validate
mvn clean install
```