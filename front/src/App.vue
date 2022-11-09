<template>
  <Header />
  <RouterView />
  <Footer />
</template>

<script>
import Header from '@/components/Header'
import Footer from '@/components/Footer'
import store from './store';

export default {
  name: 'App',
  components: {
    Header,
    Footer
  },

  setup() {
    const id = sessionStorage.getItem("id");

    if(id) {
      store.commit("setAccount", id)
    }

  }
}
</script>

<style scoped>
</style>
