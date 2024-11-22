<!--这里是导航栏的组件们-->
<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark"><!--lg格式小折叠，大展开-->
  <div class="container-fluid"> <!--自适应宽度100%-->
    <router-link class="navbar-brand" :to="{name:'home'}">重生之我在内大学JAVA</router-link>
    
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0"> 
        <li class="nav-item">
          <!--<a class="nav-link" aria-current="page" href="/homepage/">首页</a>-->
          <router-link :class="route_name =='home_index' ? 'nav-link activate' : 'nav-link' " :to="{name:'home_index'}" >首页</router-link>
        </li>
        <li class="nav-item">
          <!--<a class="nav-link" href="/store/">购买</a>  会刷新，换成vue的组件-->
          <router-link :class="route_name =='sotre_index' ? 'nav-link activate' : 'nav-link'  " :to="{name:'store_index'}">购买</router-link>
        </li>
        <li class="nav-item">
            <!--<a class="nav-link" href="/order/">订单</a>-->
            <router-link :class="route_name =='order_index' ? 'nav-link activate'  : 'nav-link' " :to="{name:'order_index'}">订单</router-link>
        </li>
      </ul>

      <ul class="navbar-nav">
        <!-- 分类讨论一下，没有登陆的时候显示的是 登陆+注册 
                            登陆的话，显示的是 人名+退出 
                根据is_login的状态来判断                    
        -->
        <ul class="navbar-nav" v-if="$store.state.user.is_login">

        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            {{ $store.state.user.user_name }}
          </a>
          <ul class="dropdown-menu">
            <li>
              <routerlink class="dropdown-item" :to="{name:'user_index'}">用户信息</routerlink>
            </li>

            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">退出</a></li>
          </ul>
        </li> 
      </ul>
      <!---->
      <ul class="navbar-nav" v-else>
        <li class="nav-item ">
          <router-link class="nav-link ":to="{name:'user_account_login'}" href="#" role="button">
            登陆
          </router-link>
        </li> 

        <li class="nav-item">
          <a class="nav-link " href="#" role="button">
            注册
          </a>
        </li>  

      </ul>
      <!---->

      </ul>

      <span class="nav-item">

      </span>
    </div>
  </div>
</nav>
</template>

<script>
//界面高亮，先取得页面 vue-router中获取uesRoute的API

import  {useRoute} from 'vue-router'
//实时计算
import { computed } from 'vue'
export default{
  setup()
  {
    const route =useRoute()
    //computed函数返回route的name
    let route_name = computed(()=>route.name)
    return {route_name}
  }
}

</script>

<!--scoped加入之后，样式只在当前组件生效，不会影响到其他组件。 -->
<style scoped>

</style>