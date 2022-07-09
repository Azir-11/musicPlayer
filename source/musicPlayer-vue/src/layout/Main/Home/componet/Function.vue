<template>
<!-- 主页3大盒子播放功能 -->
    <ul class="function">
        <li class="play">
            <!-- 播放模块 -->
            <img src="@/assets/HomeCarousel/1.jpg" alt="">

        </li>
        <li>2</li>
        <li>3</li>
    </ul>
</template>
    
<script setup lang='ts'>
import Waveform from "./waveform.vue";


</script>
    
<style scoped>
.function {
    display: flex;

}

.function li {
    position: relative;
    margin-left: 3%;
    width: 27%;
    height: 15em;
    border: 1px solid;
    background-color: #fff;
    border-radius: 15%;
    box-shadow: 10px 10px 5px rgba(0, 0, 0, 0.2);
}

.function li:nth-child(1) {
    margin-top: -5%;
    margin-left: -7%;
    width: 35%;
    height: 16em;


}

/* 播放模块 */
.play img {
    position: relative;
    left: 40%;
    top: 17%;
    width: 18%;
    height: 15%;
    border-radius: 100%;
}

.play div {
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    top: 30%;
    width: 70%;
    height: auto;
}
</style>