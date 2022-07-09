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
          :style="MusicList[scope.$index].like_id ? 'display:none' : 'display:block'"
          ><Eleme
        /></el-icon>
        <!-- MusicList[scope.$index].likestatus  -->
        <el-icon
          class="Eleme"
          @click="islike(scope.$index, scope.row)"
          :style="MusicList[scope.$index].like_id ? 'display:block' : 'display:none'"
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

//获取用户喜欢的列表
const getLikeList = () => {
  let like_ID2 = ref([]);
  get(`musicList/findAllLove?id=${sessionStorage.getItem("userId")}&pageNum=1&pageSize=5`)
    .then((res: any) => {
      if (res.data.musiclist != "") {
        like_ID2.value = res.data.musiclist.split(",");
        like_ID2.value.forEach((item: any) => {
          console.log(1111111, item);
                    like_id.push(parseInt(item));
        });
         console.log(1111111, like_id);
      }
    })
    .catch((error: any) => {
      console.log(error);
    });
};
getLikeList();
// 获取推荐数据列表
const getMusicList = async () => {
  get("musicList/findAll")
    .then((res: any) => {
      console.log(res);
      MusicList.value = res.data;
      // console.log(MusicList.value);
    res.data.forEach((item: any) => {
        // console.log(item)
        for (let i = 0; i < like_id.length; i++) {
          if (item.id === like_id[i]) {
            item["like_id"] = like_id[i];
          }
        }
        item.duration = TimeFilter(item.duration);
      });
      console.log(MusicList.value);
      //  console.log(MusicList.value.duration);
      // useMusicmun.$state.musicList = MusicList.value;
    })
    .catch((error: any) => {
      console.log(error);
    });
};

setTimeout(() => {
  getMusicList();
}, 200);


// /musicList/love
const like = (index: number, value: any) => {
  // console.log(555555, index, value.id);
  like_id.push(value.id);
  console.log(like_id);
  UpdateLikes(like_id);
  getMusicList(); //重新获取
};
const islike = (index: number, value: any) => {
  console.log(index, value);
  let like_id2: number[] = [];
  like_id2 = like_id;
  like_id = [];
  like_id2.forEach((element) => {
    // console.log(2222, element, value.id);
    if (element != value.id) {
      like_id.push(element);
    }
  });
  // console.log(3, like_id);
  UpdateLikes(like_id);
  getMusicList(); //重新获取
};

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
   useMusicmun.$state.musicList = MusicList.value; //主要控制列表
  useMusicmun.$state.musicIndex = index;
  //   返回当前歌曲 的src
  useMusicmun.changeMusicSrc(MusicList.value[index].mp3src)
  console.log(useMusicmun.$state.musicSrc)
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
