<template>
  <NavBar />
  <router-view></router-view>
</template>

<script>
import NavBar from '@/components/NavBar.vue'
export default
{
  components:
  {
    NavBar
  }
}
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"


</script>

<!--scoped加入之后，样式只在当前组件生效，不会影响到其他组件。 -->
<style>
body{
  background-image:url(@/assets/background.png);
  background-size:cover;
}
</style>


