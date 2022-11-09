import { createStore } from 'vuex'

export default createStore({
  state() {
    return {
      account: {
        id: 0
      }
    }
  },

  getters: {
  },

  mutations: {
    setAccount(state, payload) {
      state.account.id = payload;
    }
  },

  actions: {
  },

  modules: {
  }
})
