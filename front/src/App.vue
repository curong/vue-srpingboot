<template>
  <Header/>
  <RouterView/>
  <Footer/>
</template>

<script>
import Header from '@/components/Header'
import Footer from '@/components/Footer'
import store from './store';
import axios from "axios";
import {watch} from "vue";
import {useRoute} from "vue-router";

export default {
  name: 'App',
  components: {
    Header,
    Footer
  },

  setup() {

    const check = () => {
      axios.get("/api/account/check").then( ({data}) => {
        console.log(data)
        store.commit("setAccount", data || 0);
      })
    }

    const route = useRoute();

    watch(route, () => {
      check();
    })

  }
}
</script>

<style scoped>
</style>
