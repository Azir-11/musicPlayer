<template>
  <ul
    ref="lyric"
    v-infinite-scroll="load"
    @scroll="onchange"
    class="infinite-list"
    style="overflow: auto"
  >
    <li v-for="(item, index) in musicmin.$state.newmusicLrc" class="infinite-list-item">
      <div :class="musicmin.$state.lyricIndex == index ? 'highlight' : ''">
        {{ item.lyric }}
      </div>
    </li>
  </ul>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useMusicStore, useLyrics } from "@/stores/counter";
const musicmin = useLyrics();
const count = ref(0);
const lyric = ref<any>(null); // dom -  包含歌词标签的ul，高度很高，主要用于控制transform
//  var top = lyric.value.scrollTop;
musicmin.$state.Lyr = lyric;
const onchange = () => {
//   console.log(1111, lyric.value.scrollTop);
  lyric.value.scrollTop;
};
 
const load = () => {
  count.value += 10;
};
</script>

<style scoped>
.infinite-list {
  height: 300px;
  padding: 0;
  margin: 0;
  list-style: none;
  color: #b7b7c1;
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
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);
}

.infinite-list .infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  margin: 10px;

  font-size: 14px;
  font-weight: 700;
  line-height: 20px;
}

.infinite-list .infinite-list-item + .list-item {
  margin-top: 10px;
}
/* //歌词高亮 */

.highlight {
  font-weight: 700;
  color: black;
}
</style>
