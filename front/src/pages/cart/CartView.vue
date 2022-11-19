<template>
  <div class="cart">

    <div class="container">
      <ul>
        <li v-for="(i, idx) in state.items" :key="idx">
          <img :src="i.imgPath" width="200" height="200" alt="장바구니 추가 아이템 이미지">
          <span class="name">{{ i.name }}</span>
          <span class="price">{{ lib.addComma(i.price - (i.price * i.discountPer / 100)) }}</span>
          <i class="fa fa-trash" @click="remove(i.pid)"></i>
        </li>
      </ul>

      <router-link to="/order" class="btn btn-primary">구입하기</router-link>

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
    const load = () => {
      axios.get(`/api/cart/items`).then(({data}) => {
        state.items = data;
      });
    }

    load();
    return {state, lib, load}
  },
  methods: {
    remove(itemId)
    {
      if ( confirm("정말 삭제하시겠습니까?") )
      {
        axios.delete(`/api/cart/items/${itemId}`).then(({data}) => {
          this.load();
        })
      }
    }

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

.cart .btn {
  display: block;
  margin: 0 auto;
  padding: 30px 50px;
  font-size: 20px;
  width: 300px;
}

</style>