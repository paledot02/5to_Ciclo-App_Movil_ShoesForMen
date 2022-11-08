## Pasos para probar la aplicacion (version de Android Studio: 2021.2.1.14)

#### Creamos un proyecto en Firebase: <img src=./screenshot/5_Android_3.jpg/ align="right" width="250">
- Entramos a Firebase y creamos un nuevo proyecto con cualquier nombre ejemplo "proyecto01"
- Dentro de tu proyecto seleccionas el icono de Android y completamos los datos:
	1. Obtener el nombre del paquete: En Android Studio seleccionas cualquiera de los paquetes 
	   dentro de java/Copy Path/Copy Reference, que debe ser algo asi: com.paledot01.shoesformen 
	2. Sobrenombre lo dejamos en blanco.
	3. Certificado de firma SHA-1: en Android Studio/Gradle(esta en la barra lateral derecha, en 
           parte superior)/Tasks/android/doble click a "signingReport"/copiamos el codigo SHA1 que 
           aparece en la consola y lo pegamos en Firebase y click en "Registrar app". 
    4. NOTA: si no aparece la opcion Tasks: Entras File/Settings/deseleccionas la opcion "Do not 
                 built Gradle task list during Gradle sync"/Apply/OK, luego entras a File/Sync Project with Gradle Files. Luego de sincronizar el proyecto ya deberia aparecer la opcion Task. Estos pasos cambian la opcion para ejecutar la aplicacion en el Android Studio en la parte superior central
                 regresarlo a "app" para correr la aplicacion.
    5. Nos generará un archivo JSON lo descargamos y lo colocamos dentro del proyecto donde indica Firebase: Android Studio cambiamos la vista a "Project"/MyProyecto/app/y aca pegamos o sobreescribimos el archivo JSON proporcionado por Firebase. y le damos siguiente a todo lo demas.
#### Para que empezar a llenar la base de datos:
1. En Firebase, dentro de la consola de nuestro proyecto entramos a Compilacion/Authentication/Comenzar/En Proveedores de Acceso/seleccionamos Correo electronico y contraseña/en la siguiente pagina damos check a "Correo electronico y contraseña"/Guardar. ya podemos agregar usuarios.
2. En Authentication/Users/Agregar usuario/colocamos un correo y contraseña no tienen que ser válidas/Agregar usuario.
3. Crearemos la base de datos: Firebase/Realtime Database/Crear una base de datos/Siguiente/Comenzar en modo de prueba/Habilitar. Al inicio en Realtime Database aparecerá una URL, la copiamos. Luego nos vamos a Android Studio/cambiamos la vista a "Project"/MyAplicacion/app/google-service-json/dentro de "project_info" agregamos el campo: "Firebase_url": "URL", y aca remplazamos URL por la que copiamos.
4. Volvemos a sincronizar el projecto: File/Sync Project with Gradle Files/esperamos que acabe.
5. Ejecutamos la aplicacion en el emulador, nos logeamos en la aplicacion con el usuario creado, y podemos 
  empezar a registrar los calzados.
  
  #### Nota: 
  Para la imagen de los calzados, necesita la url de una imagen subida a internet. Ten presente que tanto el acceso del usuario como la modificacion de los datos de los productos deben verse registrados y almacenados en el Firebase, de lo contrario se cometio algun error en el proceso.




[img_1]: ./screenshot/5_Android_3.jpg

