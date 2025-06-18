<template>
  <div class="min-h-screen flex items-center justify-center bg-gradient-to-r from-blue-500 to-indigo-600">
    <div class="bg-white shadow-xl rounded-2xl p-10 w-full max-w-md">
      <h2 class="text-2xl font-bold text-center text-gray-800">Sign In</h2>
      <form @submit.prevent="handleLogin" class="mt-6">
        <input type="email" v-model="email" placeholder="Email" required class="input-field" />
        <input type="password" v-model="password" placeholder="Password" required class="input-field" />
        <button type="submit" class="btn-primary w-full">Login</button>
        <p class="text-center mt-4 text-sm">
          Don't have an account? 
          <router-link to="/register" class="text-blue-500 hover:underline">Register</router-link>
        </p>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      password: ''
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await fetch("http://<BACKEND_HOST>:8080/api/auth/login", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ email: this.email, password: this.password })
        });

        const data = await response.json();
        if (response.ok) {
          localStorage.setItem("token", data.token);
          this.$router.push("/dashboard");
        } else {
          alert(data.message || "Login failed");
        }
      } catch (error) {
        alert("An error occurred.");
      }
    }
  }
};
</script>

<style scoped>
.input-field {
  @apply mt-4 p-3 w-full border rounded-md shadow-sm;
}
.btn-primary {
  @apply mt-6 bg-blue-600 text-white p-3 rounded-lg hover:bg-blue-700 transition;
}
</style>
