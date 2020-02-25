<template>
<div>
  <div id="nav">
      <div v-for="(item,index) in items" :key="index">
          <span>{{items[index].title}}</span>
          <button v-if="items[index].title == '361'">111</button>
      </div>
  </div>
</div>
</template>

<script>
export default {
    name:"Nav",
    data(){
        return{
            items:{}
        }
    },
     mounted: function () {
        this.$axios.get(this.$url + '/api/nav').then( (res)=> {
        this.items = res.data.resp
        console.log(this.items)
    })
  }
}

</script>

<style>

</style>