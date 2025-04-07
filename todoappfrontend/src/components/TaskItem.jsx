import React from "react";
import { markTaskAsDone } from "./taskApi.js";

const TaskItem = ({ task, onTaskComplete }) => {
  const handleDoneClick = async () => {
    try {
      await markTaskAsDone(task.id);
      onTaskComplete(task.id);
    } catch (error) {
      console.error("Error marking task as done:", error);
    }
  };

  return (
    <li className="task-item">
      <div className="task-header">
        <h3>{task.title}</h3>
      </div>
      <div className="task-body">
        <div className="task-description">
          <p>{task.description}</p>
        </div>
        <div className="task-actions">
          <button type="button" onClick={handleDoneClick}>
            Done
          </button>
        </div>
      </div>
    </li>
  );
};

export default TaskItem;
