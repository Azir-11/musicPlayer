<template>
    <!-- 底部播放功能 -->
    <!-- 功能模块 -->

    <div class="function">
        <el-button color="#626aef" :icon="SortDown" plain></el-button>
         <el-icon @click="Videopause" :style="mincounter.$state.isvisble ? 'display:block' : 'display: none'">
        <VideoPause />
    </el-icon>
    <el-icon :style=" mincounter.$state.isvisble ? 'display:none' : 'display:block'" @click="Videoplay">
        <VideoPlay />
    </el-icon>
        <el-button color="#626aef" :icon="SortUp" plain></el-button>
    </div>
    <!-- 进度条 -->
    <div class="Progress"> <div class="slider-demo-block">
    <span class="demonstration"></span>
    <el-slider v-model="value4" :format-tooltip="formatTooltip" />
  </div></div>
</template>
    
<script setup lang='ts'>
import { SortDown, SortUp,VideoPause,VideoPlay } from '@element-plus/icons-vue'
import { ref } from 'vue'
import { useCounterStore } from '@/stores/counter';
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
// 进度条
const value4 = ref(0)
const formatTooltip = (val: number) => {
  return val / 100
}

</script>
    
<style scoped>
.function {
    width: 100%;
    height: 60%;
    display: flex;
    align-items: center;
    justify-content: center;
}
.el-icon {
    border-radius: 100%;
    cursor: pointer;
    font-size: 2em;
}
.Progress {
    width: 100%;
    height: 40%;
}
</style>