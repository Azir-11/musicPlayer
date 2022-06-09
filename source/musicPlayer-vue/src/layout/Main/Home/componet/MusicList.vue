<template>
<div id="list">
        <div id="MusicList">
          <ul :style="ulBottom" @wheel="Wheel">
            <div :class="[todo.tt==3?'PlayMusic':'StopMusic',]" v-for="(todo,index) in todos" class="BOX" :style="lastStyle(index) as any" >
              <div class="left">
                  <img :src="([todo.srcc] as any" alt="">
                  <div class="SongIntr">
                      <p class="SongName">{{todo.SongName}}</p>
                      <p class="PeoPleName">{{todo.PeoPleName}}</p>
                  </div>
              </div>
              <div class="right" v-if="todo.play" @click="CClick(index)">
                  
                  <a class="iconfont icon-caozuo-bofang-zanting"></a>     
              </div>
              <div class="right" v-if="!todo.play" @click="CClick(index)">
                  <a class="iconfont icon-bofang"></a>                 
              </div>
            </div> 
          </ul>
        </div>
</div>
    <!-- <div class="box">
      <nav class="nav">
        <router-link to="/">Home</router-link> |
        <router-link to="/about">About</router-link> |
        <router-link to="/say">Say</router-link> |
        <router-link to="/user">User</router-link>
      </nav>
      <router-view/> -->
</template>
<script lang="ts">
var WheelIndex = 0;  //防抖
var GoDrop = 0   //判断当前是否为底层
export default {
    name:'MusicList',
    data() {
      return {
        todos: [
           { SongName:'Eleanor Pena',PeoPleName:'Wade Warren',play:false,srcc:'/img/1.png',tt:1},
           { SongName:'Esther Howard',PeoPleName:'Guary',play:false,srcc:'/img/1.png',tt:2},
           { SongName:'Eleanor Pena',PeoPleName:'Wade Warren',play:false,srcc:'/img/1.png',tt:3},
           { SongName:'Estheras',PeoPleName:'Aritene McCoy',play:false,srcc:'/img/1.png',tt:4},
           { SongName:'Esther Howard',PeoPleName:'Guary',play:false,srcc:'/img/1.png',tt:5},
           { SongName:'Estheras',PeoPleName:'Aritene McCoy',play:false,srcc:'/img/1.png',tt:6},
           { SongName:'Eleanor Pena',PeoPleName:'Wade Warren',play:false,srcc:'/img/1.png',tt:7},
           { SongName:'Esther Howard',PeoPleName:'Guary',play:false,srcc:'/img/1.png',tt:null},
           { SongName:'Estheras',PeoPleName:'Aritene McCoy',play:false,srcc:'/img/1.png',tt:null},
           { SongName:'Eleanor Pena',PeoPleName:'Wade Warren',play:false,srcc:'/img/1.png',tt:null},
           { SongName:'Eleanor Pena',PeoPleName:'Wade Warren',play:false,srcc:'/img/1.png',tt:null},
           { SongName:'Esther Howard',PeoPleName:'Guary',play:false,srcc:'/img/1.png',tt:null},
           { SongName:'Estheras',PeoPleName:'Aritene McCoy',play:false,srcc:'/img/1.png',tt:null},
           { SongName:'Eleanor Pena',PeoPleName:'Wade Warren',play:false,srcc:'/img/1.png',tt:null}
      ],
      last5:
        {
          background: '#EAE9F9',
          boxShadow: '0px 4px 4px rgba(0, 0, 0, 0.1)',
          borderRadius: '9px',
          right: '10px'
        },
      last6:
        {
          background: '#E1E0F8',
          boxShadow: '0px 4px 4px rgba(0, 0, 0, 0.12)',
          borderRadius: '9px',
          right: '20px'
        },
      last7:
        {
          background: '#E2E1F3',
          boxShadow: '0px 4px 4px rgba(0, 0, 0, 0.05)',
          borderRadius: '9px',
          right: '15px'
        },
      ulBottom:{
          bottom:'0px'
        }
      }
    },
    methods: {
      // 点击播放
      CClick:function(index :any){
        if(index==GoDrop+2){
          this.todos[index].play = !this.todos[index].play
        }else{
          for(let i =0;i<this.todos.length;i++){
          if(this.todos[i].play==true){
            this.todos[i].play=false
          }
        }
          var todosin = this.todos[index]
          this.todos[index].play=true
          this.todos[GoDrop+2].play=false
          let indextt = this.todos[index].tt
          this.todos[index].tt=3
          this.todos[GoDrop+2].tt=indextt

          this.$set(this.todos, index, this.todos[GoDrop+2])
          this.$set(this.todos, GoDrop+2, todosin)
        }
      },
      lastStyle:function(index :any){
        if(this.todos[index].tt==5){
          return this.last5
        }else if(this.todos[index].tt==6){
            return this.last6
        }else if(this.todos[index].tt==7){
            return this.last7
        }else{
          return null;
        }
      },
      // 滚动
      Wheel:function(e:any){
        if(WheelIndex==0){
          //滚轮向上
          if (e.wheelDelta > 0 || e.detail < 0) {  
              console.log("向上")
              console.log(this.ulBottom)
              if(GoDrop==0){
                  return;
                }else{
                  WheelIndex=1;
                  GoDrop-=1;
                  this.SetClass(GoDrop)
                  if(GoDrop==1){
                    this.ulBottom.bottom = 90+"px"
                  }else{
                    this.ulBottom.bottom = 90+(GoDrop-1)*82+"px"
                  }
                }
            }
          //滚轮向下
          if (e.wheelDelta < 0 || e.detail > 0){   
              console.log("向下")
              console.log(this.ulBottom)
              if(GoDrop+7>this.todos.length-1){
                  return
              }
              WheelIndex=1;
              GoDrop+=1;
              this.SetClass(GoDrop)
              if(GoDrop==1){
                this.ulBottom.bottom = 90+"px"
              }else{
                this.ulBottom.bottom = 90+(GoDrop-1)*82+"px"
              }
            }
          //1秒过后可以滚动
            setTimeout(()=>{   
            WheelIndex=0
          },1000)
        }else{
          console.log("防抖")
        }
      },
            // tt修改
      SetClass:function(GoDrop :any){
        let NowIndex=GoDrop
        let num=1
        // 初始位
            for(let i = 0;i<this.todos.length;i++){
              if(NowIndex==i){
                console.log(1)
                NowIndex+=1
                this.todos[i].tt=num;
                num++
              }else{
                console.log(2)
                this.todos[i].tt=null
              }
            }
      }
    }
}
</script>
<style>
*{
  margin: 0;
  padding: 0;
}
/* icon图标 */
@font-face {
  font-family: "iconfont"; /* Project id 3437231 */
  src: url('//at.alicdn.com/t/font_3437231_oivse5xg8m.woff2?t=1653874194669') format('woff2'),
       url('//at.alicdn.com/t/font_3437231_oivse5xg8m.woff?t=1653874194669') format('woff'),
       url('//at.alicdn.com/t/font_3437231_oivse5xg8m.ttf?t=1653874194669') format('truetype');
}

.iconfont {
  font-family: "iconfont" !important;
  font-size: 16px;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.icon-bofang:before {
  content: "\e607";
}

.icon-caozuo-bofang-zanting:before {
  content: "\e6fe";
}
/* 结束 */
#app {
    width: 100vw;
    height: 100vh;
    background-color: #c7c9e2;
    justify-content: center;
    align-items: center;
    display: flex;
    overflow: hidden;
}
.box{
  width: 1200px;
  height: 700px;
  background-color: skyblue;
}
.Rect{
    width: 1094px;
    height: 810px;
    margin: auto auto;
    background: linear-gradient(118.73deg, #F4F4FD 2.47%, #DFDDF3 100%);
    border-radius: 81px;
    position: relative;
}
.Rect::before{
    content: '';
    position: absolute;
    width: 80px;
    height: 80px;
    left: -65px;
    top: 230px;
    background: #c7c9e2;
    border-radius: 25px;
    transform: rotate(150DEG);
}
.Rect::after{
    content: '';
    position: absolute;
    width: 80px;
    height: 80px;
    left: -65px;
    top: 240px;
    background: #c7c9e2;
    border-radius: 25px;
    transform: rotate(120DEG);
}
#MusicList{
    width: 420px;
    position: absolute;
    right: -65px;
    top: 150px;
    height: 610px;
    overflow: hidden;
}
#MusicList ul{
    width: 420px;
    height: 610px;
    transition: all .5s;
    position: relative;
    bottom: 0px;
}
.BOX {
    width: 284px;
    height: 62px;;
    background: #EDEBF6;
    box-shadow: 0px 4px 5px 1px rgba(0, 0, 0, 0.1);
    border-radius: 9px;
    margin: 20px 0;
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: relative;
    transition: all 1s;
    margin-left: 25px;
}
.StopMusic .left{
    margin-left: 10px;
    display: flex;
    align-items: center;
    height: 100%;
    justify-content: center;
}
.StopMusic .right{
    margin-right: 20px;
    cursor: pointer;
}
.StopMusic .left img{
    width: 40px;
    height: 40px;
    border-radius: 20px;
}
.StopMusic .right a{
    font-size: 25px;
}
.SongIntr{
    display: flex;
    flex-direction: column;
    height: 100%;
    justify-content: center;
    margin-left: 10px;
}
.SongIntr .SongName{
    font-size: 15px;
    font-weight: 700;
}
.SongIntr .PeoPleName{
    font-size: 10px;
    color: #7f7f7f;
}
/* 中间最大的 */
.PlayMusic{
    width: 368px;
    height: 88px;
    margin: 20px 0 20px 10px;
    background: #FAF9FE;
    box-shadow: 0px 4px 5px 3px rgba(134, 126, 126, 0.25);
    border-radius: 9px;
    transition: all 1s;
    display: flex;
    justify-content: space-between;
    align-items: center;
    position: relative;
    transition: all 1s;
}
.PlayMusic .left{
    margin-left: 10px;
    display: flex;
    align-items: center;
    height: 100%;
    justify-content: center;
}
.PlayMusic .left img{
    width: 60px;
    height: 60px;
    border-radius: 20px;
}
.PlayMusic .right {
    margin-right: 20px;
    font-size: 20PX;
    cursor: pointer;
}
.PlayMusic .right a{
    font-size: 50px;
}
</style>
