<template>
  <!-- 喜欢页 -->
  <el-table
    :data="likeList"
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
        <!-- MusicList[scope.$index].likestatus  -->
        <el-icon class="Eleme" @click="islike(scope.$index, scope.row)"
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
    <el-table-column prop="duration" label="时长" width="120" />
  </el-table>
</template>

<script lang="ts" setup>
import { get, posts } from "@/utils/api";
import { Eleme, ElemeFilled } from "@element-plus/icons-vue";
import { ref, computed } from "vue";

import { useMusicStore, useCounterStore, useLyrics } from "@/stores/counter";
const useMusicmun = useMusicStore();
const useCountmin = useCounterStore();
const useLyricsmin = useLyrics();
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

//用来增加或者取消喜欢
const UpdateLikes = (like_ids: any) => {
  posts("musicList/updateMylove", {
    id: sessionStorage.getItem("userId"),
    musiclist: `${like_ids}`,
  })
    .then((res: any) => {
      console.log(res, 666);
    })
    .catch((error: any) => {
      console.log(error);
    });
};

let like_id: number[] = [];
let likeList = ref([]);
//判断喜欢
// /musicList/love

const islike = (index: number, value: any) => {
  like_id.splice(index, 1);
  console.log(666666, like_id);
  if (confirm("确定取消")) {
  UpdateLikes(like_id); //用来增加或者取消喜欢

    likeList.value.splice(index,1)
  }
};

//获取用户喜欢的列表
const getLikeList = () => {
  let like_ID2 = ref([]);
  get(`musicList/findAllLove?id=${sessionStorage.getItem("userId")}&pageNum=1&pageSize=5`)
    .then((res: any) => {
      likeList.value = res.data.data.list;
     
      // console.log(likeList.value);
      //过滤时间
      likeList.value.forEach((item: any) => {
        // console.log(item)
        item.duration = TimeFilter(item.duration);
      });
      if (res.data.musiclist != "") {
        like_ID2.value = res.data.musiclist.split(",");
        console.log(1111111, res.data.musiclist);
        like_ID2.value.forEach((item: any) => {
          like_id.push(parseInt(item));
          console.log(item);
        });
      }
    })
    .catch((error: any) => {
      console.log(error);
    });
};

setTimeout(() => {
  getLikeList();
}, 200);

//写歌词，获取这首歌的数据
const getlric = (id: number) => {
  get("musicList/findAll?id=" + id + "")
    .then((res: any) => {
      console.log(333, res.data.lrc);
      useLyricsmin.$state.musicLrc = res.data.lrc;
      useLyricsmin.Initialization(); //换个清空上一首push的值
      //解析歌词时间与值
      useLyricsmin.LyricsTraversal();
    })
    .catch((error: any) => {
      console.log(error);
    });
};

/**
 * 点击播放
 */

const player = (index: number, value: any) => {
  //   返回当前歌曲的index下标
   useMusicmun.$state.musicList = likeList.value; //主要控制列表
  useMusicmun.$state.musicIndex = index;
  //   返回当前歌曲 的src
  useMusicmun.$state.musicSrc = MusicList.value[index].mp3src;

  console.log(1111, useMusicmun.$state.musicSrc);
  // 播放icon样式
  useCountmin.$state.isvisble = false;
  //  播放icon样式
  useCountmin.$state.musicStyle = "";
  getlric(value.id);
  //换歌歌词初始化
  useLyricsmin.$state.lyricIndex = -1; //高亮歌词
  useLyricsmin.$state.idx = 0; //下标
};

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
