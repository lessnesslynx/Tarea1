Se utilizó double dispatch para el cálculo del daño y también para ser capaz de identificar qué item corresponde a qué unidad, se podría haber usado method overload o bien instanceof, pero como eran reemplazables por double dispatch, se prefirió usar double dispatch (por el simple hecho de que es posible y es lo que se enseña en el curso, ya que era "tentador" usar method overload).

La organización de las clases corresponde a sub-clases de AbstractUnit y AbstractItem, es posible agrupar unidades en "unidades mágicas" e "unidades físicas", o de agrupar "items mágicos" e "items físicos", pero nunca se explicitó que se debían agrupar, dependiendo del diseño futuro, podría ser util agruparlos en una clase "mágica" y "física" que heredan de las clases abstractas, o podría ser útil no hacerlo, no hay suficiente información. [Esto es un supuesto de que la separación de la clase según "tipo" no era necesario.]

Se implementó un sistema de métodos que usan double dispatch para efectuar la efectividad, y el equipado de items (dealTypeDamage, getTypeDamage, equipTypeItem), aprovechándose de la herencia y del method lookup, y se implementaron métodos de combate que se encargan de calcular la distancia entre una unidad y otra y efectuar contraataque cuando es debido (cuando la unidad a contra-atacar sigue viva y está en rango).

No se implementó intercambio de items, pero se tendrá en cuenta para futuro.

Se usó javadoc (o se intentó hacerlo) para cada método usado, es posible que se me haya olvidado alguno de todos modos.

Se intentó hacer tests para cada caso, efectividad, resistencia, atributos de las clases, rango de ataque, no contratacar si la unidad a la que se ataca muere, es también posible que se me hayan olvidado casos, el coverage es más de 90% en líneas, pero no 100%.