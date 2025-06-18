<template>
  <div class="min-h-screen bg-gray-100 p-8">
    <div class="max-w-4xl mx-auto">
      <h1 class="text-3xl font-bold mb-6 text-center text-indigo-600">My To-Do Dashboard</h1>
      <div class="flex gap-4 mb-4">
        <input v-model="newTask" placeholder="Enter new task" class="flex-grow p-3 border rounded-md shadow-sm" />
        <button @click="addTask" class="btn-primary">Add</button>
      </div>
      <ul class="bg-white shadow-lg rounded-lg p-6 space-y-4">
        <li v-for="task in tasks" :key="task.id" class="flex justify-between items-center border-b pb-2">
          <span>{{ task.description }}</span>
          <button @click="deleteTask(task.id)" class="text-red-500 hover:underline">Delete</button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newTask: "",
      tasks: []
    };
  },
  methods: {
    async fetchTasks() {
      const token = localStorage.getItem("token");
      const res = await fetch("http://<BACKEND_HOST>:8080/api/tasks", {
        headers: { Authorization: `Bearer ${token}` }
      });
      const data = await res.json();
      this.tasks = data;
    },
    async addTask() {
      if (!this.newTask) return;
      const token = localStorage.getItem("token");
      const res = await fetch("http://<BACKEND_HOST>:8080/api/tasks", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`
        },
        body: JSON.stringify({ description: this.newTask })
      });
      if (res.ok) {
        this.newTask = "";
        this.fetchTasks();
      }
    },
    async deleteTask(id) {
      const token = localStorage.getItem("token");
      await fetch(`http://<BACKEND_HOST>:8080/api/tasks/${id}`, {
        method: "DELETE",
        headers: { Authorization: `Bearer ${token}` }
      });
      this.fetchTasks();
    }
  },
  mounted() {
    this.fetchTasks();
  }
};
</script>

<style scoped>
.btn-primary {
  @apply bg-indigo-600 text-white px-4 py-2 rounded hover:bg-indigo-700 transition;
}
</style>
