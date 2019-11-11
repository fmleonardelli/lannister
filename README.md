## Lannister
Lannister es la aplicación responsable de realizar las notificaciones de eventos que generan cargos para los usuarios de Mercado Libre.

### Pre-Requisitos
Este projecto para su correcto funcionamiento requiere tener instalado el siguiente stack de tecnologías:
- [Java Development Kit 8 Oracle](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
- [Maven](https://maven.apache.org/): construcción del proyecto / manejador de dependencias.
- [MongoDB](https://www.mongodb.com/es): desde versión 3.6 en adelante. Base de datos no relacional que se ejecuta escuchando en el puerto por default 27017.
- [Apache Kafka](https://kafka.apache.org/): la app se comunica con otros microservicios mediante una cola de mensajería el cual cumple el rol de productor de mensajes. El servicio de mensajería tiene que estar configurado para escuchar tráfico por el puerto 9092.

### Configuración
El Ide utilizado para el desarrollo de la app fue IntelliJ, por lo tanto, si se quiere utilizar otro se deberá buscar las alternativas propuestas por el entorno utilizado.

Config Intellij
- Plugin Lombok
- Config Lombok: File -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> Enable annotation processing

#### Notas
Si se utiliza la versión de java, openJDK es probable que haya componentes que no compilen. 

La utilización de lombok no está limitada solo por los features de generación de getter/setter y constructors sino que también se usa para manejar inmutabilidad entre otros.

Además, se utilizó una libreria llamada Vavr para agregar carácteristicas funcionales.
**Ciertos features de esta biblioteca no son reconocidos por el IDE; esto se tiene que tener en cuenta al momento de descargar el proyecto, ya que el mismo compila y funciona correctamente.**

### Ejecución
Lannister es una aplicación desarrollado sobre el framework spring boot. 
Para ejecutarla basta ejecutar el comando **mvn spring-boot:run** en la carpeta root del proyecto. 



