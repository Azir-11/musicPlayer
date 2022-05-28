<template>
<!--底部 -->
    <ul>
        <li>
            <div class="title"> <img src="@/assets/HomeCarousel/1.jpg" alt="">
                <div style="width:100%;margin-left:30%">
                    <p style="display:inline-block;width:auto;float: left;">唯一</p>
                    <img class="like" src="@/assets/like/0.jpg" :style="ischange ? 'display:block' : 'display:none'"
                        v-on:click="change1" alt="">
                    <img class="like" src="@/assets/like/1.jpg" :style="ischange ? 'display:none' : 'display:block'"
                        v-on:click="change2" alt="">
               
</div>
                <p style="float: left;width: 70%; margin-left:5%">邓紫棋</p>
                 
            </div>
        </li>
        <li><Player></Player></li>
        <li>3</li>
    </ul>
</template>
    
<script setup lang='ts'>
import { ref } from 'vue'
import Player from './footerconponent/player.vue'
let ischange = ref(true)
const change1 = () => {
    ischange.value = false
}
const change2 = () => {
    ischange .value= true
}
</script>
    
<style scoped>
ul {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: space-between;
    padding: 0;
   
}

ul li:nth-child(1) {
    flex: 2;
    align-items: center;
}

.title {
    width: 100%;
    height: 72%;
    ;
    margin-top: 5%;

}

ul li .title .like {
    width: auto;
    height: 24px;
}
ul li .title .like:hover{
    border-radius: 30%; 
    border: 1px solid #626aef; ;
}

ul li:nth-child(1) img {
    float: left;
    width: 25%;
    height: 100%;
    border-radius: 10%;
    cursor: pointer;
}



ul li:nth-child(2) {
    flex: 6;
    height: 100%;
}

ul li:nth-child(3) {
    flex: 2;
}
</style>