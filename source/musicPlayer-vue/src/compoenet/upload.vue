<template>
  <el-upload
    class="upload-demo"
    :accept="limit"
    drag
    method="post"
    action="http://localhost:8089/multiUpload"
    multiple
    limit="1"
    :headers="headers"
    :on-success="success"
    :on-error="error"
    :on-exceed="exceed"
  >
    <el-icon class="el-icon--upload"><upload-filled /></el-icon>
    <div class="el-upload__text">
      请上传 <em>{{ title }}</em>
    </div>
    <template #tip>
      <div class="el-upload__tip">
        {{ format }}
      </div>
    </template>
  </el-upload>
  
</template>

<script setup lang="ts">
import { UploadFilled } from "@element-plus/icons-vue";
import { ElMessage } from 'element-plus'
const open1 = () => {
  ElMessage({
    duration: 3000,
    showClose: true,
    message: '只能上传一个文件！！！！！',
  })
}
const props = defineProps(["format", "title", "limit"]);
const emit = defineEmits(["onSuccess"]);
/**  @on-success 钩子
 *获取封面图片，音乐src，音乐歌词
 */
const success = (response: any) => {
  console.log(111, response);
  emit("onSuccess", response);
};
/**
 * @on-error钩子
 */
const error = (response: any) => {
  console.log(2222, response);
};
const exceed = (response: any) => {
  open1()
};
//设置token
const headers: Record<string, any> = {
  token: sessionStorage.getItem("Token"),
};
</script>

<style></style>
