FROM tomcat:10-jdk17

# Borra las aplicaciones de ejemplo para un contenedor más limpio
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia el .war al directorio de despliegue de Tomcat
COPY target/RetroTrackSoapJava.war /usr/local/tomcat/webapps/ROOT.war

# Copia el keystore si usas HTTPS
COPY certs/keystore.jks /usr/local/tomcat/conf/keystore.jks

# Copia base de datos con datos mínimos de prueba
COPY retrotrack.db /usr/local/tomcat/retrotrack.db

# Copia el server.xml si necesitas configurar HTTPS en Tomcat
COPY server.xml /usr/local/tomcat/conf/server.xml

EXPOSE 8095

CMD ["catalina.sh", "run"]
