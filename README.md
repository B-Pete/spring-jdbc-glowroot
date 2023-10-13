# spring-jdbc-glowroot
Cloned from https://github.com/mkyong/spring-boot/spring-jdbc

Glowroot:
src/main/glowroot13
src/main/glowroot14

Run application
# java -jar -javaagent:src/main/glowroot14/glowroot.jar target/spring-jdbc-glowroot-1.0.jar

Browse to
# http://localhost:8080/

exptect an error:
There was an unexpected error (type=Internal Server Error, status=500).
Class com.sun.proxy.$Proxy83 does not implement the requested interface org.glowroot.agent.plugin.jdbc.DataSourceAspect$Connection
