<template>
  <!-- 搜索 -->
  <div class="el-autocomplete">
    <Inquire></Inquire>
  </div>

  <!-- 头像 -->

  <div class="avatar">
    <div class="sub-title">嘻嘻~</div>
       <el-image
      style="width: 40px; height: 40px"
    :preview-src-list="person"
      src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
      :initial-index="4"
      fit="cover"
    />
   
  </div>

    <!-- 设置下拉菜单 -->
  <el-dropdown trigger="click">
    <span class="el-dropdown-link">
      <el-icon class="el-icon--right" size="1.2em">
        <Tools />
      </el-icon>
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item :disabled="ToDisable">个人中心</el-dropdown-item>
        <el-dropdown-item @click="Login">{{ WhetherToLogIn() }}</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script lang="ts" setup>
import { Tools } from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import { ref } from "vue";
import Inquire from "../../compoenet/Inquire.vue";
const router = useRouter();
// 通过token 判断有无登录
let WhetherToLogIn = () => {
  return sessionStorage.getItem("Token") ? "退出登录" : "登录/注册";
};
//登录与退出
let Login = () => {
  sessionStorage.getItem("Token")
    ? sessionStorage.clear()
    : router.push({ path: "/login" });
  sessionStorage.getItem("Token") ? (ToDisable.value = false) : (ToDisable.value = true);
};
// 通过token 禁用个人中心
const ToDisable = ref();
let WhetherToDisable = () => {
  sessionStorage.getItem("Token") ? (ToDisable.value = false) : (ToDisable.value = true);
};
WhetherToDisable();
//获取个人数据
const person=[
`https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png`
]
</script>
<style scoped>
.el-autocomplete {
  position: relative;
  display: inline-block;
  left: 3%;
  top: 24%;
}
.example-showcase .el-dropdown-link {
  cursor: pointer;
  color: var(--el-color-primary);
  display: flex;
  align-items: center;
}

.el-dropdown {
  position: absolute;
  width: 100%;
  right: 5%;
  bottom: 10%;
  width: 1.5vw;
  height: 1.5vh;
}

.el-icon--right {
  cursor: pointer;
}

.avatar {
  position: absolute;
  width: 15%;
  height: 93%;
  
    right: 7%;

  text-align: center;
  bottom: 0;

}
.sub-title{
    font-weight: 600;
}
</style>
