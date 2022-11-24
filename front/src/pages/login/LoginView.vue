<template>

  <div class="text-center">

    <main class="form-signin m-auto col-md-3">
      <!-- <img class="mb-4" src="/docs/5.2/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57"> -->
      <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

      <div class="form-floating">
        <input type="email" class="form-control" id="floatingInput" placeholder="이메일을 입력해 주세요"
          @keyup.enter="submit()" v-model="state.form.email">
        <label for="floatingInput">Email address</label>
      </div>
      <div class="form-floating">
        <input type="password" class="form-control" id="floatingPassword" placeholder="비밀번호를 입력해 주세요"
          @keyup.enter="submit()" v-model="state.form.password">
        <label for="floatingPassword">Password</label>
      </div>

      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me" @keyup.enter="submit()"> Remember me
        </label>
      </div>
      <button class="w-100 btn btn-lg btn-primary" @click="submit()">Sign in</button>
      <p class="mt-5 mb-3 text-muted">© 2022 WJ</p>
    </main>
  </div>
</template>

<script>
import router from '@/router'
import store from '@/store'
import { reactive } from '@vue/reactivity'
import axios from 'axios'

export default {
  name: 'LoginView',
  props: {},

  setup() {

    const state = reactive({
      form: {
        email: '',
        password: ''
      },

    })

    const submit = () => {
      axios.post(`/api/account/login`, state.form)
        .then((res) => {

          // vuex store에 입력
          store.commit('setAccount', res.data);
          
          // session storage 사용
          sessionStorage.setItem("id", res.data)

          // router 이동
          router.push("/");

          alert(`로그인 성공`)
        })
        .catch((err) => {
          window.alert("로그인 정보가 존재하지 않습니다.")
        })
    }

    return { state, submit }
  },

  /*
    data() {
      return {
        form: {
          email: '',
          password: ''
        }
      }
    },
  */

  /*
    methods: {
      submit() {
        axios.post(`/api/account/login`, state.form)
          .then((data) => {
            console.log(data);
            alert(`data`)
          })
      }
    }
  */
}
</script>

<style scoped>
.bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
}

@media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
}

.b-example-divider {
  height: 3rem;
  background-color: rgba(0, 0, 0, .1);
  border: solid rgba(0, 0, 0, .15);
  border-width: 1px 0;
  box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
}

.b-example-vr {
  flex-shrink: 0;
  width: 1.5rem;
  height: 100vh;
}

.bi {
  vertical-align: -.125em;
  fill: currentColor;
}

.nav-scroller {
  position: relative;
  z-index: 2;
  height: 2.75rem;
  overflow-y: hidden;
}

.nav-scroller .nav {
  display: flex;
  flex-wrap: nowrap;
  padding-bottom: 1rem;
  margin-top: -1px;
  overflow-x: auto;
  text-align: center;
  white-space: nowrap;
  -webkit-overflow-scrolling: touch;
}
</style>