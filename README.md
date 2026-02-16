# Snake Game Android

- BBDD IMPLEMENTADA POR COMPLETO

- GameCache:
    - Línea 41-54, fun saveHighScore:
        - Filtra de forma desdenciente (por score) la lista de HighScores
        - Ahora también inserta la información de la última partida hecha en la base de datos

- GameActivity:
    - Línea 47, fun Content:
        - Se quita el filtrado y se realiza directamente en saveHighScore
    - Línea 46, fun Content:
        - Se lanza un LaunchedEffect para comenzar el contador

- GameRepository:
    - Línea 16-19, fun insertNewScore:
        - Deja pasar tan solo las scores que tienen puntuación

- EndScreen:
    - Línea 43:
        - Comienza el timer de nuevo

- presentation/component/Button
    - Línea 64 - 130
        - Añadido nuevo tipo de botón para el menú principal, con animación de pulsado.

- presentation/screen/MenuScreen
    - Linea 23 - 62
        - Implementados los nuevos botones en la pantalla principal

- presentation/screen/AboutScreen
  - Línea 57 - 61
    - Implementado nuevo botón PixelButton

- presentation/screen/SettingScreen
  - Línea 83 - 95
    - Implementado el nuevo PixelButton

- presentation/screen/EndScreen
  - Línea 47 - 53
    - Implementado el nuevo PixelButton

- presentation/screen/GameScreen
  - Línea 34 - 93
    - Añadida cuenta atrás de 5 segundos para empezar el juego

- data/game/GameEngine
  - Línea 56
    - Delay de 5 segundos para empezar la partida

- presentation/component/Button
  - Línea 124 - 176
    - Añadido componente de botón para la cruceta direcciona PixelIconButton

- presentation/component/Controller
  - Línea 18 - 71
    - Implementados PixelIconButton para la cruceta direccional

- data/game/GameEngine
  - Todo el archivo
    - Implementado delay de 5 segundos tras reiniciar partida
  
- presentation/screen/HighScoreScreen
  - Todo el archivo
    - Implementado nuevo composable para mostrar las puntuaciones

- presentation/viewmodel/HighScoreViewModel
  - Todo el archivo
    - Implementado viewmodel para mostrar scores de la db

- presentation/screen/SettingsScreen
  - Toda la columna
    - Corregido fallo que deja poner tu nombre en blanco
    - Implementada funcionalidad de esconder el teclado tras pulsar fuera de este

- GameActivity:
  - Todo el archivo
    - Nueva variable 'typeFood' que declara el tipo de comida y por ende el powerUp

- GameScreen:
  - Línea 72 - 85:
    - Nuevo parámetro de entrada 'typeFood' que declara si está o no borracha la Snake
    - Cuando el valor de 'typeFood' sea 1 la Snake estará borracha

- Nuevo Enum TypeFood para los estados de la snake

- GameEngine:
  - Línea 79:
    - Si el 'typeFood' es VELOZ se modifica el delay para que se mueva más rápido

- Board:
  - Añadida la animación de volocidad
  - Añadidos fondos según el power up
  - Añadido texto explicativo del power up
  - Añadidos los borrachos, LaunchEffect que aleatoriamente cambia la Snake por borrachos