# Software Requirements Specification for Kanban App

The Kanban application is a web-based task and project management tool that helps users organize their work into boards, with tasks assigned to specific columns (e.g., To Do, In Progress, Done). It will allow users to create, manage, and track tasks and boards. The application will have authentication and authorization features, enabling users to manage tasks, boards.

## Note 

This is an SRS for the completed project. Right now authorization and hence all user related details are not impletemented.

## Objectives

- Allow users to create, read, update, and delete tasks and boards.
- Ensure the application is scalable and can support multiple users.

## Functional Requirements (FR)

1. The system should allow users to create a new board.
2. The system should allow users to create tasks within a board.
3. Users should be able to move tasks between columns on the board.
4. The system should allow users to edit or delete tasks.
4. The system should allow users to edit or delete boards.
6. The system should allow users to view task details (title, description, status).
7. The system should display a list of tasks in a specific board, filtered by status or other parameters.
8. The system should allow user registering


## Non-Functional Requirements (NFR)

1. **NFR1**: The system should be responsive and load within 2 seconds for 95% of the user requests.
2. **NFR2**: The application should be able to support up to 1000 concurrent users.
3. **NFR3**: The system should have 99.9% uptime.
4. **NFR4**: The application should be available for use across multiple devices, including desktops, tablets, and smartphones.
5. **NFR5**: The system should have secure authentication and authorization mechanisms to protect user data.
6. **NFR6**: Data should be encrypted both at rest and in transit.

## Use Cases

### Use Case 1: Create a New Board
- **Description**: A user creates a new board to manage tasks.
- **Actor**: User
- **Preconditions**: User is logged in.
- **Postconditions**: A new board is created and visible in the user's dashboard.

### Use Case 2: Add Task to Board
- **Description**: A user adds a new task to an existing board.
- **Actor**: User
- **Preconditions**: A board exists.
- **Postconditions**: A new task is added to the board.

### Use Case 3: Move Task Between Columns
- **Description**: A user moves a task from one column to another.
- **Actor**: User
- **Preconditions**: A task exists on a board.
- **Postconditions**: The task is moved to a new column.

### Use Case 4: Edit or Delete a Task
- **Description**: A user edits or deletes an existing task.
- **Actor**: User
- **Preconditions**: The task exists.
- **Postconditions**: The task is edited or removed from the board.


## User Stories

### User Story 1: Create a Board
- **As a** user
- **I want to** create a new board
- **So that** I can manage my tasks visually and categorize them.

### User Story 2: Create a Task
- **As a** user
- **I want to** create tasks under specific boards
- **So that** I can track my work.

### User Story 3: Move Tasks
- **As a** user
- **I want to** move tasks between columns
- **So that** I can update their status (e.g., "In Progress" to "Completed").

### User Story 4: Delete a Task
- **As a** user
- **I want to** delete tasks that are no longer needed
- **So that** my board remains clean and organized.

### User Story 5: Assign Tasks to Other Users
- **As a** user
- **I want to** assign tasks to other team members
- **So that** the work can be distributed efficiently.

