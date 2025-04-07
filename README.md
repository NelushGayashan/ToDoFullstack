# To-Do Task Application

This is a To-Do task web application consisting of a **Frontend UI**, **Backend API**, and **Database**. The application allows users to create, view, and mark tasks as completed. 

## Features

- **Create Tasks**: Users can create tasks by providing a **title** and **description**.
- **Recent Tasks**: The **most recent 5 tasks** are displayed on the UI.
- **Task Completion**: Users can mark tasks as completed, which removes them from the UI.
- **User Interface**: A simple and clean web UI built using ReactJS, following the provided UI mockup.

## Architecture

The system is made up of three main components:

1. **Database**: The tasks are stored in a relational database (MySQL) in a table called `task`. The `task` table consists of columns such as:
   - `id`: The primary key, auto-incremented.
   - `title`: The title of the task.
   - `description`: The description of the task.
   - `completed`: A boolean flag indicating if the task is completed.
   - `createdAt`: The timestamp when the task was created.

2. **Backend API**: The backend is implemented in **Java** using **Spring Boot**. The backend exposes REST APIs to perform operations such as:
   - **Create Task** (`POST /tasks`): Allows users to create a new task.
   - **Get Recent Tasks** (`GET /tasks/recent`): Retrieves the most recent 5 tasks that are not marked as completed.
   - **Mark Task as Done** (`PUT /tasks/{id}/done`): Marks a task as completed, removing it from the UI.

3. **Frontend UI**: The frontend is built as a **Single Page Application (SPA)** using **ReactJS**. The UI allows users to:
   - Add a new task via a form.
   - View the most recent 5 tasks.
   - Mark tasks as completed using the "Done" button.

All components are containerized using Docker and can be managed using `docker-compose`.

## Tech Stack

- **Frontend**: ReactJS, Axios, React Toastify
- **Backend**: Spring Boot, MySQL (for database)
- **Database**: MySQL
- **Containerization**: Docker, Docker Compose

## Database Schema

The `task` table is designed with the following columns:
```sql
CREATE TABLE task (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 1. Create Task
- **Endpoint**: `POST /tasks`
- **Request Body**:
  ```json
  {
    "title": "Task Title",
    "description": "Task Description"
  }
  ```
- **Response**: The created task object.

2. **Get Recent Tasks**  
   - **Endpoint**: `GET /tasks/recent`

   - **Response**:
   ```json
   [
     {
       "id": 1,
       "title": "Task 1",
       "description": "Description of Task 1",
       "completed": false,
       "createdAt": "2025-04-07T12:34:56"
     },
     ...
   ]

### Running the Application with Docker

1. **Clone the repository:**

   ```bash
   git clone <repo-url>
   cd <repo-directory>
   ```
2. **Build and start the containers:**
  Run the following command to build the containers and start the application using Docker Compose:
  ```bash
  docker-compose up --build
  ```
3. **Access the application:**
  - The frontend will be available at [http://localhost:5173](http://localhost:5173).
  - The backend API will be accessible at [http://localhost:8080](http://localhost:8080).

### Testing

#### Backend Testing
- **Unit Tests**: Manual testing was performed to ensure proper functionality of backend services and controllers.
- **Integration Tests**: Manual testing was conducted to verify the endpoints and database interactions, ensuring the system functions as expected.

#### Frontend Testing
- **Component Tests**: Manual testing was done to ensure the React components and UI behave as expected.


### Structure

The project is organized as follows:

#### Backend (todoBackend)
```bash
todoBackend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── todoBackend/
│   │   │           ├── controller/      # REST controllers
│   │   │           ├── model/           # Task entity and DTOs
│   │   │           ├── repository/      # JPA repositories
│   │   │           ├── service/         # Service layer
│   │   │           ├── exception/       # Custom exception classes
│   ├── resources/
│   │   └── application.properties        # Database configurations
└── Dockerfile
```
![Screenshot from 2025-04-07 20-32-14](https://github.com/user-attachments/assets/7b705172-78fa-423f-8c81-b55c63075a85)


#### Frontend (todo-frontend)
```bash
todo-frontend/
├── src/
│   ├── components/
│   │   ├── TaskFormWithList.js          # Main form and task list component
│   │   ├── TaskItem.js                  # Individual task component
│   ├── App.js                           # Root component
│   ├── index.js                         # React entry point
│   ├── styles.css                       # Global styles
└── Dockerfile
```
file:///home/nelush/Pictures/Screenshots/Screenshot%20from%202025-04-07%2020-32-53.png

### Additional Notes

- **Task Limit**: Only the 5 most recent tasks are displayed on the frontend. Older tasks are hidden.

- **Completed Tasks**: Once a task is marked as completed, it will no longer appear in the task list on the frontend.

- **Error Handling**: Errors are displayed to the user with appropriate messages via toast notifications.

- **Responsive UI**: The UI is designed to be responsive and adapts to various screen sizes.

![Screenshot from 2025-04-07 20-33-58](https://github.com/user-attachments/assets/aedc7c05-e51c-4c31-8cae-9353400a83d7)








