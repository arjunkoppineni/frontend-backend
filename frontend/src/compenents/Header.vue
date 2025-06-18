<template>
  <header class="bg-white shadow p-4 flex justify-between items-center">
    <div class="flex items-center space-x-2">
      <img src="@/assets/logo.png" class="w-10 h-10" alt="Logo" />
      <span class="font-bold text-lg">ToDo Premium</span>
    </div>
    <nav>
      <button
        v-if="!isAuthenticated"
        @click="navigate('/login')"
        class="btn"
      >Login</button>
      <button
        v-if="!isAuthenticated"
        @click="navigate('/register')"
        class="btn ml-2"
      >Register</button>
      <button v-if="isAuthenticated" @click="logout" class="btn">Logout</button>
    </nav>
  </header>
</template>

<script>
import { useRouter } from 'vue-router';
export default {
  setup() {
    const router = useRouter();
    const isAuthenticated = !!localStorage.getItem('token');
    const navigate = (path) => router.push(path);
    const logout = () => {
      localStorage.removeItem('token');
      router.push('/login');
    };
    return { isAuthenticated, navigate, logout };
  },
};
</script>

