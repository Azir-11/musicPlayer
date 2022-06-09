<template  >
   <!-- 主页 -->
    <span class="left">
        <!-- 歌词部分 -->
        <div class="profile">
            <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto">
                <li v-for="(item, index) in count" class="infinite-list-item" :class="index == 0 ? 'Song_title' : ''">
                    {{ item }}</li>
            </ul>

        </div>
        <!-- 播放器 -->
        <div class="Player">
            <Player></Player>
        </div>
        <!-- 3大盒子功能 -->
        <div class="function">
            <Function></Function>
        </div>
    </span>
    <span class="right"></span>
</template>
    
<script setup lang='ts'>
import Player from "./componet/Player.vue";
import { ref } from 'vue'
import Function from './componet/Function.vue'

const count = ref(0)
const load = () => {
    count.value += 2
}
</script>
    
<style scoped>
.left {
    display: inline-block;
    width: 70%;
    height: 99%;
    border: 1px solid black;
}

.right {
    position: absolute;
    display: inline-block;
    width: 30%;
    height: 99%;
    border: 1px solid black;
}

/* 歌词部分 */
.infinite-list {
    height: 300px;
    padding: 0;
    margin: 0;
    list-style: none;
}

.infinite-list::-webkit-scrollbar {
    /*滚动条整体样式*/
    width: 4px;
    /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
}

.infinite-list::-webkit-scrollbar-thumb {
    /*滚动条里面小方块*/
    border-radius: 10px;
    background: #9e9e9e;
    box-shadow: inset 0 0 5px #e9e9eb;
}

/* //滚动条底层颜色! */
.AddSinfinite-listtyle::-webkit-scrollbar-track {
    border-radius: 10px;
    background: #ededed;

    /*滚动条里面轨道*/
    box-shadow: inset 0 0 5px rgba(0, 0, 0, .1);
}

.infinite-list .infinite-list-item {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 50px;
    margin: 10px;
    color: #b7b7c1;
    font-size: 14px;
    font-weight: 700;
    line-height: 20px;
}

.infinite-list .infinite-list-item+.list-item {
    margin-top: 10px;
}

.profile {
    position: absolute;
    width: 40%;
    height: 45%;
    top: 2%;
    border: 1px solid black;
}

.Song_title {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 50px;
    margin: 10px;
    font-size: 30px !important;
    font-weight: 700;


}



.profile p {
    display: block;
    width: 100%;
    font-size: 14px;
    font-weight: 700;
    line-height: 20px;
    color: #b7b7c1;
    overflow: hidden;
    text-overflow: ellipsis;
}

.Player {
    position: absolute;
    right: 0;
    top: 1%;
    width: 60%;
    height: 55%;
    border: 1px solid black;
}

/* 功能3大盒子 */
.function {
    position: absolute;
    width: 100%;
    height: auto;
    top: 59%;
    z-index: 999;
}
</style>