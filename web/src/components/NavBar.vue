<!--这里是导航栏的组件们-->
<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark"><!--lg格式小折叠，大展开-->
  <div class="container-fluid"> <!--自适应宽度100%-->
    <router-link class="navbar-brand" :to="{name:'home'}">重生之我在内大学JAVA</router-link>
    
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0"> 
        <li class="nav-item">
          <!--<a class="nav-link" aria-current="page" href="/homepage/">首页</a>-->
          <router-link :class="route_name =='home_index' ? 'nav-link active' : 'nav-link' " :to="{name:'home_index'}" >首页</router-link>
        </li>
        <li class="nav-item">
          <!--<a class="nav-link" href="/store/">购买</a>  会刷新，换成vue的组件-->
          <router-link :class="route_name =='sotre_index' ? 'nav-link active' : 'nav-link'  " :to="{name:'store_index'}">购买</router-link>
        </li>
        <li class="nav-item">
            <!--<a class="nav-link" href="/order/">订单</a>-->
            <router-link :class="route_name =='order_index' ? 'nav-link active'  : 'nav-link' " :to="{name:'order_index'}">订单</router-link>
        </li>
      </ul>

      
        <!-- 分类讨论一下，没有登陆的时候显示的是 登陆+注册 
                            登陆的话，显示的是 人名+退出 
                根据is_login的状态来判断                    
        -->
        <ul class="navbar-nav" v-if="$store.state.user.is_login">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
             {{ $store.state.user.username }}
            </a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
              <li>
                <router-link class="dropdown-item" :to="{name:'user_index'}">用户信息</router-link>
              </li>

              <!-- add a manage超级管和普通管都可以管理商品-->
              <template v-if="userlevel === '3'||userlevel==='2'">
                <li>
                  <router-link class="dropdown-item" :to="{name:'user_manage'}">管理商品</router-link>
                </li>
                <li><hr class="dropdown-divider"></li>
              </template>
              <!---->
               <!-- 管理用户，只有超级管可以管理用户-->
               <template v-if="userlevel === '3'">
                <li>
                  <router-link class="dropdown-item" :to="{name:'user_usermanage'}">管理用户</router-link>
                </li>
                <li><hr class="dropdown-divider"></li>
              </template>
              <!---->

              <li><a class="dropdown-item" href="#" @click="logout">退出</a></li>
              <!-- 点退出调用-logout函数调dispatch，然后调用user.js的logout，然后调用mutations的logout函数改变state
              同步的话直接调用mutations的logout函数改变state 
              异步放在actions里面，然后调用mutations的logout函数改变state
              -->
            </ul>
          </li>
        </ul>

        <ul class="navbar-nav" v-else-if="!$store.state.user.pulling_info">
          <li class="nav-item">
            <router-link class="nav-link" :to="{name:'user_account_login'}" role="button">
              登陆
            </router-link>
          </li> 
          <li class="nav-item">
            <router-link class="nav-link" :to="{name:'user_account_register'}" role="button">
              注册
            </router-link>
          </li>  
        </ul>
      <!---->

      

      
    </div>
  </div>
</nav>
</template>

<script>
//界面高亮，先取得页面 vue-router中获取uesRoute的API
import  {useRoute} from 'vue-router'
//实时计算
import { computed } from 'vue'
// import {ref} from 'vue'
// import $ from 'jquery'

import {useStore} from 'vuex'
export default{
  setup()
  {
    const route =useRoute();
    //computed函数返回route的name
    let route_name = computed(()=>route.name)
    
    const store=useStore();
    //事件=>触发函数   用户退出登陆   

    // 获取当前用户名
    let username = computed(() => store.state.user.username);
    //let user-authority=,computed(() => store.state.user.authority);
    let userlevel= computed(() => store.state.user.userlevel);

  //console.log("userlevel:",userlevel.value);
    // const logout=()=>{
    //   store.dispatch("logout");//dispatch（vuex的方法）触发action
    // } 
    

  console.log(store.state.user); 
    const logout=()=>{
      store.dispatch("logout");//dispatch（vuex的方法）触发action
      
    }
    // const userlevel = ref(null);
    // $.ajax({
    //   url:"http:http://127.0.0.1:3000/user/account/info/",
    //   type:"GET",
    //   headers:{
    //     Authorization:"Bearer "+store.state.user.token
    //   },
    //   success(resp){
    //     if(resp.error_message==="success")
    //     { 
    //       console.log("userlevel:",resp.userlevel);
    //       userlevel.value=resp.userlevel;
    //     }
    //     else{
    //       console.log("用户信息获取失败",resp);
    //     }
    //   },
    // })

    return {
      route_name,
      username,
      userlevel,
      logout
    }
  }
}

</script> 

<!--scoped加入之后，样式只在当前组件生效，不会影响到其他组件。 -->
<style scoped>

</style>