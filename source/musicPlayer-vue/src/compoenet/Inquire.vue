<template>
  <el-autocomplete
    v-model="state"
    :fetch-suggestions="querySearch"
    popper-class="my-autocomplete"
    placeholder="Please input"
    @select="handleSelect(state)"
    clearable
  >
    <template #suffix>
      <el-icon class="el-input__icon" @click="handleIconClick(state)">
        <edit />
      </el-icon>
    </template>
    <template #default="{ item }">
      <div class="value">{{ item.value }}</div>
      <!-- <span class="link">{{ item.link }}</span> -->
    </template>
  </el-autocomplete>
</template>

<script lang="ts" setup>
import { onMounted, ref } from "vue";
import { Edit } from "@element-plus/icons-vue";
import axios from "axios";

/**
 * porps:
 *
 * 1.获取所有用户名  allUser
 * 2.选择事件，handleSelect
 * 3.点击确定事件 handleIconClsacick
 */
const props = defineProps(["allUser", "handleSelect", "handleIconClick"]);

interface LinkItem {
  value: string;
  link: string;
}

const state = ref("");
const links = ref<LinkItem[]>([]);

const querySearch = (queryString: string, cb: any) => {
  const results = queryString
    ? links.value.filter(createFilter(queryString))
    : links.value;

  // call callback function to return suggestion objects
  cb(results);
};
const createFilter = (queryString: any) => {
  return (restaurant: any) => {
    return restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0;
  };
};
//获取所有用户名
// const allUser = ref([])
// const getUsername = () => {
//     axios.post('http://localhost:8080/findusers').then(res => {
//         console.log(999, res.data.data)
//         const userobj = ref({ value: '' })
//         res.data.data.forEach(element => {
//             userobj.value.value = element.username
//             allUser.value.push(JSON.parse(JSON.stringify(userobj.value)))
//             console.log(userobj.value)
//         });
//     })
//     console.log(666, allUser.value)
// }
// getUsername()
const loadAll = () => {
  return props.allUser;
  //  [
  //     { value: 'vue', link: 'https://github.com/vuejs/vue' },
  //     { value: 'element', link: 'https://github.com/ElemeFE/element' },
  //     { value: 'cooking', link: 'https://github.com/ElemeFE/cooking' },
  //     { value: 'mint-ui', link: 'https://github.com/ElemeFE/mint-ui' },
  //     { value: 'vuex', link: 'https://github.com/vuejs/vuex' },
  //     { value: 'vue-router', link: 'https://github.com/vuejs/vue-router' },
  //     { value: 'babel', link: 'https://github.com/babel/babel' },
  // ]
};
// const handleSelect = (item: string) => {
//     axios.post(`http://localhost:8080/findbyUsername?username=${item}&pageNum=1&pageSize=2`).then(res => {
//         if (res.data.code = 200) {
//             useminStore.$state.minUserList = res.data.data.data
//             useminStore.$state.mintotal = res.data.data.total
//             console.log(666, res.data.data.total)
//         } else alert(res.data.msg)
//     }).catch(error => {
//         console.log(error)
//     })

// }

// const handleIconClick = (state:string) => {
//     if (state!="") {
//         axios.post(`http://localhost:8080/findbyUsername?username=${state}&pageNum=1&pageSize=10`).then(res => {
//             if (res.data.code = 200) {
//                 useminStore.$state.minUserList = res.data.data.data
//                 useminStore.$state.mintotal = res.data.data.total
//                 console.log(666, res.data.data.total)
//             } else alert(res.data.msg)
//         }).catch(error => {
//             console.log(error)
//         })
//     } else {
//         axios.post("http://localhost:8080/findusers?pageNum=1&pageSize=10").then(res => {
//             console.log(res)
//             useminStore.$state.minUserList = res.data.data
//             useminStore.$state.mintotal = res.data.total

//         })
//     }
// }

onMounted(() => {
  links.value = loadAll();
});
</script>

<style scoped>
.my-autocomplete li {
  line-height: normal;
  padding: 7px;
}

.my-autocomplete li .name {
  text-overflow: ellipsis;
  overflow: hidden;
}

.my-autocomplete li .addr {
  font-size: 12px;
  color: #b4b4b4;
}

.my-autocomplete li .highlighted .addr {
  color: #ddd;
}
</style>
