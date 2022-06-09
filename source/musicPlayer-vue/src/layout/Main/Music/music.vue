<template>
        <!-- 音乐页 -->
        <el-container>
                <el-header><HeaderMenu></HeaderMenu></el-header>
                <el-main> <RouterView></RouterView></el-main>
        </el-container>
</template>
<script setup lang='ts'>
import HeaderMenu from "./musicComponent/headerMenu.vue";
import { useRouter } from "vue-router";

</script>
        
<style scoped>
.common-layout {
        position: absolute;
}

.el-container {
        width: 100%;
        height: auto;
        border: 1px solid;
}

.el-header {border: 1px solid;}
</style>

