# Gilded Rose starting position in Java

Esto es un fork de la conocida [kata de Gilded Rose ](https://github.com/emilybache/GildedRose-Refactoring-Kata/)para simplificar su descarga.

**¿Qué esperamos de la prueba?**
Solo queremos mantener una conversación contigo sobre programación, para ello lo más fácil es tener un código en común.

- ¿Cómo solucionarías el problema?
- ¿Qué técnicas aplicarías?
- ¿Qué consideras que se puede mejorar del código?
- ¿Testing?

**¿Qué te recomendamos hacer en la prueba?**
Tanto si vienes a la prueba de live coding como si has optado por realizar la prueba tranquilamente por separado te recomendamos que entregues algo **con lo que te sientas comoda**. 

El ejercicio propuesto es una modificación que podrías tener lista en poco tiempo. Sin embargo, sabemos que el código tiene bastante margen de mejora, ¿nos ayudas?. 

Por favor, no inviertas demasiado tiempo, queremos conversar y aprender como resuelves problemas desde un código común, pero lo que entregues no tiene que estar perfecto, pero si mostrarnos que tipo de codigo haces en tu día a día y que mejoras incluirías.

**Recomendaciones:**
- Haz un readme para contarnos qué te ha parecido, qué decisiones has tomado y qué harías si dedicaras más tiempo o si quisieras mejorar algo.
- Has test
- Ordena el código, hay muchas cosas que mejorar ahí
- Busca enseñarnos como programas en tu día a día
- Nos gustan los test, las buenas practicas, SOLID, KISS, etc
- Disfruta, si dejar de hacerlo para y charla con nosotros de ello.

**¿Qué plazo tengo para entregar una prueba por separado?**
Si vas a entregar una prueba desde casa, te agradeceríamos que nos enseñaras el código al menos un día antes para poder garantizarte un feedback de calidad en la propia entrevista, pero no es condición necesaria. La opción perfecta para entregar es compartirnos un enlace a un repositorio publico de git con el código, así también podemos ver como usas los commit para documentar :).

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
