<template>
    <div class="col">
        <div class="card shadow-sm">
            <!-- <span class="img" :style="{ backgroundImage: `url (${item.imgPath})` }"></span> -->
            <img :src="item.imgPath" :alt="item.name">
            <div class="card-body">
                <p class="card-text"> {{ item.name }} 
                <small class="discount badge bg-danger">{{ item.discountPer}}%</small> </p>
                <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                        <button class="btn btn-primary">구매</button>
                    </div>
                    <small class="price text-muted">{{ lib.addComma(item.price) }} 원</small>
                    <small class="real text-danger">{{ lib.addComma(item.price - (item.price * item.discountPer / 100))
                    }} 원</small>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import lib from "@/scripts/lib";

export default {
    name: 'Card',
    props: {
        item: Object
    },
    setup() {
        return { lib }
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