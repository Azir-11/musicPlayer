<template>
  <!--喜欢页 -->
  <el-table
    :data="useMusicmin.$state.musicList"
    height="450"
    style="width: 100%"
    lazy="true"
    row-style="background-color:#ebeaf9"
    stripe="true"
  >
    <el-table-column type="index" align="center " />
    <el-table-column align="left ">
      <template #default="scope">
        <el-icon
          @click="like(scope.$index, scope.row)"
          class="Eleme"
          :style="
            useMusicmin.$state.musicList[scope.$index].likestatus
              ? 'display:none'
              : 'display:block'
          "
          ><Eleme
        /></el-icon>
        <el-icon
          class="Eleme"
          @click="islike(scope.$index, scope.row)"
          :style="
            useMusicmin.$state.musicList[scope.$index].likestatus
              ? 'display:block'
              : 'display:none'
          "
          ><ElemeFilled
        /></el-icon>
      </template>
    </el-table-column>
    <el-table-column align="left">
      <template #default="scope">
        <el-button
          size="small"
          color="#626aef"
          @click="player(scope.$index, scope.row)"
          plain
          >播放</el-button
        >
      </template>
    </el-table-column>
    <el-table-column prop="musicname" label="音乐标题" width="180" />
    <el-table-column prop="singername" label="歌手" width="180" />
    <el-table-column prop="musicname" label="专辑" width="180" />
  </el-table>
</template>

<script lang="ts" setup>
import { post, get } from "@/utils/api";
import { Eleme, ElemeFilled } from "@element-plus/icons-vue";
import { ref } from "vue";
import { useMusicStore } from "@/stores/counter";
const useMusicmin = useMusicStore();
let num = ref();
let MusicList: any = ref([]);
// 默认播放
// useMusicmin.$state.musicSrc=useMusicmin.$state.musicList[0].songurl

const like = (index: number, value: any) => {
  console.log(index, value);
  useMusicmin.$state.musicList[index].likestatus = true;
};
const islike = (index: number, value: any) => {
  console.log(index, value);
  useMusicmin.$state.musicList[index].likestatus = false;
};

const player = (index: number, value: any) => {
  num.value = index;
  useMusicmin.$state.musicSrc = useMusicmin.$state.musicList[index].songurl;
  console.log(7777, useMusicmin.$state.musicSrc);
};

// 获取推荐数据列表
const getMusicList = () => {
  get("musicList/findAll").then((res: any) => {
    console.log(res);
    useMusicmin.$state.musicList = res.data;
  });
};
getMusicList();
</script>
<style scoped>
.Eleme {
  cursor: pointer !important;
}
</style>
