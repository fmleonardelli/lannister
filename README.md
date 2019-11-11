## Lannister
Lannister es la aplicación responsable de realizar las notificaciones de eventos que generan cargos para los usuarios de meli.

### Pre-Requisitos
Este projecto para su correcto funcionamiento requiere tener instalado el siguiente stack de tecnologías:
- Java Development Kit 8 de Oracle.
- Maven: Manejador de dependencias / Construcción del proyecto.
- MongoDB: escuchando en el puerto por default 27017.
- Apache Kafka: la app actúa como productor de mensajes, por lo cual envía información. El servicio de mensajería tiene que estar configurado para escuchar tráfico por el puerto 9092.

### Configuración
El Ide utilizado para el desarrollo de la app fue IntelliJ, por lo tanto si se quiere utilizar otro se deberá buscar otras alternativas propuestas por el entorno utilizado.

Config Intellij
- Plugin Lombok
- Config Lombok: File -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> Enable annotation processing

##### Notas
Si se utiliza la versión de java openJDK es probable que algunos componentes no compilen. 

La utilización de lombok no está limitada solo por los features de generación de getter/setter y constructors sino que también se usa para manejar inmutabilidad.

Además, se utilizó una libreria llamada Vavr para agregar carácteristicas funcionales. 
**Ciertos features de esta biblioteca no son reconocidos por el IDE; esto se tiene que tener en cuenta al momento de descargar el proyecto, ya que el mismo compila y funciona correctamente.**

### Ejecución



