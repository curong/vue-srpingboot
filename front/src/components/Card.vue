<template>
    <div class="col">
        <div class="card shadow-sm">
            <span class="img" :style="{ backgroundImage : `url ( http://localhost/img/img%20(1).jpg )`}" />
            <!-- <img :src="item.imgPath" :alt="item.name"> -->
            <div class="card-body">
                <p class="card-text"> {{ item.name }} </p>
                <div class="d-flex justify-content-between align-items-center">
                    <div class="btn-group">
                        <button class="btn btn-primary">구매</button>
                    </div>
                    <small class="text-muted">{{ item.price }} 원</small>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: 'Card',
    props: {
        item: Object
    },
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
</style>