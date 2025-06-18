<template>
  <div class="min-h-screen flex items-center justify-center bg-gradient-to-r from-green-400 to-blue-500">
    <div class="bg-white shadow-xl rounded-2xl p-10 w-full max-w-md">
      <h2 class="text-2xl font-bold text-center text-gray-800">Register</h2>
      <form @submit.prevent="handleRegister" class="mt-6">
        <input type="text" v-model="username" placeholder="Username" required class="input-field" />
        <input type="email" v-model="email" placeholder="Email" required class="input-field" />
        <input type="password" v-model="password" placeholder="Password" required class="input-field" />
        <button type="submit" class="btn-primary w-full">Register</button>
        <p class="text-center mt-4 text-sm">
          Already have an account? 
          <router-link to="/login" class="text-green-600 hover:underline">Login</router-link>
        </p>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
      email: '',
      password: ''
    };
  },
  methods: {
    async handleRegister() {
      try {
        const response = await fetch("http://<BACKEND_HOST>:8080/api/auth/register", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            username: this.username,
            email: this.email,
            password: this.password
          })
        });

        const data = await response.json();
        if (response.ok) {
          alert("Registration successful! Please login.");
          this.$router.push("/login");
        } else {
          alert(data.message || "Registration failed");
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
  @apply mt-6 bg-green-600 text-white p-3 rounded-lg hover:bg-green-700 transition;
}
</style>
