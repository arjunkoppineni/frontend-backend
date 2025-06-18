<template>
  <div>
    <TaskForm @task-added="fetchTasks" />
    <table class="table-auto w-full mt-6 bg-white rounded-lg">
      <thead>
        <tr class="bg-blue-500 text-white">
          <th class="px-4 py-2">#</th>
          <th class="px-4 py-2">Title</th>
          <th class="px-4 py-2">Due Date</th>
          <th class="px-4 py-2">Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(task, i) in tasks" :key="task.id" class="hover:bg-gray-100">
          <td class="border px-4 py-2">{{ i + 1 }}</td>
          <td class="border px-4 py-2">{{ task.title }}</td>
          <td class="border px-4 py-2">{{ task.dueDate }}</td>
          <td class="border px-4 py-2">
            <button @click="deleteTask(task.id)" class="text-red-500">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';
import TaskForm from './TaskForm.vue';
export default {
  components: { TaskForm },
  data: () => ({ tasks: [] }),
  mounted: () => this.fetchTasks(),
  methods: {
    async fetchTasks() {
      const res = await axios.get('/api/todos', {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
      });
      this.tasks = res.data;
    },
    async deleteTask(id) {
      await axios.delete(`/api/todos/${id}`, {
        headers: { Authorization: `Bearer ${localStorage.getItem('token')}` }
      });
      this.fetchTasks();
    }
  }
};
</script>

