<template>
  <h1>Music</h1>
  <!-- 菜单 -->
  <ul class="menu" ref="V1">
    <li @click="route('/', 'display:block')">
      <el-icon style="color: #fff; background-color: red">
        <VideoPlay /> </el-icon
      >首页
    </li>
    <li @click="route('/music', 'display:none')">
      <el-icon>
        <Headset /> </el-icon
      >音乐
    </li>
    <li @click="route('/singer', 'display:none')">
      <el-icon>
        <User /> </el-icon
      >歌手
    </li>
    <li @click="route('/upload', 'display:none')">
      <el-icon>
        <Eleme /> </el-icon
      >上传
    </li>



      <li v-if="role=='1'" @click="route('/audit', 'display:none')">
        <el-icon><View /></el-icon>审核
      </li>
  </ul>
  <!-- 3个球 -->
  <ul class="ball">
    <li></li>
    <li></li>
    <li></li>
  </ul>
</template>

<script setup lang="ts">
import { VideoPlay, Headset, Eleme, User, View } from "@element-plus/icons-vue";
import { onMounted, ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import { useCounterStore } from "@/stores/counter";
import { useTokenStore } from "@/stores/counter";
const useminToken = useTokenStore();
// 数据共享
const useminStroe = useCounterStore();
const router = useRouter();
const Route = useRoute();
let role = sessionStorage.getItem("role");

// dom操作
const V1 = ref();
onMounted(() => {
  // 刷新回到首页
  route("/", "display:block");
  const obj_li = V1.value.children;
  console.log(obj_li);
  const obj_icon: any[] = [];
  for (let i = 0; i < obj_li.length; i++) {
    obj_icon.push(obj_li[i].childNodes[0]);
    obj_li[i].onclick = function () {
      obj_icon.forEach((element) => {
        element.style = "";
        obj_icon[i].style = "color: #fff;background-color: red";
      });
    };
  }
});

const route = (path: string, data: string) => {
  router.push({
    path: path,
    query: {
      data: data,
    },
  });
};
</script>

<style scoped>
h1 {
  font-size: 25px;
  font-weight: 600;
  font-family: Sans-serif;
  text-align: center;
  margin-top: 50px;
}

.menu {
  margin-top: 50px;
}

.menu li {
  line-height: 40px;
  font-weight: 600;
  margin-left: -10px;
}

.menu li:hover {
  cursor: pointer;
  color: #e85353;
}

.el-icon {
  width: 20px;
  height: 20px;
  margin-right: 10px;
  border-radius: 100%;
  vertical-align: middle;
  text-align: center;
}

.ball {
  position: relative;
  top: 22%;
}

.ball li {
  width: 30px;
  height: 30px;
  border-radius: 100%;
  margin-bottom: 30%;
}

.ball li:nth-child(1) {
  background-color: #c4487a;
}

.ball li:nth-child(2) {
  background-color: #57b9c6;
}

.ball li:nth-child(3) {
  background-color: #ec4743;
}
</style>
