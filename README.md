# Snake Game Android

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