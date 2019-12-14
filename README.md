Sobre de la implementación de double dispatch para la tarea anterior, en esta se utilizó Factory Pattern y Observer Pattern, Factory Pattern permite crear unidades preestablecidas para cada jugador, observer Pattern permite que el controller pueda conocer todo los cambios que se hacen para cada Tactician y sus unidades, de hecho, es necesario que los haga para poder llevar a cabo las reglas del juego.

Se completaron errores de la entrega pasada, métodos faltantes y documentación faltante, además, se implementaron métodos necesarios para que el controller pudiera llevar a cabo las reglas del juego.

Se aleatorizaron eventos como los turnos mediante aleatorización pura, se podría haber usado semilla, pero no conocía alguna función de hash para ello, de todos modos en los tests se probaron los invariantes de la aleatorización.

Se implementó el controller en su propio paquete, con interfaces y clases asociadas, y se implementó Tactician fuera de cualquier paquete, Tactician sin ninguna interfaz o clase asociada.

Se implementó Factory Pattern en su propio packete, con clases que actúan como Factory de cada unidad a crear, y además un TacticianFactory.