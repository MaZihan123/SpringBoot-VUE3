<template>
  <NavBar />
  <router-view></router-view>
</template>

<script>
import NavBar from '@/components/NavBar.vue'

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"

import $ from 'jquery'
export default
{
  components:
  {
    NavBar
  },
  setup()
  {
      $.ajax(
        {
          url:"http://127.0.0.1:3000/user/account/token/",
          type:"post",
          data:
          {
            user_name:"马梓涵",
            password:"123321",
          },
          success(resp)
          {
            console.log(resp);
            
          },
          error(resp)
          {
            console.log(resp);
          }
        }
      )

        //test2
        // $.ajax(
        // {
        //   url:"http://127.0.0.1:3000/user/account/info/",
        //   type:"get",
        //   data:
        //   header:{
        //     "Authorization":"Bearer "+localStorage.getItem("token")
        //   },
        //   success(resp)
        //   {
        //     console.log(resp);
        //   },
        //   error(resp)
        //   {
        //     console.log(resp);
        //   }
        // }

  }
}


</script>

<!--scoped加入之后，样式只在当前组件生效，不会影响到其他组件。 -->
<style>
body{
  background-image:url(@/assets/images/background.png);
  background-size:cover;
}
</style>


