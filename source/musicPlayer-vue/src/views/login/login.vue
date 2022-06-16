<template>
  <!-- 登录页 -->
  <div class="loginbody">
    <div class="logindata">
      <div class="logintext">
        <h2>Welcome</h2>
      </div>
      <div class="formdata">
        <el-form :model="form" :rules="rules">
          <el-form-item prop="username">
            <el-input
              v-model="form.username"
              placeholder="请输入账号"
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="form.password"
              placeholder="请输入密码"
              show-password
              @input="change($event)"
              clearable
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="tool">
        <div>
          <el-checkbox v-model="checked" @change="remenber">记住密码</el-checkbox>
        </div>
        <div>
          <span class="shou" @click="forgetpas">忘记密码？</span>
        </div>
      </div>
      <div class="butt">
        <el-button type="primary" @click="login">登录</el-button>
        <el-button class="shou" @click="register">注册</el-button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, onMounted, onUpdated, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import type { FormInstance, FormRules } from "element-plus";
import { get, posts } from "@/utils/api";
import { useTokenStore } from "@/stores/counter";

const router = useRouter();
const useminToken = useTokenStore();

/**
 * 问题：可能嵌套太深无法输入input
 * 解决：@change="change($event)"
 */

const change = (e: any) => {
  e.updated();
};

let form = reactive({
  username: "",
  password: "",
});

// 规矩
const rules = reactive<FormRules>({
  username: [
    { required: true, message: "请输入用户名", trigger: "blur" },
    { max: 10, message: "不能大于10个字符", trigger: "blur" },
  ],
  password: [
    { required: true, message: "请输入密码", trigger: "blur" },
    { max: 10, message: "不能大于10个字符", trigger: "blur" },
  ],
});
//记住密码checked: false,
let checked = ref(false);
const remenber = (data: any) => {
  checked.value = data;
  if (checked.value) {
    localStorage.setItem("news", JSON.stringify(form));
   
  } else {
    localStorage.removeItem("news");
  }
};
//如果本地存储有form调用
if (localStorage.getItem("news")) {
form = JSON.parse(localStorage.getItem("news") as string);
console.log(form.password);
checked.value = true;
}


// 忘记密码
const forgetpas = () => {
  alert("未开放");
};
// 登录
const login = () => {
  posts("reg/login", {
    'username': form.username,
    'password': form.password,
  })
    .then((res: any) => {
      if ((res.code = 200)) {
        console.log(res)
        //权限判定是否显示审核为1
      sessionStorage.setItem("role", res.data.role);
        alert(res.msg);
        sessionStorage.setItem("Token", res.data.token);
          sessionStorage.setItem("userId", res.data.id);
        console.log(2222222,  res.data);
        router.push({
          path: "/home",
          query:{role:res.data.role}
        });
        
      } else {
        alert(res.msg);
      }
    })
    .catch((error) => {
      console.log(error);
    });
};
//注册
const register = () => {
  router.push({ path: "/register" });
};
</script>

<style scoped>
.loginbody {
  width: 100%;
  height: 100%;
  min-width: 1000px;
  /* background-image: url("../assets/login2.jpg"); */
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
  background-repeat: no-repeat;
  position: fixed;
  line-height: 100%;
  padding-top: 150px;
}

.logintext {
  margin-bottom: 20px;
  line-height: 50px;
  text-align: center;
  font-size: 30px;
  font-weight: bolder;
  color: white;
  text-shadow: 2px 2px 4px #000000;
}

.logindata {
  width: 400px;
  height: 300px;
  transform: translate(-50%);
  margin-left: 50%;
}

.tool {
  display: flex;
  justify-content: space-between;
  color: #606266;
}

.butt {
  margin-top: 10px;
  text-align: center;
}

.shou {
  cursor: pointer;
  color: #606266;
}

/*ui*/
/* /deep/ .el-form-item__label {
  font-weight: bolder;
  font-size: 15px;
  text-align: left;
}

/deep/ .el-button {
  width: 100%;
  margin-bottom: 10px;

} */
</style>
