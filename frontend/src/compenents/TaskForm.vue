<template>
  <form @submit.prevent="addTask" class="mt-6 mb-6 flex space-x-2">
    <input v-model="title" placeholder="New Task..." required class="input flex-1" />
    <button type="submit" class="btn">Add</button>
  </form>
</template>

<script>
import axios from 'axios';
export default {
  data: () => ({ title: '' }),
  methods: {
    async addTask() {
      await axios.post(
        '/api/todos', 
        { title: this.title }, 
        { headers: { Authorization: `Bearer ${localStorage.getItem('token')}` } }
      );
      this.$emit('task-added');
      this.title = '';
    }
  }
};
</script>

