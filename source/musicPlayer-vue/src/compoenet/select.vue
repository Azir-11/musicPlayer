<template>
  <el-select v-model="value" class="m-2" placeholder="Select" @change="Change">
    <el-option v-for="item in data" :label="dataValue" :value="item.id" />
  </el-select>
</template>
<script setup lang="ts">
const props = defineProps(["value", "data", "dataValue", "dataId"]);


const emit = defineEmits(["changes"]);
const Change = (val: number) => {
  emit("changes", val);
};
</script>

<style></style>
