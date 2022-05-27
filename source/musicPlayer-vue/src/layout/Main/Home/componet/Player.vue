<template>
    <audio >
        <source src="@/../public/唯一.mp3" type="audio/mpeg">
    </audio>
    <!-- 中间图片 -->
    <img class="img1" src="@/assets/HomeCarousel/1.jpg" alt="">
    <el-icon @click="Videopause" :style="isvisble?'display:block':'display: none'" >
        <VideoPause />
        </el-icon>
         <el-icon :style="isvisble?'display:none':'display:block'" @click="Videoplay">
        <VideoPlay />
    </el-icon>
</template>

<script setup lang='ts'>
import { VideoPlay, VideoPause } from '@element-plus/icons-vue'
import { ref } from 'vue'
// 播放
let isvisble=ref(false) 
const Videoplay = () => {
       isvisble.value=true
}
// 暂停
const Videopause = () => {
 isvisble.value=false
}
</script>
    
<style scoped>
.img1 {
    position: relative;
    width: 70%;
    height: 70%;
    top: 15%;
    left: 15%;
    border-radius: 50%;

}

.el-icon {
    position: absolute;
    top: 76%;
    left: 42%;
    border-radius: 100%;
    background-color: #ffbe0f;
    color: #f1e66a;
    cursor: pointer;
    font-size: 3.9em;
}
</style>