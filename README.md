# Gilded Rose starting position in Java

Esto es un fork de la conocida [kata de Gilded Rose ](https://github.com/emilybache/GildedRose-Refactoring-Kata/)para simplificar su descarga.

## My solution

La solución planteada es casi la solución final que plantearía.

En este caso, se ha mejorado de forma notable la forma de clasificar los Items (por sus nombres y 
teniendo solo en cuenta caracteres de a-z y A-Z y una comparación case-insensitive) y se ha tratado de 
encapsular su lógica por separado dentro de la clase GildedRose. Idealmente y como se describe a 
continuación, la idea sería encapsular la lógica y atributos necesarios de cada tipo de item en una
clase diferente que herede y sobreescriba la clase Item.

Como aspectos notables destacar que
- Se ha tenido en cuenta el manejo de excepciones con una customException llamada ItemTypeException
- Se ha desarrollado un pipeline en Github para tener un proceso de CI/CD
- Gestión de versiones con el changelog y generación de TAGs en Github
- Desarrollo de tests

Dado que no es posible modificar la clase Item, la idea sería crear clases que extiendan de Item, 
donde cada una de esa clases corresponda con cada uno de los tipos especiales de items que 
se han planteado (Aged Brie, Sulfuras, Backstage, Conjured y Normal).

La clase Normal en el caso ideal sería modificar e implementar su lógica de updateQuality en la clase
Item directamente, y que el resto de subclases sobreescriban el método. Además, definiría
una interfaz que la clase Item implemente donde esté definido al menos ese método updateQuality.

Se ha definido la enumeración ItemType para determinar, a partir del nombre, el tipo del Item
y llamar a su correspondiente método (y de ésta forma tratar de acercarnos lo máximo posible
a la definición de clases mencionadas, principios de SRP y encapsulación, etc).

## Run TESTs

La clase GildedRoseTest contiene los tests unitarios del sistema (clase GildedRose)

```
./gradlew test
```

Los resultados pueden visualizarse en la ruta /build/reports/tests/test/index.html



## Run the TextTest Fixture from Command-Line

```
./gradlew -q text
```

### Specify Number of Days

For e.g. 10 days:

```
./gradlew -q text --args 10
```

You should make sure the gradle commands shown above work when you execute them in a terminal before trying to use TextTest (see below).

# Especificaciones de la Rosa Dorada (Gilded Rose)

Bienvenido al equipo de **Gilded Rose**.
Como quizá sabes, somos una pequeña posada ubicada estratégicamente en una prestigiosa ciudad, atendida por la amable **Allison**.
También compramos y vendemos mercadería de alta calidad.
Por desgracia, nuestra mercadería va bajando de calidad a medida que se aproxima la fecha de venta.

Tenemos un sistema instalado que actualiza automáticamente el `inventario`.
Este sistema fue desarrollado por un muchacho con poco sentido común llamado Leeroy, que ahora se dedica a nuevas aventuras.
Tu tarea es agregar una nueva característica al sistema para que podamos comenzar a vender una nueva categoría de items.

## Descripción preliminar

Pero primero, vamos a introducir el sistema:

* Todos los artículos (`Item`) tienen una propiedad `sellIn` que denota el número de días que tenemos para venderlo
* Todos los artículos tienen una propiedad `quality` que denota cúan valioso es el artículo
* Al final de cada día, nuestro sistema decrementa ambos valores para cada artículo mediante el método `updateQuality`

Bastante simple, ¿no? Bueno, ahora es donde se pone interesante:

* Una vez que ha pasado la fecha recomendada de venta, la `calidad` se degrada al doble de velocidad
* La `calidad` de un artículo nunca es negativa
* El "Queso Brie envejecido" (`Aged brie`) incrementa su `calidad` a medida que se pone viejo
    * Su `calidad` aumenta en `1` unidad cada día
    * luego de la `fecha de venta` su `calidad` aumenta `2` unidades por día
* La `calidad` de un artículo nunca es mayor a `50`
* El artículo "Sulfuras" (`Sulfuras`), siendo un artículo legendario, no modifica su `fecha de venta` ni se degrada en `calidad`
* Una "Entrada al Backstage", como el queso brie, incrementa su `calidad` a medida que la `fecha de venta` se aproxima
    * si faltan 10 días o menos para el concierto, la `calidad` se incrementa en `2` unidades
    * si faltan 5 días o menos, la `calidad` se incrementa en `3` unidades
    * luego de la `fecha de venta` la `calidad` cae a `0`

## El requerimiento

Hace poco contratamos a un proveedor de artículos *conjurados mágicamente*.
Esto requiere una actualización del sistema:

* Los artículos `conjurados` degradan su `calidad` al doble de velocidad que los normales

Siéntete libre de realizar cualquier cambio al mensaje `updateQuality` y agregar el código que sea necesario, mientras que todo siga funcionando correctamente. Sin embargo, **no alteres el objeto `Item` ni sus propiedades** ya que pertenecen al goblin que está en ese rincón, que en un ataque de ira te va a liquidar de un golpe porque no cree en la cultura de código compartido.

## Notas finales

Para aclarar: un artículo nunca puede tener una `calidad` superior a `50`, sin embargo las Sulfuras siendo un artículo legendario posee una calidad inmutable de `80`.
