<template>
  <div class="orders">
    <table>
      <thead>
      <tr>
        <th>번호</th>
        <th>주문자명</th>
        <th>주소</th>
        <th>결제수단</th>
        <th>구입항목</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(o, idx) in state.orders" :key="idx">
        <td>{{ state.order.length - idx }}</td>
        <td>{{ o.name }}</td>
        <td>{{ o.address }}</td>
        <td>{{ o.payment }}</td>
        <td>{{ o.items }}</td>
      </tr>
      </tbody>
    </table>
    <div class="container">
    </div>

  </div>
</template>

<script>
import {reactive} from "vue";
import axios from "axios";

export default {
  name: "OrderView",
  setup() {
    const state = reactive({
      orders: [],
    });

    axios.get(`/api/orders`).then(({data}) => {
      console.log(data);
      state.orders = data;
    })

    return {state}
  },

}
</script>

<style scoped>

</style>