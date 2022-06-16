<template>
  <!-- 审核页 -->

  <el-table
    :data="ReviewSong"
    style="width: 100%"
    max-height="540"
    lazy="true"
    row-style="background-color:#ebeaf9"
    stripe="true"
    :highlight-current-row="false"
  >
    <el-table-column fixed prop="musicname" label="音乐名称" width="150" />
    <el-table-column prop="reltime" label="歌手" width="120" />
    <el-table-column prop="songwriter" label="作词" width="120" />
    <el-table-column prop="composer" label="作曲" width="120" />
    <el-table-column label="封面" width="120" style="z-index: 4 !important">
      <template #default="scope">
        <div class="demo-image__preview">
          <el-image
            style="width: 50px; height: 50px"
            :src="'http://localhost/' + ReviewSong[scope.$index].coverimg"
            :preview-src-list="srcList"
            :hide-on-click-modal="true"
            :initial-index="scope.$index"
          />
        </div>
      </template>
    </el-table-column>
    <el-table-column
      class="lrc"
      prop="lrc.lrc"
      label="歌词"
      width="120"
      :show-overflow-tooltip="true"
    />

    <el-table-column prop="language.language" label="语言" width="120" />
    <el-table-column prop="emotion.emotion" label="情感" width="120" />
    <el-table-column prop="scene.scene" label="场景" width="120" />
    <el-table-column prop="topic.topic" label="主题" width="120" />
    <el-table-column prop="uploadtime" label="上传时间" width="120" />
    <el-table-column fixed="right" label="审核" width="120">
      <template #default="scope">
        <el-button
          link
          type="primary"
          size="small"
          @click.prevent="ispass(scope.$index, true)"
        >
          通过
        </el-button>
        <el-button
          link
          type="primary"
          size="small"
          @click.prevent="ispass(scope.$index, false)"
        >
          不通过
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup lang="ts">
import { get, posts } from "@/utils/api";
import { reactive, ref } from "vue";
//放大预览
let srcList = ref([]) as any;
//审核歌曲的数据
let ReviewSong = ref("") as any;

// 获取审核歌曲的数据
const getReviewSong = () => {
  get("audit/findAll")
    .then((res: any) => {
      if (res != "权限认证失败") {
        ReviewSong.value = res.data;
        console.log(222222222, ReviewSong.value);
        for (let i = 0; i < ReviewSong.value.length; i++) {
          srcList.value.push("http://localhost/" + ReviewSong.value[i].coverimg);
        }
      } else {
        alert(res + "-已过期请重新登陆");
      }
    })
    .catch((error: any) => {
      console.log(error);
    });
};

getReviewSong();
// 解决点击发大图片的bug
let Visble = ref(false);
const isvisble = (val: boolean) => {
  Visble.value = val;
};
// 是否通过
const ispass = (index: number, ispass: boolean) => {};
</script>

<style scoped>
.demo-image__error .image-slot {
  font-size: 30px;
}
.demo-image__error .image-slot .el-icon {
  font-size: 30px;
}
.demo-image__error .el-image {
  width: 30% !important;
  height: 200px;
}
</style>
