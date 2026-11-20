
# Proyecto MiniMax - Tic Tac Toe

## Descripción

Este proyecto consiste en el desarrollo de un juego de Tic Tac Toe (Tres en Raya) utilizando Java y JavaFX, implementando el algoritmo de Inteligencia Artificial **Minimax con poda Alfa-Beta**.

El objetivo es desarrollar una inteligencia artificial capaz de analizar los posibles movimientos del tablero y seleccionar la mejor jugada para competir contra el jugador.

El programa cuenta con una interfaz gráfica que permite jugar contra la IA y visualizar parte del proceso de análisis y toma de decisiones del algoritmo.

## Tecnologías utilizadas

- **Java:** Lenguaje de programación utilizado para desarrollar la lógica del juego y el algoritmo.
- **JavaFX:** Biblioteca utilizada para crear la interfaz gráfica.
- **Minimax:** Algoritmo de búsqueda utilizado para determinar los mejores movimientos.
- **Poda Alfa-Beta:** Técnica de optimización que permite reducir la cantidad de movimientos que necesita analizar el algoritmo.
- **Visual Studio Code:** Entorno de desarrollo utilizado para programar y ejecutar el proyecto.

## Características principales

- Juego de Tic Tac Toe para un jugador contra la inteligencia artificial.
- Interfaz gráfica interactiva desarrollada con JavaFX.
- Implementación del algoritmo Minimax.
- Optimización de la búsqueda mediante poda Alfa-Beta.
- Visualización de los posibles movimientos y sus valores.
- Panel de análisis que muestra el árbol de decisiones de la IA.
- Estadísticas de nodos visitados y movimientos omitidos.
- Pantalla de resultados que muestra el ganador y las estadísticas de la partida.
- Opción para reiniciar la partida o regresar al menú principal.

## ¿Cómo funciona el algoritmo?

El algoritmo Minimax analiza los posibles movimientos que se pueden realizar en el tablero para seleccionar la jugada más conveniente.

En este proyecto:

- **Jugador (X):** Es controlado por el usuario y busca ganar la partida.
- **Inteligencia Artificial (O):** Utiliza Minimax para buscar el mejor movimiento posible.
- **Valor 1:** Representa un resultado favorable para la IA.
- **Valor 0:** Representa un empate.
- **Valor -1:** Representa un resultado desfavorable para la IA.

La poda Alfa-Beta optimiza este proceso al evitar analizar algunas posibilidades que no pueden cambiar la decisión final del algoritmo.

## Estructura del proyecto

```text
ProyectoMiniMax/
│
├── .vscode/
│   ├── settings.json
│   └── launch.json
│
├── src/
│   └── minimax/
│       ├── Main.java
│       ├── Tablero.java
│       ├── Nodo.java
│       ├── Minimax.java
│       ├── ResultadoBusqueda.java
│       ├── InterfazJuego.java
│       ├── PanelArbol.java
│       ├── PantallaResultados.java
│  
│       
├── .gitignore
└── README.md
```

## Requisitos para ejecutar el proyecto

Para ejecutar este proyecto es necesario contar con:

- JDK 25 o una versión compatible con JavaFX 26.
- JavaFX SDK 26.0.1.
- Visual Studio Code.
- Extensiones de Java para Visual Studio Code.

## Instrucciones de ejecución

1. Descargar o clonar este repositorio.
2. Instalar el JDK y JavaFX SDK.
3. Abrir la carpeta del proyecto en Visual Studio Code.
4. Configurar la ruta de la carpeta `lib` del SDK de JavaFX en el proyecto.
5. Ejecutar el archivo `Main.java` o utilizar la configuración de ejecución de VS Code.

**Nota:** La ruta del SDK de JavaFX debe ajustarse de acuerdo con la ubicación donde se encuentre instalado en cada computadora.

## Objetivo del proyecto

Aplicar los conocimientos de Inteligencia Artificial mediante la implementación del algoritmo Minimax y la poda Alfa-Beta, comprendiendo cómo una inteligencia artificial puede analizar diferentes escenarios y tomar decisiones estratégicas dentro de un juego.

---

**Proyecto académico de Inteligencia Artificial**

**Algoritmo:** Minimax con poda Alfa-Beta  
**Lenguaje:** Java  
**Interfaz gráfica:** JavaFX

**Autor:** Joshua Emmanuel Esparza Chavez
