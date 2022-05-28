<template>
<!-- 中间部分 -->
    <audio>
        <source src="@/../public/唯一.mp3" type="audio/mpeg">
    </audio>
    <!-- 中间图片 -->
    <img class="img1" src="@/assets/HomeCarousel/1.jpg" alt="">
    <el-icon @click="Videopause" :style=" mincounter.$state.isvisble? 'display:block' : 'display: none'">
        <VideoPause />
    </el-icon>
    <el-icon :style="mincounter.$state.isvisble? 'display:none' : 'display:block'" @click="Videoplay">
        <VideoPlay />
    </el-icon>
</template>

<script setup lang='ts'>
import { VideoPlay, VideoPause } from '@element-plus/icons-vue'
import { ref } from 'vue'
import {useCounterStore} from '@/stores/counter'
const mincounter=useCounterStore()
// 播放

const Videoplay = () => {
   mincounter.$state.isvisble =true
     console.log(mincounter.$state.isvisble);
}
// 暂停
const Videopause = () => {
    mincounter.$state.isvisble = false
}
</script>
    
<style scoped>
.img1 {
    position: relative;
    width: 67%;
    height: 70%;
    top: 15%;
    left: 17%;
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