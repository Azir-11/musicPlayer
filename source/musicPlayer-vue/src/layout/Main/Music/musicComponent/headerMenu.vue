<template>
    <ul>
        <li v-for="(item,index) in data">
            <p   :class="num==index?'change':''" @click="changes(index,item.src)" >{{item.title}}</p>
        </li>
       
    </ul>
</template>
    
<script setup lang='ts'>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const data=[{title:'推荐',src:'/music/recommend'},{title:'我的喜欢',src:'/music/like'}]
const router=useRouter()
let num=ref(0)
// 封装路由
const route = (path: string, data: string, ) => {
    router.push({
        path: path,
        query: {
            data: data
        }
    })
}
const changes=(index:number, src:string)=>{
    num.value=index 
    route(src,'display:none')
}


</script >
    
<style scoped>
ul {
    position: relative;
    top: 50%;
    transform: translateY(-50%);
    display: flex;
   
    align-items: center;
}
ul li{
margin-right: 5%;
cursor: pointer;
}
ul li p{
    font-weight: 500;
   color: #383f47;
}
ul li p:hover{
    color: #000;
    
}
.change{
    border-bottom: 2px solid red;
    border-left:30px solid #ebeaf9 ;
    font-size: 1.5em;
    font-weight: 700;
    color: #1e1e1e;
}

</style>