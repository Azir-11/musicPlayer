import { defineStore } from 'pinia'

export const useCounterStore = defineStore({
  id: 'counter',
  state: () => ({
    counter: 1,
    display:"display:block",
    PlayerDisplay:"display:none",
    isvisble:false,
  }),
  getters: {
    doubleCount: (state) => state.counter * 2,
    isdisplay: (state)=>{
      return  state.display== "display:block"? "display:none" :"display:block"
    }
  },
  actions: {
    increment() {
      this.counter++
    },
 
  }
})
