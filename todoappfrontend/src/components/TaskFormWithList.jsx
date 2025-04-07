import React, { useState, useEffect } from "react";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import TaskItem from "./TaskItem";
import { fetchRecentTasks, createTask, markTaskAsDone } from "./taskApi.js";
import "../styles.css";

const TaskFormWithList = () => {
  const [tasks, setTasks] = useState([]);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const fetchTasks = async () => {
    try {
      const fetchedTasks = await fetchRecentTasks();
      setTasks(fetchedTasks);
    } catch (error) {
      toast.error("Error fetching tasks.");
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!title || !description) {
      toast.error("Both title and description are required.");
      return;
    }

    try {
      await createTask({ title, description });
      setTitle("");
      setDescription("");
      await fetchTasks();
      toast.success("Task added successfully!");
    } catch (error) {
      if (error.response && error.response.data) {
        const { code, errors } = error.response.data;
        const titleError = errors?.title;

        if (code && titleError) {
          toast.error(`${code} - ${titleError}`);
        } else {
          toast.error("Error adding task.");
        }
      } else {
        toast.error("Error adding task.");
      }
    }
  };

  const handleTaskComplete = async (taskId) => {
    try {
      await markTaskAsDone(taskId);
      await fetchTasks();
      toast.success("Task marked as done!");
    } catch (error) {
      toast.error("Error marking task as done.");
    }
  };

  return (
    <div className="outer-container">
      <div className="white-box">
        <div className="right-pane">
          <h2>Add a Task</h2>
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              placeholder="Task Title"
            />
            <textarea
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              placeholder="Task Description"
            />
            <button type="submit" className="add-task-button">Add Task</button>
          </form>
        </div>

        <div className="separator"></div>

        <div className="left-pane">
          <ul style={{ width: "100%", padding: 0, listStyle: "none" }}>
            {tasks.map((task) => (
              <TaskItem
                key={task.id}
                task={task}
                onTaskComplete={handleTaskComplete}
              />
            ))}
          </ul>
        </div>
      </div>
      <ToastContainer />
    </div>
  );
};

export default TaskFormWithList;
