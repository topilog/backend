<template>
  <div class="allPage">
    <div class="nav">
      <Nav/>
      <div v-for="(item,index) in items" :key="index">{{item}}</div>
    </div>
  </div>
</template>

<script>
import Nav from "./Nav"
export default {
  name: 'mainPage',
  components:{
    Nav
  },
  data () {
    return {
      items:[
        {id:0,num:1},
        {id:1,num:2},
        {id:2,num:3}
      ]
    }
  },
  mounted: function () {
    this.$axios.get(this.$url + '/api/article/infos').then(function (res) {
      // console.log(res.data.resp)
    })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>

</style>
