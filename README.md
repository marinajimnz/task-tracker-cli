# Task Tracker CLI (https://roadmap.sh/backend/projects)

Un proyecto de interfaz de línea de comandos (CLI) simple para gestionar y rastrear tus tareas diarias. Esta aplicación te permite mantener un registro de lo que necesitas hacer, lo que ya has completado y en lo que estás trabajando actualmente.

## Descripción

Task Tracker es una herramienta de línea de comandos que te ayuda a organizar tus tareas de manera eficiente. Todas las tareas se almacenan en un archivo JSON local, lo que hace que sea fácil de usar y no requiere configuración adicional.

## Características

- ✅ **Agregar tareas**: Crea nuevas tareas con descripciones personalizadas
- ✏️ **Actualizar tareas**: Modifica la descripción de tareas existentes
- 🗑️ **Eliminar tareas**: Borra tareas que ya no necesitas
- 📝 **Marcar progreso**: Cambia el estado de las tareas (pendiente, en progreso, completada)
- 📋 **Listar tareas**: Visualiza todas las tareas o filtra por estado
- 💾 **Almacenamiento local**: Las tareas se guardan en un archivo JSON

## Requisitos

- Lenguaje de programación compatible (Python, JavaScript, etc.)
- Sin dependencias externas - solo módulos nativos del sistema de archivos

## Instalación

1. Clona o descarga el proyecto
2. Navega al directorio del proyecto
3. Asegúrate de que el archivo ejecutable tenga permisos de ejecución

## Uso

### Comandos Disponibles

#### Agregar una nueva tarea
```bash
task-cli add "Comprar víveres"
# Salida: Task added successfully (ID: 1)
```

#### Actualizar una tarea existente
```bash
task-cli update 1 "Comprar víveres y cocinar la cena"
```

#### Eliminar una tarea
```bash
task-cli delete 1
```

#### Marcar una tarea como en progreso
```bash
task-cli mark-in-progress 1
```

#### Marcar una tarea como completada
```bash
task-cli mark-done 1
```

#### Listar todas las tareas
```bash
task-cli list
```

#### Listar tareas por estado
```bash
# Listar tareas completadas
task-cli list done

# Listar tareas pendientes
task-cli list todo

# Listar tareas en progreso
task-cli list in-progress
```

## Estructura de las Tareas

Cada tarea contiene las siguientes propiedades:

- **id**: Identificador único de la tarea
- **description**: Descripción breve de la tarea
- **status**: Estado actual (`todo`, `in-progress`, `done`)
- **createdAt**: Fecha y hora de creación
- **updatedAt**: Fecha y hora de la última actualización

## Almacenamiento

Las tareas se almacenan en un archivo `tasks.json` en el directorio actual. Este archivo se crea automáticamente si no existe.

Ejemplo de estructura del archivo JSON:
```json
[
  {
    "id": 1,
    "description": "Comprar víveres",
    "status": "todo",
    "createdAt": "2024-01-15T10:30:00Z",
    "updatedAt": "2024-01-15T10:30:00Z"
  }
]
```

## Estados de las Tareas

- **`todo`**: Tarea pendiente por realizar
- **`in-progress`**: Tarea en progreso
- **`done`**: Tarea completada

## Implementación

### Características Técnicas

- Utiliza argumentos posicionales en línea de comandos
- Interactúa con el sistema de archivos usando módulos nativos
- Manejo de errores y casos límite
- Sin dependencias externas

### Flujo de Desarrollo Recomendado

1. **Configuración del entorno**: Elige tu lenguaje de programación preferido
2. **Inicialización**: Crea la estructura básica del CLI
3. **Implementación incremental**: Desarrolla una funcionalidad a la vez
4. **Pruebas**: Verifica cada característica antes de continuar
5. **Finalización**: Limpia el código y documenta

## Contribución

Este proyecto está diseñado como ejercicio de práctica para mejorar habilidades de programación, incluyendo:

- Trabajo con el sistema de archivos
- Manejo de entrada de usuario
- Construcción de aplicaciones CLI
- Manipulación de datos JSON

## Notas

- El archivo JSON se crea automáticamente en el directorio actual
- Los IDs de las tareas se asignan automáticamente de forma incremental
- Todas las fechas se almacenan en formato ISO

---

Empieza a organizar tus tareas de manera eficiente con Task Tracker CLI 🚀
