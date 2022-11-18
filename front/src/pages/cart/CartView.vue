<template>
  <div class="cart">

    <div class="container">
      <ul>
        <li v-for="(i, idx) in state.items" :key="idx">
          <img :src="i.imgPath" width="200" height="200" alt="장바구니 추가 아이템 이미지">
          <span class="name">{{ i.name }}</span>
          <span class="price">{{ lib.addComma(i.price - (i.price * i.discountPer / 100)) }}</span>
          <i class="fa fa-trash"></i>
        </li>
      </ul>
    </div>

  </div>
</template>

<script>
import {reactive} from "vue";
import axios from "axios";
import lib from "@/scripts/lib"

export default {
  name: "Cart",
  setup() {
    const state = reactive({
      items: []
    })

    axios.get(`/api/cart/items`).then(({data}) => {
      state.items = data;
    });

    return {state, lib}
  }
}
</script>

<style scoped>
.cart ul li {
  list-style: none;
  margin: 0;
  padding: 0;
}

.cart ul li {
  border: 1px solid #eee;
  margin-top: 25px;
  margin-bottom: 25px;
}

.cart ul li img {
  width: 150px;
  height: 150px;
}

.cart ul li .name {
  margin-left: 25px;
}

.cart ul li .price {
  margin-left: 25px;
}

.cart ul li i {
  float: right;
  font-size: 20px;
  margin-top: 65px;
  margin-right: 50px;
}

</style>