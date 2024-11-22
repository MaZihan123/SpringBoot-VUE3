<template>
  <NavBar />
  <router-view></router-view>
</template>

<script>
import NavBar from '@/components/NavBar.vue'

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"

//import $ from 'jquery'
export default
{
  components:
  {
    NavBar
  },
  setup()
  {
      // $.ajax(
      //   {
      //     url:"http://127.0.0.1:3000/user/account/token/",
      //     type:"post",
      //     data:
      //     {
      //       user_name:"马梓涵",
      //       password:"123321",
      //     },
      //     success(resp)
      //     {
      //       console.log(resp);
            
      //     },
      //     error(resp)
      //     {
      //       console.log(resp);
      //     }
      //   }
      // )

      //   //test2
      //   $.ajax(
      //   {
      //     url:"http://127.0.0.1:3000/user/account/info/",
      //     type:"get",
      //     headers:{
      //       //授权
      //       "Authorization":"Bearer "+"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3NzE3ZDhmYzljNWE0YjJmODM1ZWUxODdiNTQ0MWIwYyIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTczMjA5ODQ3MywiZXhwIjoxNzMzMzA4MDczfQ.oyl5aTeCRlaj8XfFE2zab4MTk4xieBSrKc9pifzHQ80"
      //       //认证是Authentication
      //     },
      //     success(resp)
      //     {
      //       console.log(resp);
      //     },
      //     error(resp)
      //     {
      //       console.log(resp);
      //     }
      //   }
      // )

      // $.ajax(
      //   {
      //     url:"http://127.0.0.1:3000/user/account/register/",
      //     type:"post",
      //     data:
      //     {
      //       user_name:"马梓涵2",
      //       password:"123321541",
      //       confirm_password:"123321541",
      //       email:"2321@qq.com",
      //       phone_number:"12345678901",
      //     },
      //     success(resp)
      //     {
      //       console.log(resp);
      //     },
      //     error(resp)
      //     {
      //       console.log(resp);
      //     }
      //   },
    //  )

  }
}


</script>

<!--scoped加入之后，样式只在当前组件生效，不会影响到其他组件。 -->
<style scoped>
body{
  background-image:url(@/assets/images/background.png);
  background-size:cover;
}
</style>


