<template>
  <!-- //上传文件 -->
  <div class="upload">
    <Upload
      format="只能上传.jpg格式"
      title="音乐的封面"
      limit=".jpg"
      @on-success="getcoverimg"
    ></Upload>
    <Upload
      format="只能上传.lrc格式"
      title="音乐的歌词"
      limit=".lrc"
      @on-success="getlrcpath"
    ></Upload>
    <Upload
      format="只能上传.mp3格式"
      title="MP3 音乐"
      limit=".mp3"
      @on-success="getmp3src"
    ></Upload>
  </div>
  <!-- 填写作者等名字信息 -->
  <el-form
    ref="ruleFormRef"
    :label-position="labelPosition"
    label-width="100px"
    :model="formLabelAlign"
    style="max-width: 98%"
    :rules="rules"
  >
    <div class="form">
      <el-form-item label="音乐名称" prop="musicname">
        <el-input v-model="formLabelAlign.musicname" />
      </el-form-item>
      <el-form-item label="歌手 " prop="reltime">
        <el-input v-model="formLabelAlign.reltime" />
      </el-form-item>
      <el-form-item label="音乐作词">
        <el-input v-model="formLabelAlign.songwriter" />
      </el-form-item>
      <el-form-item label="音乐作曲 ">
        <el-input v-model="formLabelAlign.composer" />
      </el-form-item>
    </div>
    <!-- //类型选择 -->
    <!-- <Select :data="language" :value="languagevalue"  dataValue="language" dataId="id" @changes="change" ></Select> -->
    类型选择
    <div class="type">
      <el-select
        v-model="languagevalue"
        class="m-2"
        placeholder="语言类型"
        @change="change"
        :clearable="true"
      >
        <el-option v-for="item in language"  :label="item.language" :value="item.id" />
      </el-select>
      <el-select v-model="scenevalue"   :clearable="true" class="m-2" placeholder="场景类型" @change="change">
        <el-option v-for="item in scene"  :label="item.scene" :value="item.id" />
      </el-select>
      <el-select v-model="emotionvalue"   :clearable="true" class="m-2" placeholder="风格类型" @change="change">
        <el-option v-for="item in emotion"  :label="item.emotion" :value="item.id" />
      </el-select>
      <el-select v-model="topicvalue"   :clearable="true" class="m-2" placeholder="其他类型" @change="change">
        <el-option v-for="item in topic"  :label="item.topic" :value="item.id" />
      </el-select>
    </div>
    <el-form-item class="button">
      <el-button type="primary" icon @click="upload(ruleFormRef)">上传文件</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup lang="ts">
import Upload from "../../../compoenet/upload.vue";
import type { FormInstance } from "element-plus";
import { reactive, ref } from "vue";
import { get, posts } from "@/utils/api";
import { ElMessage } from 'element-plus'

const ruleFormRef = ref<FormInstance>();
const labelPosition = ref("right");
const formLabelAlign = reactive({
  musicname: "",
  songwriter: "",
  composer: "",
  reltime: "",
});
//提示
const open1 = (msg:string) => {
  ElMessage({
    duration: 3000,
    showClose: true,
    message: msg,
  })
}
const rules = reactive({
  musicname: [{ required: true, message: "请输入音乐名称", trigger: "blur" }],
  reltime: [{ required: true, message: "请输入音乐名称", trigger: "blur" }],
});
const value = ref();

/************************************************************************ */
//封面图片
let coverimg = ref("");
//音乐src
let duration = ref("");
let mp3src = ref("");
//音乐歌词
let lrcid = ref("");
let lrcpath = ref("");
/***获取封面图片，音乐src，音乐歌词 */
const getcoverimg = (res: any) => {
  coverimg.value = res.data.coverimg;
  console.log(2, coverimg.value);
};
const getmp3src = (res: any) => {
  mp3src.value = res.data.mp3src;
  duration.value = res.data.duration;
  console.log(3, mp3src.value, duration.value);
};
const getlrcpath = (res: any) => {
  lrcid.value = res.data.lrcid;
  lrcpath.value = res.data.lrcpath;
  console.log(4, lrcpath.value, lrcid.value);
};
/***获取标签类型列表 */
let language = ref("") as any;
let scene = ref("") as any;
let emotion = ref("") as any;
let topic = ref("") as any;
//双向绑定值
let languagevalue = ref("");
let scenevalue = ref("");
let emotionvalue = ref("");
let topicvalue = ref("");
const getLabel = () => {
  get("http://localhost:8089/musicTab/allmusicTab")
    .then((res: any) => {
      language.value = res.language;
      scene.value = res.scene;
      emotion.value = res.emotion;
      topic.value = res.topic;
      console.log(666666666, language.value, scene.value, emotion.value, topic.value);
    })
    .catch((error: any) => {});
};
getLabel();
const change = (id: number) => {
  console.log(id);
};
//点击butoon提交上传
const upload = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate((valid) => {
    if (valid) {
      // 上传
      posts("/musicList/addSongToAudit", {
        musicname: formLabelAlign.musicname,
        songwriter: formLabelAlign.songwriter,
        composer: formLabelAlign.composer,
        reltime: formLabelAlign.reltime,
        coverimg: coverimg.value,
        duration: duration.value,
        mp3src: mp3src.value,
        lrcid: lrcid.value,
        lrcpath: lrcpath.value,
        auditstatus: false,
        likestatus: false,
        userid: sessionStorage.getItem("userId"),
        languageid: languagevalue.value,
        sceneid: scenevalue.value,
        emotionid: emotionvalue.value,
        topicid: topicvalue.value,
      }).then((res:any)=>{
        console.log(res)
        if(res.code==400){
          open1('已有这首歌啦！！！')
        }else{
           open1(res.data+'-' +res.msg)
        }
  
      }).catch((error:any)=>{
        console.log(error)
      });
    } else {
      // 不符合rule就返回
      console.log("error submit!");
      return false;
    }
  });
};
</script>
<style scoped>
.upload {
  display: flex;
  justify-content: space-around;
}
.form {
  display: flex;
  justify-content: center;
  color: black;
}
.type {
  display: flex;
}
.button{
  position: relative;
 margin-left: -100px;
}
.el-button{
  margin: 10% auto;
 background-color: #626aef;
 border: 0;
}

</style>
