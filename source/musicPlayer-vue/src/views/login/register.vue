<template>
  <!-- 注册页 -->
  <div class="login clearfix">
    <div class="login-wrap">
      <el-row type="flex" justify="center">
        <el-form
          ref="ruleFormRef"
          :model="user"
          status-icon
          label-width="120px"
          :rules="rules"
        >
          <div class="tool">
            <h3>注册</h3>
            <span class="shou" @click="forgetpas">已有账户密码/登录</span>
          </div>
          <hr />
          <el-form-item prop="name" label="姓名">
            <el-input v-model="user.name" placeholder="请输入姓名" clearable></el-input>
          </el-form-item>
          <el-form-item prop="sex" label="性别">
            <el-radio-group v-model="user.sex" class="ml-4">
              <el-radio label="男" size="large">男</el-radio>
              <el-radio label="女" size="large">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item prop="phone" label="电话号码">
            <el-input
              v-model.number="user.phone"
              type="text"
              placeholder="请输入电话号码"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item prop="username" label="用户账号">
            <el-input
              v-model="user.username"
              placeholder="请输入账号"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item prop="email" label="邮箱">
            <el-input v-model="user.email" placeholder="请输入邮箱" clearable />
          </el-form-item>
          <el-form-item prop="password" label="设置密码">
            <el-input
              v-model="user.password"
              show-password
              placeholder="请输入密码"
              clearable
            ></el-input>
          </el-form-item>
          <el-form-item label="二次输入" prop="checkPass" autocomplete="off">
            <el-input
              v-model="user.checkPass"
              type="password"
              autocomplete="off"
              clearable
            />
          </el-form-item>
          <el-form-item label="个人简介" prop="profile">
            <el-input v-model="user.profile" type="textarea" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon @click="doRegister(ruleFormRef)"
              >注册账号</el-button
            >
          </el-form-item>
        </el-form>
      </el-row>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue";
import type { FormInstance } from "element-plus";
import { useRouter } from "vue-router";
import { posts } from "@/utils/api";
import { onMounted } from "vue";
const router = useRouter();

const ruleFormRef = ref<FormInstance>();
const user = reactive({
  name:"",
  sex:"男",
  phone:"",
 username :"",
  email: "",
  password: "",
  checkPass: "",
  profile: '"☾只要是你腻歪，那歪腻一辈子"',
});
// 注册

//定义给rule
const validatePass = (rule: any, value: any, callback: any) => {
  if (value === "") {
    callback(new Error("请输入密码"));
  } else {
    if (user.checkPass !== "") {
      if (!ruleFormRef.value) return;
      ruleFormRef.value.validateField("checkPass", () => null);
    }
    callback();
  }
};
//定义给rule
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === "") {
    callback(new Error("请再次输入密码"));
  } else if (value !== user.password) {
    callback(new Error("2次输入不一致!"));
  } else {
    callback();
  }
};
//rule
const rules = reactive({
   name: [
    { required: true, message: "请输入用户名", trigger: "blur" },
    {min:3, max: 15, message: "姓名至少3不超过15位!", trigger: "blur" },
  ],
  username: [
    { required: true, message: "请输入用户名", trigger: "blur" },
    { max: 10, message: "用户名不超过10位!", trigger: "blur" },
  ],
  password: [
    { required: true, validator: validatePass, trigger: "blur" },
    { min: 6, max: 10, message: "密码⻓度⾄少6位但不超过10位!", trigger: "blur" },
  ],
  checkPass: [{ required: true, validator: validatePass2, trigger: "blur" }],
  email: [
    { required: true, message: "请输入电子邮箱", trigger: "blur" },
    {
      type: "email",
      min: 15,
      max: 20,
      message: "请输入正确的电子邮件地址",
      trigger: ["blur", "change"],
    },
    { min: 15, max: 20, message: "请输入正确的电子邮件地址", trigger: "blur" },
  ],
  profile :[
    { required: false,  trigger: "blur" },
    { max: 30, message: "简介不超过30位!", trigger: "blur" },
  ],
  phone:[
        { required: true,message: '请输入正确的电话号码'  },
        { type:"number", min:10000000000,
      max: 99999999999,message: '电话号码11位' },
      ]
});
  


//提交注册
const doRegister = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      // 注册
      posts("reg/register", {
        name:user.name,
        sex:user.sex,
        phone:user.phone,
        username: user.username,
        password: user.password,
        email: user.email,
        profile:user.profile
      }).then((res: any) => {
        console.log(res)
        alert(res.msg);
      });
    } else {
      // 不符合rule就返回
      console.log("error submit!");
      return false;
    }
  });      
};
// 已有账户密码
const forgetpas = () => {
  router.push({
    path: "/login",
  });
};
</script>
<style scoped>
.login {
  width: 100%;
  height: 740px;
  /* background: url("../assets/images/bg1.png") no-repeat; */
  background-size: cover;
  overflow: hidden;
}
.login-wrap {
  /* background: url("../assets/images/login_bg.png") no-repeat; */
  background-size: cover;
  width: 400px;
  height: 600px;
  margin: 64px auto;
  overflow: hidden;
  padding-top: 10px;
  line-height: 40px;
}
#password {
  margin-bottom: 5px;
}
h3 {
  color: #ffffff;
  font-size: 24px;
}
hr {
  background-color: #444;
  margin: 20px auto;
}
a {
  text-decoration: none;
  color: #aaa;
  font-size: 15px;
}
a:hover {
  color: coral;
}
.el-button {
  width: 80%;
  margin-left: -50px;
}
.tool {
  display: flex;
  justify-content: space-between;
  color: #606266;
}
.shou {
  cursor: pointer;
  color: #606266;
}
</style>
