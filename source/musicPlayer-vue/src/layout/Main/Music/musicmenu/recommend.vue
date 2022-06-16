<template>
  <!-- 推荐页 -->
  <el-table
    :data="FakeData"
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
          :style="FakeData[scope.$index].likestatus ? 'display:none' : 'display:block'"
          ><Eleme
        /></el-icon>
        <el-icon
          class="Eleme"
          @click="islike(scope.$index, scope.row)"
          :style="FakeData[scope.$index].likestatus? 'display:block' : 'display:none'"
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
    <el-table-column prop="musicname" label="专辑" width="180"  />
  </el-table>
</template>

<script lang="ts" setup>
import { post, get,posts } from "@/utils/api";
import { Eleme, ElemeFilled } from "@element-plus/icons-vue";
import { ref } from "vue";
import {useMusicStore,useCounterStore} from "@/stores/counter"
const useMusicmun=useMusicStore()
const useCountmin=useCounterStore()
let num= ref();
let MusicList:any= ref([]);

// /musicList/love
const like = (index: number, value: any) => {
  console.log(index, value);
   
 MusicList.value[index].likestatus= true ;
};
const islike = (index: number, value: any) => {
  console.log(index, value);
  MusicList.value[index].likestatus = false ;
};
const player = (index: number, value: any) => {
 //   点击播放返回当前歌曲的index下标      
  useMusicmun.$state.musicIndex = index;
//   点击播放返回当前歌曲 的src
  useMusicmun.$state.musicSrc= FakeData.value[index].songurl
      
  console.log( 1111, useMusicmun.$state.musicSrc);
  useCountmin.$state.isvisble=false
//  
useCountmin.$state.musicStyle=""
  };

// 获取推荐数据列表
const getMusicList = () => {
  get("musicList/findAll").then((res: any) => {
    console.log(res);
      MusicList.value = res.data;
  });
};
getMusicList();
// 假数据
const FakeData=ref([{
        likestatus: false,
musicname: "那就晚安",
singername: "陈若炯",
songurl: "@/../public/那就晚安.mp3",
},{likestatus: false,
musicname: "唯一",
singername: "腻歪",
songurl: "@/../public/唯一.mp3",
},{likestatus: false,
musicname: "再怎么爱",
singername: "尚世达",
songurl: "@/../public/再怎么爱.mp3",
},{likestatus: false,
musicname: "总有一天你会出现在我身边",
singername: "CRJ486",
songurl: "@/../public/总有一天你会出现在我身边.mp3",
}])
useMusicmun.$state.musicList=FakeData.value 
</script>
<style scoped>
.Eleme {
  cursor: pointer !important;
}
</style>
