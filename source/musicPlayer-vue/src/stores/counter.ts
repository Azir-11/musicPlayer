import { defineStore } from 'pinia'
import { ElMessage } from 'element-plus'
import { get } from '@/utils/api'

const open1 = (val: string) => {
  ElMessage({
    duration: 3000,
    showClose: true,
    message: val,
  })
}
//二维数组解析为1维数组
function flatten(arr: any) {
  return [].concat(...arr.map((x: any) => (Array.isArray(x) ? flatten(x) : x)));
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
    authority: 0,
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








//歌词
export const useLyrics = defineStore({
  id: 'AuseLyrics',
  state: () => ({
    counter: 1,
    Lyr: null as any,
    musicLrc: [] as Array<Object>,//音乐歌词,源码
    newmusicLrc: [] as any,//音乐歌词,解析过的
    idx:0,
    lyricIndex: -1,// 当前高亮歌词的索引，与div标签里的index进行比较，判断是否高亮
    lrc1: [] as Array<Object>,//key 二维
    lrc2: [] as Array<Object>,//value二维
    musicLrcA: [] as Array<Object>,//key解析后一维
    musicLrcB: [] as Array<Object> //value解析后一维
  }),
  getters: {
    doubleCount: (state) => state.counter * 2,
    analyze: (state) =>state.newmusicLrc,
  },
  actions: {
    increment() {
      this.counter++
    },
    //清空push后的数组换歌就不会叠加
    Initialization() {
      this.lrc1 = []
      this.lrc2 = []
      this.musicLrcA = []
      this.musicLrcB = []
      this.newmusicLrc=[]
    },
    LyricsTraversal() {
      this.musicLrc.forEach((element) => {
        // console.log(8, Object.keys(element));
        // console.log(9, Object.values(element));
        this.lrc1.push(Object.keys(element));//获取key
        this.lrc2.push(Object.values(element));//获取value
      });
      /** 
       * flatten是二维数组解析
       * **/
      // console.log(2222, flatten(this.lrc1));
      //  console.log(2222, flatten(this.lrc2));
      //让原有的清空push 解析过的
      flatten(this.lrc1).forEach((element: any) => {

        let currTime = (parseInt(element.slice(0, 2)) * 60) + parseFloat(element.slice(3, 9)) //换成秒数
        // console.log( 111,currTime);
        this.musicLrcA.push({ 'time': currTime })
      });

      flatten(this.lrc2).forEach((element: any) => {
        this.musicLrcB.push({ 'lyric': element })
      });

      for (let i = 0; i < this.musicLrc.length; i++) {
        this.newmusicLrc.push(Object.assign(this.musicLrcA[i], this.musicLrcB[i]))
      }
      console.log(this.newmusicLrc, 3333333)
    }
  }
})
const useLyricsmin = useLyrics()
let getlyrics = (id: any) => {
  useLyricsmin.$state.idx=0
  useLyricsmin.$state.lyricIndex=-1
  get("musicList/findAll?id=" + id + "")
    .then((res: any) => {
      console.log(333, res.data.lrc);
      useLyricsmin.$state.musicLrc = res.data.lrc;
      useLyricsmin.Initialization()//换个清空上一首push的值
      //解析歌词时间与值
      useLyricsmin.LyricsTraversal()

    })
    .catch((error: any) => {
      console.log(error);
    });
}

//音乐列表
export const useMusicStore = defineStore({
  id: 'MusicStore',
  state: () => ({
    counter: 1,
    audio:null as any,
    musicList: [] as any,//音乐列表
    musicSrc: "" as string,//音乐路径
    musicIndex: -1 as number,//音乐下标
    visble: false,//播放样式

  }),
  getters: {
    doubleCount: (state) => state.counter * 2,
    playersrc: (state) => {
      console.log( state.musicSrc)
       return state.musicIndex != -1 ? state.musicList[state.musicIndex].mp3src : state.musicSrc }
  },
  actions: {
    increment() {
      this.counter++
    },
    /**列表循环 */
    ListCycle() {
      if (this.musicIndex < this.musicList.length - 1) {
        this.musicIndex++
        getlyrics( this.musicList[this.musicIndex].id)
      } else {
        console.log(8888);
        this.musicIndex = 0
        getlyrics( this.musicList[this.musicIndex].id)
      }
    },
    /**单曲循环 */
    SingleCycle() {
      this.musicIndex = this.musicIndex
    
      getlyrics( this.musicList[this.musicIndex].id)
    },
    changeMusicSrc(src:string)
    {
      this.musicSrc=src
    },
    /**
     * 随机播放
     */
    RandomCirculation() {
      this.musicIndex = Math.floor((Math.random() * this.musicList.length))
   
      getlyrics( this.musicList[this.musicIndex].id)

    },
    /**下一首 */
    NextSong() {
      if (this.musicIndex != -1) {
        if (this.musicIndex < this.musicList.length - 1) {
          this.musicIndex++
        
          getlyrics( this.musicList[this.musicIndex].id)
        } else {
          this.musicIndex = 0
   
          getlyrics( this.musicList[this.musicIndex].id)
          console.log(8888);
          open1('没有歌曲啦！已从头开始播放！')

        }
      } else {
        open1('请先选歌曲！！')
      }
    }
    ,
    /**上一首 */
    Last() {
      if (this.musicIndex != -1) {
        if (this.musicIndex != 0) {
          this.musicIndex--
   
          getlyrics( this.musicList[this.musicIndex].id)
        }
        else {
          this.musicIndex = this.musicList.length-1
          getlyrics( this.musicList[this.musicIndex].id)
          open1('到顶啦！已从尾部开始播放')

        }
      } else {
        open1('请先选歌曲！！')
      }
    }

  }

})


