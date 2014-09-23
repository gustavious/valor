Instrucciones para la instalacion del proyecto:
1.Descomprima el archivo en el cual se encuentra el proyecto
2.Importe el proyecto que descomprimio a eclipse
3.En el explorador de paquetes abra el paquete co.uniandes.valorAndes.fachada y seleccione la clase ValorAndes.java
4.Busque la constante de nombre RUTA y cambiela por la direccion de la carpeta default dentro de jBoss, ya que aqui es donde se hara el deploy del archivo .war
5.En el explorador de paquetes busque la carpeta llamada build y abrala, busque dentro el archivo build.xml
6.En este archivo bisque la propiedad jboss.home y cambie su ruta por la direccion donde tiene instalado jboss
7.Guarde todos los cambios hechos
8.Utilize el archivo ant para generar los archivos necesarios dentro de jBoss, para esto corra el progrma con ant builder para la automatizacion de tarear
9.Ahora cree un servidor en jBoss para poder mostrar la aplicacion dentro del browser
10.Ingrese en su browser a a ruta: http://localhost:8080/ValorAndes
11.Ya esta listo para disfrutar de todas las funcionalidades de valorAndes

Ejemplos requerimientos funcionales
1.Para ordenar una operacion bursatil:
1.1.Seleccione el link que dice ordenar operacion
1.2.LLene los campos, indique un id que le asignara a la operacion(1), un valor comercial cualquiera, un id del usuario que solicito esta operacion(1)e indique si es una compra o venta
1.3.Oprima el boton de enviar y debe de aprecer una ventana indicando que la operacion fue exitosa

2.Para cancelar una operacion bursatil:
2.1.Seleccione el link que dice cancelaroperacion
2.2.LLene los campos, indique una un ID de una operacion ya ordenada, en este caso 1 y el id del comisionista, en este caso 1 tambien.
2.3.Oprima el boton de enviar y debe de aprecer una ventana indicando que la operacion fue exitosa y se borro la operacion

3.Para registrar una operacion bursatil:
3.1.Seleccione el link que dice registrar operacion
3.2.LLene los campos, indicando el ID de la operacion ha registrar, el ID de su comisionista, el ID del segundo comisionista y el monto que se negocio.
3.3.Oprima el boton de enviar y debe de aprecer una ventana indicando que la operacion fue exitosa

