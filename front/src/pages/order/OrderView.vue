<template>
  <div class="order">

    <div class="container">
      <main>
        <div class="py-5 text-center">
          <h2>주문하기</h2>
        </div>

        <div class="row g-5">
          <div class="col-md-5 col-lg-4 order-md-last">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
              <span class="text-primary">장바구니</span>
              <span class="badge bg-primary rounded-pill">3</span>
            </h4>
            <ul class="list-group mb-3">

              <li class="list-group-item d-flex justify-content-between lh-sm" v-for="(i, idx) in state.items"
                  :key="idx">
                <div>
                  <h6 class="my-0"> {{ i.name }} </h6>
                  <small class="text-danger"> {{ i.discountPer }}% 할인가</small>
                </div>
                <span class="text-muted"> {{ lib.addComma(i.price - (i.price * i.discountPer / 100)) }} 원</span>
              </li>

            </ul>
            <h3 class="te total-price">총 금액 : {{ lib.addComma(computedPrice) }} 원</h3>

            <form class="card p-2">
              <div class="input-group">
                <input type="text" class="form-control" placeholder="Promo code">
                <button type="submit" class="btn btn-secondary">Redeem</button>
              </div>
            </form>
          </div>
          <div class="col-md-7 col-lg-8">
            <hr class="my-4">
            <h4 class="mb-3">주문자 정보</h4>
            <form class="needs-validation" novalidate="">
              <div class="row g-3">


                <div class="col-12">
                  <label for="username" class="form-label">수하인</label>
                  <div class="input-group has-validation">
                    <span class="input-group-text">@</span>
                    <input type="text" class="form-control" id="username" placeholder="수하인 성함을 입력해 주세요." required="">
                    <div class="invalid-feedback">
                      받는분을 입력해 주세요.
                    </div>
                  </div>
                </div>


                <div class="col-12">
                  <label for="address" class="form-label">주소</label>
                  <input type="text" class="form-control" id="address" placeholder="주소를 입력해 주세요." required="">
                  <div class="invalid-feedback">
                    주소를 입력해 주세요.
                  </div>
                </div>


              </div>


              <hr class="my-4">

              <h4 class="mb-3">결제수단</h4>

              <div class="my-3">
                <div class="form-check">
                  <input id="credit" name="creditCard" type="radio" class="form-check-input" checked="" required="">
                  <label class="form-check-label" for="credit">신용카드</label>
                </div>
                <div class="form-check">
                  <input id="account" name="paymentMethod" type="radio" class="form-check-input" required="">
                  <label class="form-check-label" for="account">무통장 입금</label>
                </div>
              </div>

              <div class="row gy-3">

                <div class="col-md-12">
                  <label for="cc-number" class="form-label">카드번호</label>
                  <input type="text" class="form-control" id="cc-number" placeholder="" required="">
                  <div class="invalid-feedback">
                    Credit card number is required
                  </div>
                </div>

              </div>
              <hr class="my-4">
              <button class="w-100 btn btn-primary btn-lg" type="submit">결제하기</button>
            </form>
          </div>
        </div>
      </main>

    </div>

  </div>
</template>

<script>
import {computed, reactive} from "vue";
import axios from "axios";
import lib from "@/scripts/lib"

export default {
  name: "OrderView",
  setup() {
    const state = reactive({
      items: []
    });

    const computedPrice = computed( () => {
      let result = 0;
      for (let i of state.items) {
        result += i.price - i.price * i.discountPer / 100;
      }

      return result;
    })

    const load = () => {
      axios.get(`/api/cart/items`).then(({data}) => {
        return state.items = data;
      });
    }

    load();
    return {state, lib, load, computedPrice}
  },

}
</script>

<style scoped>

</style>