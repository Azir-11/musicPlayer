<template>
    <!-- 设置下拉菜单 -->
    <el-dropdown trigger="click">
        <span class="el-dropdown-link">
            <el-icon class="el-icon--right" size="1.2em">
                <Tools />
            </el-icon>
        </span>
        <template #dropdown>
            <el-dropdown-menu>
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item>退出登录</el-dropdown-item>
            </el-dropdown-menu>
        </template>
    </el-dropdown>
    <!-- 头像 -->
    <div class="avatar"> <img src="@/assets/HomeCarousel/1.jpg" alt=""><p>陈若炯</p></div>

</template>

<script lang="ts" setup>

import { Tools } from '@element-plus/icons-vue'

</script>
<style scoped>
.example-showcase .el-dropdown-link {
    cursor: pointer;
    color: var(--el-color-primary);
    display: flex;
    align-items: center;
}

.el-dropdown {
    position: absolute;
    width: 100%;
    right: 20%;
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
    height: 50%;
    right: 5%;
    bottom: 0;
   
}

.avatar img {
    float: right;
    width: 26%;
    height: 100%;
    margin-right: 20%;
    border-radius: 100%;
    cursor: pointer;
    
}
.avatar p {
    float: right;
    margin-right: 5%;
   margin-top: 10px;
   font-size: 8px;
    cursor: pointer;
    font-weight: 600;
}
</style>