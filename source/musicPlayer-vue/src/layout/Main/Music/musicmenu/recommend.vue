<template>
  <!-- 推荐页 -->
  <el-table
    :data="MusicList"
    height="450"
    style="width: 100%"
    lazy="true"
    row-style="background-color:#ebeaf9"
    stripe="true"
    #default="scope"
  >
    <el-table-column type="index" align="center " width="50" />
    <el-table-column align="left " width="80">
      <template #default="scope">
        <el-icon
          @click="like(scope.$index, scope.row)"
          class="Eleme"
          :style="MusicList[scope.$index].likestatus ? 'display:none' : 'display:block'"
          ><Eleme
        /></el-icon>
        <el-icon
          class="Eleme"
          @click="islike(scope.$index, scope.row)"
          :style="MusicList[scope.$index].likestatus ? 'display:block' : 'display:none'"
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
    <el-table-column prop="musicname" label="音乐标题" width="120" />
    <el-table-column prop="reltime" label="歌手" width="120" />
    <el-table-column prop="musicname" label="专辑" width="120" />
    <el-table-column prop="duration" label="专辑" width="120" />
  </el-table>
</template>

<script lang="ts" setup>
import {  get,  } from "@/utils/api";
import { Eleme, ElemeFilled } from "@element-plus/icons-vue";
import { ref,computed } from "vue";
import { useMusicStore, useCounterStore } from "@/stores/counter";
const useMusicmun = useMusicStore();
const useCountmin = useCounterStore();
let num = ref();
let MusicList: any = ref([]);


/**过滤时间
 *
 */
const TimeFilter = (time: any) => {
  var timeType = typeof time;
  if (timeType === "number" || timeType === "string") {
    time = parseInt(time);
    var hours = Math.floor(time / 3600);
    time = time - hours * 3600;
    var mimute = Math.floor(time / 60);
    time = time - mimute * 60;
    return hours + ":" + ("0" + mimute).slice(-2) + ":" + ("0" + time).slice(-2);
  } else {
    return "0:00:00";
  }
};
// /musicList/love
const like = (index: number, value: any) => {
  console.log(index, value);

  MusicList.value[index].likestatus = true;
};
const islike = (index: number, value: any) => {
  console.log(index, value);
  MusicList.value[index].likestatus = false;
};
/**
 * 点击播放
 */
const player = (index: number, value: any) => {
  //   返回当前歌曲的index下标
  useMusicmun.$state.musicIndex = index;
  //   返回当前歌曲 的src
  useMusicmun.$state.musicSrc = MusicList.value[index].mp3src;

  console.log(1111, useMusicmun.$state.musicSrc);
  // 播放icon样式
  useCountmin.$state.isvisble = false;
  //  播放icon样式
  useCountmin.$state.musicStyle = "";

};

// 获取推荐数据列表
const getMusicList = () => {
  get("musicList/findAll")
    .then((res: any) => {
      console.log(res);

      MusicList.value = res.data;
      console.log(MusicList.value)
      MusicList.value.forEach((item:any)=>{
        // console.log(item)
        item.duration=TimeFilter(item.duration);
      })
  //  console.log(MusicList.value.duration);
      useMusicmun.$state.musicList = MusicList.value;
    })
    .catch((error: any) => {
      console.log(error);
    });
};
getMusicList();
// 假数据

// const FakeData=ref([{
//         likestatus: false,
// musicname: "那就晚安",
// singername: "陈若炯",
// songurl: "@/../public/那就晚安.mp3",
// },{likestatus: false,
// musicname: "唯一",
// singername: "腻歪",
// songurl: "@/../public/唯一.mp3",
// },{likestatus: false,
// musicname: "再怎么爱",
// singername: "尚世达",
// songurl: "@/../public/再怎么爱.mp3",
// },{likestatus: false,
// musicname: "总有一天你会出现在我身边",
// singername: "CRJ486",
// songurl: "@/../public/总有一天你会出现在我身边.mp3",
// }])
</script>
<style scoped>
.Eleme {
  cursor: pointer !important;
}
</style>
