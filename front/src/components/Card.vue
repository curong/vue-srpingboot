<template>
  <div class="col">
    <div class="card shadow-sm">
      <!-- <span class="img" :style="{ backgroundImage: `url (${item.imgPath})` }"></span> -->
      <img :src="item.imgPath" :alt="item.name">
      <div class="card-body">
        <p class="card-text"> {{ item.name }}
          <small class="discount badge bg-danger">{{ item.discountPer }}%</small></p>
        <div class="d-flex justify-content-between align-items-center">
          <div class="btn-group">
            <button class="btn btn-primary" @click="addToCart(item.pid)"> <i class="fa fa-shopping-cart" aria-hidden="true"></i>
            </button>
          </div>
          <small class="price text-muted">{{ lib.addComma(item.price) }} 원</small>
          <small class="real text-danger">{{
              lib.addComma(item.price - (item.price * item.discountPer / 100))
            }} 원</small>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import lib from "@/scripts/lib";
import axios from "axios";

export default {
  name: 'Card',
  props: {
    item: Object
  },
  setup() {
    const addToCart = (itemId) => {
      axios.post(`/api/cart/items/${itemId}`).then(() => {
        console.log(`${itemId}번 아이템 장바구니 추가`)
      });
    }
    return {lib, addToCart}
  }
}
</script>

<style scoped>
.card .img {
  display: inline-block;
  width: 100%;
  height: 250px;
  background-size: cover;
  background-position: center;
}

.card .card-body .price {
  text-decoration: line-through;
}
</style>