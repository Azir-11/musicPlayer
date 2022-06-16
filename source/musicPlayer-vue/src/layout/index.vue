<template>

    <!-- 版心 -->
    <el-container class="common-layout">

        <el-container>
            <el-aside width="130px">
                <Aside></Aside>
            </el-aside>
            <el-main style="padding: 0;">
                <!-- 右边头部 -->
                <el-container class="header">
                    <Header></Header>
                </el-container>
                <!-- main -->
                <RouterView></RouterView>
                <!-- 单独拿出来因为跳转路由这个音乐会停止 -->
                <div class="Home" :style="(route.query.data as string)">
                    <Home></Home>
                </div>
            </el-main>
            <!-- 三角形 -->
            <div class="triangle">
            </div>
        </el-container>
        <!-- 底部播放 -->
        <el-footer class="Player" :style="route.query.data == 'display:block' ? 'display:none' : 'display:block'"><Footer></Footer>
        </el-footer>
    </el-container>
</template>
<script lang="ts" setup>
import Aside from './Aside/Aside.vue';
import Home from './Main/Home/Home.vue';
import { useCounterStore } from '@/stores/counter';
import { useRoute,useRouter} from 'vue-router';
import { onMounted, ref } from 'vue';
import Header from './Header/Header.vue';
import Footer from './Footer/footer.vue';

// 数据共享,控制display

const route = useRoute()

</script>
<style scoped>
.common-layout {
    position: absolute;
    width: 1000px;
    height: 680px;
    left: 50%;
    top: 30px;
    transform: translateX(-50%);
    border-radius: 27px;
    background-color: #ebeaf9;
}

.triangle {
    position: absolute;
    width: 13px;
    height: 50px;
    margin-top: 16%;
    background-color: #ebeaf9;
    border-left: 13px solid #c7c9e2;
    border-top: 23px solid #ebeaf9;
    border-bottom: 24px solid #ebeaf9;
}

.el-aside {
    border: 2px solid black;
}

.Home {
    position: absolute;
    z-index: 999;
    width: 100%;
    height: 90%;
    background-color: #ebeaf9;

}

.Player {
    width: 100%;
    height: 100%;
    position: absolute !important;
    width: 100%;
    height: 10%;
    bottom: 0;
    background-color: #f6f6f8;
    display:none;
}

.header {
    width: 100%;
    height: 10%;

}
</style>