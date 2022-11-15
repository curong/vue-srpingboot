<template>
  <header>
    <div class="collapse bg-dark" id="navbarHeader">
      <div class="container">
        <div class="row">
          <div class="col-sm-8 col-md-7 py-4">
            <h4 class="text-white">About</h4>
            <p class="text-muted">Add some information about the album below, the author, or any other background
              context. Make it a few sentences long so folks can pick up some informative tidbits. Then, link them off
              to some social networking sites or contact information.</p>
          </div>
          <div class="col-sm-4 offset-md-1 py-4">
            <h4 class="text-white">사이트맵</h4>
            <ul class="list-unstyled">
              <li>
                <router-link to="/">메인화면</router-link>
              </li>
              <li>
                <router-link to="/login" v-if="!$store.state.account.id" class="text-primary">로그인</router-link>
                <a @click="logout()" v-else class="text-primary">로그아웃</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
      <div class="container">
        <router-link to="/" class="navbar-brand d-flex align-items-center">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor"
            stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="me-2"
            viewBox="0 0 24 24">
            <path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z" />
            <circle cx="12" cy="13" r="4" />
          </svg>
          <strong>Gallery</strong>
        </router-link>
        <router-link to="/cart" class="cart btn"> <i class="fa fa-shopping-cart" aria-hidden="true"></i> </router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarHeader"
          aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      </div>
    </div>
  </header>
</template>

<script>
import router from '@/router';
import store from '@/store';

export default {
  name: 'Header',

  setup() {
    const logout = () => {
      
      // 로그아웃 시 vuex store의 정보값을 초기화
      store.commit('setAccount', 0);

      // session storage 값 제거
      sessionStorage.removeItem("id");

      // router 이동
      router.push({ path: "/" });
    }

    return { logout }
  },

}
</script>

<style scoped>

  header .navbar .cart {
    margin-left: auto;
    color: white;
  }
</style>
