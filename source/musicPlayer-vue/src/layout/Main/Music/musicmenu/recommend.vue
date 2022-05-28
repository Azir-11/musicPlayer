
<template>
        <!-- 推荐页 -->
        <el-table :data="tableData" height="450" style="width: 100%"  lazy="true" row-style="background-color:#ebeaf9" stripe="true" >
                <el-table-column type="index" align="center " />
                <el-table-column align="left ">
                        <template #default="scope">
                                <el-icon  @click="like(scope.$index, scope.row)" class="Eleme" ><Eleme /></el-icon>
                                <!-- <el-icon  class="Eleme"  @click="islike(scope.$index, scope.row)" :style="data[scope.$index]?'display:block':'display:none'"><ElemeFilled /></el-icon> -->
                        </template>
                </el-table-column>
                 <el-table-column align="left">
                        <template #default="scope">
                                <el-button size="small" color="#626aef" @click="player(scope.$index, scope.row)"  plain>播放</el-button>
                        </template>
                </el-table-column>
                <el-table-column prop="date" label="音乐标题" width="180" />
                <el-table-column prop="name" label="歌手" width="180" />
                <el-table-column prop="address" label="专辑" width="180" />  
        </el-table>
</template>

<script lang="ts" setup>
import { Eleme ,ElemeFilled} from '@element-plus/icons-vue';
import {ref} from 'vue'
let num=ref()
let persondata=ref( [{id:3,like:false}])
let data=ref([2,3,5])
console.log(persondata.value[0].like) 
const like = (index: number, value: any) => {
        console.log(index, value)
       data.value[index] =true as never
}
const islike = (index: number, value: any) => {
        console.log(index, value)
       data.value[index] =false as never
}
const isvisble=( value: any)=>{

}
const player=(index: any, value: any)=>{
  console.log(index, value)
  num.value=index
}
const tableData = ref([
        {
                date: false,
                name: '1',
                address: 'No. 189, Grove St, Los Angeles',
        },
        {
                date: false,
                name: '2',
                address: 'No. 189, Grove St, Los Angeles',
        },
        {
                date: false,
                name: '4',
                address: 'No. 189, Grove St, Los Angeles',
        },
        {
                date: false,
                name: '6',
                address: 'No. 189, Grove St, Los Angeles',
        },
        {
                date: false,
                name: '7',
                address: 'No. 189, Grove St, Los Angeles',
        },
        {
                date: false,
                name: '8',
                address: 'No. 189, Grove St, Los Angeles',
        },
        {
                date: false,
                name: '9',
                address: 'No. 189, Grove St, Los Angeles',
        },
        {
                date: false,
                name: '10',
                address: 'No. 189, Grove St, Los Angeles',
        },
        {
                date: false,
                name: '11',
                address: 'No. 189, Grove St, Los Angeles',
        },
        {
                date: false,
                name: '12',
                address: 'No. 189, Grove St, Los Angeles',
        },
        {
                date: false,
                name: '13',
                address: 'No. 189, Grove St, Los Angeles',
        },


])
</script>
<style scoped>
.Eleme{
        cursor: pointer !important;
}

</style>
