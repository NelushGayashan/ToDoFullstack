import axios from "axios";

const API_URL = "http://localhost:8080/tasks";

export const fetchRecentTasks = async () => {
  try {
    const response = await axios.get(`${API_URL}/recent`);
    return response.data;
  } catch (error) {
    console.error("Error fetching recent tasks:", error);
    throw error;
  }
};

export const createTask = async ({ title, description }) => {
  try {
    const taskData = { title, description, completed: false };
    const response = await axios.post(API_URL, taskData);
    return response.data;
  } catch (error) {
    console.error("Error adding task:", error);
    throw error;
  }
};

export const markTaskAsDone = async (taskId) => {
  try {
    await axios.put(`${API_URL}/${taskId}/done`);
  } catch (error) {
    console.error("Error marking task as done:", error);
    throw error;
  }
};
