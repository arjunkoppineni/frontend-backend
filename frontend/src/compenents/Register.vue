<template>
  <div class="p-8 bg-white rounded-lg shadow max-w-md mx-auto">
    <h2 class="text-2xl font-bold mb-6 text-center">Register</h2>
    <form @submit.prevent="register">
      <input v-model="form.username" placeholder="Username" required class="input mb-4" />
      <input type="password" v-model="form.password" placeholder="Password" required class="input mb-4" />
      <input type="email" v-model="form.email" placeholder="Email" required class="input mb-6" />
      <button type="submit" class="btn w-full">Register</button>
    </form>
    <p class="mt-4 text-center">
      Have an account?
      <a @click="$router.push('/login')" class="text-blue-500 hover:underline">Login</a>
    </p>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data: () => ({ form: { username: '', password: '', email: '' } }),
  methods: {
    async register() {
      try {
        await axios.post('/api/auth/register', this.form);
        alert('Registered successfully!');
        this.$router.push('/login');
      } catch {
        alert('Registration failed');
      }
    },
  },
};
</script>
