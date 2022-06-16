import { defineStore } from 'pinia'
import { ElMessage } from 'element-plus'
const open1 = () => {
  ElMessage({
    duration: 3000,
    showClose: true,
    message: '没有歌曲啦！已从头开始播放！',
  })
}
const open2 = () => {
  ElMessage({
    duration: 3000,
    showClose: true,
    message: '到顶啦！已从尾部开始播放！',
  })
}
//普通数据
export const useCounterStore = defineStore({
  id: 'counter',
  state: () => ({
    counter: 1,
    display: "display:block",
    PlayerDisplay: "display:none",
    isvisble: false,
    musicStyle: "display:block;color:#d3d3d3"
  }),
  getters: {
    doubleCount: (state) => state.counter * 2,
    isdisplay: (state) => {
      return state.display == "display:block" ? "display:none" : "display:block"
    }
  },
  actions: {
    increment() {
      this.counter++
    },

  }
})
const usermincounter = useCounterStore()
//路由跳转 
export const useTokenStore = defineStore({
  id: 'Token',
  state: () => ({
    counter: 1,
    Token: "",
  }),
  getters: {
    doubleCount: (state) => state.counter * 2,
  },
  actions: {
    increment() {
      this.counter++
    },

  }
})
//音乐列表
export const useMusicStore = defineStore({
  id: 'MusicStore',
  state: () => ({
    counter: 1,
    musicList: [] as any,
    musicSrc: "" as string,
    musicIndex: -1 as number,
    visble: false,
  }),
  getters: {
    doubleCount: (state) => state.counter * 2,
    playersrc: (state) => { return state.musicIndex != -1 ? state.musicList[state.musicIndex].songurl : state.musicSrc }
  },
  actions: {
    increment() {
      this.counter++
    },
    /**列表循环 */
    ListCycle() {
      if (this.musicIndex < this.musicList.length - 1) {
        this.musicIndex++
        console.log(2222);
      } else {
        console.log(8888);
        this.musicIndex = 0
      }
    },
    /**单曲循环 */
    SingleCycle() {
      this.musicIndex = this.musicIndex
    },
    /**
     * 随机播放
     */
    RandomCirculation() {
      this.musicIndex = Math.floor((Math.random() * this.musicList.length))
      console.log(555555, this.musicIndex)

    },
    /**下一首 */
    NextSong() {
      if (this.musicIndex < this.musicList.length - 1) {
        this.musicIndex++
        console.log(2222);

      } else {
        this.musicIndex = 0
        console.log(8888);
        open1()

      }
    }
    ,
    /**上一首 */
    Last() {
if(this.musicIndex !=0){
  this.musicIndex--

}
else {
  this.musicIndex = this.musicList.length - 1
  console.log(8888);
  open2()

}
    }

  }

})


