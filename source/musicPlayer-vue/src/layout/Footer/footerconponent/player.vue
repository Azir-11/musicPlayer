<template>
  <!-- 底部播放功能 利用element ui-->

  <audio
    ref="audio"
    :src="'http://localhost/' + useMusicmin.playersrc"
    @canplay="getDuration"
    @timeupdate="updateTime"
    @pause="minPause"
    @waiting="minWaiting"
    @error="minError"
    @ended="minended"
    @loadedmetadata="mindurationchange"
  >
    <!-- <source src="@/../public/唯一.mp3" type="audio/mpeg"> -->
  </audio>
  <!-- 功能模块 -->
  <div class="TwoMajorModules">
    <div class="left">
      <div class="function">
        <el-icon class="hover" @click="useMusicmin.Last"><Back /></el-icon>
        <!-- <el-button color="#626aef" :icon="SortDown" @click="useMusicmin.Last" plain></el-button> -->
        <div style="height: 20.5px; margin: 0 20px">
          <el-icon
            @click="Videopause"
            :style="
              mincounter.$state.isvisble
                ? 'color:deeppink;display:block'
                : 'display: none'
            "
          >
            <VideoPause />
          </el-icon>
          <el-icon
            :style="
              mincounter.$state.isvisble ? 'display:none' : mincounter.$state.musicStyle
            "
            @click="Videoplay"
          >
            <VideoPlay />
          </el-icon>
        </div>
        <el-icon class="hover" @click=" useMusicmin.NextSong"><Right /></el-icon>
        <!-- <el-button color="#626aef" :icon="SortUp"  @click="useMusicmin.NextSong"  plain></el-button> -->
      </div>
      <!-- 进度条 -->
      <div class="Progress">
        <div class="slider-demo-block" @click="changeCurrentTime()" @mousemove="">
          <!-- 时间进度 -->
          <el-tag type="info">{{ TimeFilter(nowTime) }}</el-tag>
          <el-slider
            v-model="DynamicValue"
            :format-tooltip="formatTooltip"
            @change="changeCurrentTime"
            size="small"
          />
          <!-- 时间总数 -->
          <el-tag type="info">{{ TimeFilter(Time) }}</el-tag>
        </div>
      </div>
    </div>
    <div class="right">
      <!-- 单次循环，列表循环， 随机循环 -->
      <div class="cycle">
        <!-- 单次循环 -->
        <svg
          t="1654080612818"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="4962"
          width="20"
          height="20"
          @click="cycle"
          v-show="frequency == 2"
        >
          <path
            d="M928 476.8c-19.2 0-32 12.8-32 32v86.4c0 108.8-86.4 198.4-198.4 198.4H201.6l41.6-38.4c6.4-6.4 12.8-16 12.8-25.6 0-19.2-16-35.2-35.2-35.2-9.6 0-22.4 3.2-28.8 9.6l-108.8 99.2c-16 12.8-12.8 35.2 0 48l108.8 96c6.4 6.4 19.2 12.8 28.8 12.8 19.2 0 35.2-12.8 38.4-32 0-12.8-6.4-22.4-16-28.8l-48-44.8h499.2c147.2 0 265.6-118.4 265.6-259.2v-86.4c0-19.2-12.8-32-32-32zM96 556.8c19.2 0 32-12.8 32-32v-89.6c0-112 89.6-201.6 198.4-204.8h496l-41.6 38.4c-6.4 6.4-12.8 16-12.8 25.6 0 19.2 16 35.2 35.2 35.2 9.6 0 22.4-3.2 28.8-9.6l105.6-99.2c16-12.8 12.8-35.2 0-48l-108.8-96c-6.4-6.4-19.2-12.8-28.8-12.8-19.2 0-35.2 12.8-38.4 32 0 12.8 6.4 22.4 16 28.8l48 44.8H329.6C182.4 169.6 64 288 64 438.4v86.4c0 19.2 12.8 32 32 32z"
            p-id="4963"
          ></path>
          <path d="M544 672V352h-48L416 409.6l16 41.6 60.8-41.6V672z" p-id="4964"></path>
        </svg>
        <!-- 列表循环 -->
        <svg
          @click="cycle"
          v-show="frequency == 1"
          t="1654080876219"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="7028"
          width="20"
          height="20"
        >
          <path
            d="M694.4 854.4H195.2l48 44.8c9.6 6.4 16 16 16 28.8-3.2 19.2-19.2 32-38.4 32-9.6 0-22.4-6.4-28.8-12.8l-108.8-96c-12.8-12.8-16-35.2 0-48L192 704c6.4-6.4 19.2-9.6 28.8-9.6 19.2 0 35.2 16 35.2 35.2 0 9.6-6.4 19.2-12.8 25.6l-41.6 38.4h496c112 0 198.4-89.6 198.4-198.4v-86.4c0-19.2 12.8-32 32-32s32 12.8 32 32v86.4c0 140.8-118.4 259.2-265.6 259.2zM329.6 169.6h496l-48-44.8c-9.6-6.4-16-16-16-28.8 3.2-19.2 19.2-32 38.4-32 9.6 0 22.4 6.4 28.8 12.8l108.8 96c12.8 12.8 16 35.2 0 48L832 320c-6.4 6.4-19.2 9.6-28.8 9.6-19.2 0-35.2-16-35.2-35.2 0-9.6 6.4-19.2 12.8-25.6l41.6-38.4H326.4C217.6 233.6 128 323.2 128 435.2v89.6c0 19.2-12.8 32-32 32s-32-12.8-32-32v-86.4C64 288 182.4 169.6 329.6 169.6z"
            p-id="7029"
          ></path>
        </svg>
        <!-- 随机循环 -->
        <svg
          @click="cycle"
          v-show="frequency == 3"
          t="1654088681289"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="16627"
          width="20"
          height="20"
        >
          <path
            d="M768.863 325.733c-48.775 0-170.409 121.182-259.229 207.077-134.661 130.244-261.862 256.802-363.67 256.802h-62.62C57.75 789.612 37 768.828 37 743.223c0-25.604 20.749-46.387 46.342-46.387h62.622c64.335 0 194.592-129.518 299.256-230.745 127.382-123.198 237.392-233.135 323.643-233.135h59.769l-63.418-61.55c-18.113-18.088-18.142-45.665-0.062-63.788 18.08-18.128 47.422-18.164 65.535-0.07l142.708 142.495A46.306 46.306 0 0 1 987 282.83c0 12.308-4.893 24.06-13.604 32.759l-142.71 142.4c-9.043 9.032-20.89 13.328-32.735 13.328-11.875 0-23.749-4.977-32.799-14.05-18.08-18.121-18.05-48.358 0.062-66.445l63.417-65.09h-59.768z m61.824 238.627c-18.113-18.095-47.456-18.066-65.535 0.062-18.08 18.121-18.05 49.237 0.062 67.323l63.417 65.09h-59.768c-38.06 0-101.643-56.357-164.685-115.353-18.69-17.493-48.021-17.394-65.495 1.306-17.486 18.7-16.507 49.382 2.183 66.87 96.042 89.898 160.826 139.952 227.997 139.952h59.769l-63.418 61.549c-18.113 18.087-18.142 46.552-0.062 64.674 9.051 9.072 20.924 13.167 32.799 13.167 11.845 0 23.692-4.74 32.736-13.773l142.708-142.619c8.71-8.7 13.604-20.56 13.604-32.87a46.44 46.44 0 0 0-13.604-32.842L830.687 564.36zM83.342 325.733h62.622c48.84 0 121.646 62.22 179.612 114.893 8.88 8.065 20.026 12.923 31.147 12.923 12.604 0 25.172-4.677 34.315-14.75 17.208-18.954 15.8-49.839-3.146-67.055-90.646-82.365-166.477-138.787-241.928-138.787H83.342C57.749 232.957 37 253.743 37 279.346s20.75 46.387 46.342 46.387z"
            p-id="16628"
          ></path>
        </svg>
      </div>

      <div class="sound">
        <el-icon v-show="WhetherItIsMute == true" @click="sound"><Smoking /></el-icon>
        <el-icon v-show="WhetherItIsMute == false" @click="sound"><NoSmoking /></el-icon>
        <!-- 控制音量 -->
        <div class="Progress">
          <div class="slider-demo-block" @click="changeCurrentsound">
            <el-slider
              v-model="VolumeValue"
              :format-tooltip="formatTooltipvolume"
              @change="changeCurrentsound"
              size="small"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {
  SortDown,
  SortUp,
  VideoPause,
  VideoPlay,
  Smoking,
  NoSmoking,
} from "@element-plus/icons-vue";
import { ref, onBeforeUnmount, onMounted } from "vue";
import { useCounterStore, useMusicStore } from "@/stores/counter";
import { Back, Right } from "@element-plus/icons-vue";

let Funtionstyle = ref("display:block");
const mincounter = useCounterStore();
const useMusicmin = useMusicStore();
let music = ref(useMusicmin.$state.musicSrc);
onMounted(() => {
  audio.value.load();
});
// dom 操作

const audio = ref();
/**
 * 在canplay（浏览器可以开始播放该音视频）钩子函数回调中访问duration属性可获得（注：在dom挂载完直接获取duration会返回NaN）
 * @param
 * @return
 * audio.value.duration 音频总时常
 */
let Time = ref();
let volume = ref();
let src = "@/../public/那就晚安.mp3";
const getDuration = () => {
  // 总时长audion;
  Time.value = audio.value.duration;
};

// 获取播放当前时间
// @timeupdate事件在里面获取当前事件
let nowTime = ref();
let updateTime = (res: any) => {
  nowTime.value = audio.value.currentTime;
  /**
   *
   * audio.value.currentTime 是实时字节更新时间
   * 当音频当前时间改变后，进度条也要改变
   * */
  DynamicValue.value = (audio.value.currentTime / Time.value) * 100;
};

// 获取时间是秒数：过滤
// 将整数转换成 时：分：秒的格式
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

// 播放
// useMusicmin.$state.musicSrc= ""
const Videoplay = () => {
  // 如果src为空就点不了
  console.log(6666, useMusicmin.playersrc);
  if (useMusicmin.playersrc != "") {
    audio.value.play();
    mincounter.$state.isvisble = true;
    console.log(mincounter.$state.isvisble);
  } else {
    // color:deeppink;
    mincounter.$state.isvisble = false;
  }
};

// 暂停
const Videopause = () => {
  audio.value.pause();
  mincounter.$state.isvisble = false;
};

// 进度条格式化toolTip
/**
 *
 * DynamicValue是点击进度条动态值
 */

let DynamicValue = ref();
const formatTooltip = (val: number) => {
  val = (Time.value / 100) * val;
  return "进度条: " + TimeFilter(val);
};

// 拖动进度条，改变当前时间，index是进度条改变时的回调函数的参数0-100之间，需要换算成实际时间

let changeCurrentTime = () => {
  audio.value.currentTime = (DynamicValue.value / 100) * Time.value;
  console.log(audio.value.currentTime);
  console.log(3333, DynamicValue.value);
};
/***********************************************************右边模块******************************************** */
// 循环
let frequency = ref(1);
const cycle = () => {
  frequency.value++;
  console.log(frequency.value);
  if (frequency.value > 3) {
    frequency.value = 1;
  }
};
/*****
 * audio.value.volume是声音值只有[0,1]
 */
// 音量进度条值
let VolumeValue = ref(100);
// 声音控制
let WhetherItIsMute = ref(true);
const sound = () => {
  WhetherItIsMute.value = WhetherItIsMute.value ? false : true;
  //点击静音进度条跟着变
  WhetherItIsMute.value == false ? (audio.value.volume = 0) : (audio.value.volume = 1);
  WhetherItIsMute.value == false ? (VolumeValue.value = 0) : (VolumeValue.value = 100);
};
// 当前的提示音量
const formatTooltipvolume = (val: any) => {
  return val;
};
// 拖动进度条或点击控制音量
const changeCurrentsound = () => {
  // 获取音量
  audio.value.volume = VolumeValue.value / 100;
  // ==0 图标为静音状态
  audio.value.volume == 0
    ? (WhetherItIsMute.value = false)
    : (WhetherItIsMute.value = true);
};
/**
 * @pause 当媒体暂停时产生该事件
 *  playing 当媒体从因缓冲而引起的暂停和停止恢复到播放时产生该事件
 */
const minPause = () => {
  audio.value.playing = false;
};
/**
 * @waiting  当视频因缓冲下一帧而停止时产生该事件
 */
const minWaiting = (res: any) => {
  console.log(res);
};
/**
 * @error 当加载媒体发生错误时产生该事件
 */
const minError = (error: any) => {
  console.log(error);
};
/**
 * @ended 当前播放列表为结束时产生该事件
 */ const minended = (res: any) => {
  console.log(11111, frequency.value);
  switch (frequency.value) {
    case 1: {
      useMusicmin.ListCycle();
      setTimeout(() => {
        Videoplay();
      }, 500);
      console.log(99999, frequency.value);
      break;
    }
    case 2: {
      useMusicmin.SingleCycle();
      setTimeout(() => {
        Videoplay();
      }, 500);
      break;
    }

    case 3: {
      useMusicmin.RandomCirculation();
      break;
    }
  }
};
/**
 * @durationchange    当媒体的总时长改变时产生该事件
 */
const mindurationchange = () => {
  Videoplay();
};
</script>

<style scoped>
.TwoMajorModules {
  display: flex;
}
.left {
  flex: 8;
}
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
.slider-demo-block {
  display: flex;
}
.el-slider {
  padding: 0 10px;
}
.right {
  flex: 3;
  display: flex;

  justify-content: center;
  align-items: center;
}
.el-icon {
  font-size: 1.5em;
}
.cycle {
  margin-left: 8%;
}
.cycle svg {
  cursor: pointer;
}
.sound {
  display: flex;
  margin-left: 20%;
  flex: 1;
}
.sound .el-icon {
  cursor: pointer;
}
.Progress {
  flex: 1;
  padding-left: 10%;
}
.hover {
  width: 22px;
  height: 28;
}
.hover :hover {
  cursor: pointer;
  color: #626aef;
}
</style>
