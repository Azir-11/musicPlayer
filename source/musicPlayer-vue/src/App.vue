<template>
  <RouterView />
</template>

<script setup lang="ts">
import { RouterView } from 'vue-router'
</script>

<style>
@import '@/assets/base.css';
.el-message {
display: flex;
  width: 80%;
  top:50px !important;
  left: 50%;
  transform: translateX(-5%);
  align-items: center;
}

</style>
