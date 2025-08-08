# Task Tracker CLI

A simple command-line interface (CLI) project to manage and track your daily tasks. This application allows you to keep track of what you need to do, what you've already completed, and what you're currently working on.

## Description

Task Tracker is a command-line tool that helps you organize your tasks efficiently. All tasks are stored in a local JSON file, making it easy to use and requiring no additional setup.

## Features

- ✅ **Add tasks**: Create new tasks with custom descriptions
- ✏️ **Update tasks**: Modify the description of existing tasks
- 🗑️ **Delete tasks**: Remove tasks you no longer need
- 📝 **Mark progress**: Change task status (pending, in progress, completed)
- 📋 **List tasks**: View all tasks or filter by status
- 💾 **Local storage**: Tasks are saved in a JSON file

## Requirements

- Programming language: Java
- No external dependencies - only native file system modules

## Installation

1. Clone or download the project
2. Navigate to the project directory
3. Ensure the executable file has execution permissions

## Usage

### Available Commands

#### Add a new task
```bash
TaskCLI add "Buy groceries"
# Output: Task added successfully (ID: 1)
```

#### Update an existing task
```bash
TaskCLI update 1 "Buy groceries and cook dinner"
```

#### Delete a task
```bash
TaskCLI delete 1
```

#### Mark a task as in progress
```bash
TaskCLI mark-in-progress 1
```

#### Mark a task as completed
```bash
TaskCLI mark-done 1
```

#### List all tasks
```bash
TaskCLI list-all
```

#### List tasks by status
```bash
# List completed tasks
TaskCLI list-done

# List pending tasks
TaskCLI list-to-do

# List tasks in progress
TaskCLI list-in-progress
```

## Task Structure

Each task contains the following properties:

- **id**: Unique task identifier
- **description**: Brief task description
- **status**: Current status (`todo`, `in-progress`, `done`)
- **createdAt**: Creation date and time
- **updatedAt**: Last update date and time

## Storage

Tasks are stored in a `tasks.json` file in the current directory. This file is automatically created if it doesn't exist.

Example JSON file structure:
```json
[
  {
    "id": 1,
    "description": "Buy groceries",
    "status": "To_DO,
    "createdAt": "2024-01-15T10:30:00Z",
    "updatedAt": "2024-01-15T10:30:00Z"
  }
]
```

## Task States

- **`todo`**: Pending task to be done
- **`in-progress`**: Task currently in progress
- **`done`**: Completed task

## Implementation

### Technical Features

- Uses positional command-line arguments
- Interacts with the file system using native modules
- Error handling and edge cases
- No external dependencies

### Recommended Development Flow

1. **Environment setup**: Choose your preferred programming language
2. **Initialization**: Create the basic CLI structure
3. **Incremental implementation**: Develop one feature at a time
4. **Testing**: Verify each feature before continuing
5. **Finalization**: Clean up code and document

## Contribution

This project is designed as a practice exercise to improve programming skills, including:

- Working with the file system
- Handling user input
- Building CLI applications
- JSON data manipulation

## Notes

- The JSON file is automatically created in the current directory
- Task IDs are automatically assigned incrementally
- All dates are stored in ISO format

---

Start organizing your tasks efficiently with Task Tracker CLI 🚀

## Project URL

This project is part of the roadmap.sh backend projects: https://roadmap.sh/backend/projects
