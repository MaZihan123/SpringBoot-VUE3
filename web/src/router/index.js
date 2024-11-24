import { createRouter, createWebHistory } from 'vue-router'


//首页、购买、已结算订单
import HomePage from '../views/head/HomePage.vue'
import NotFound from '../views/error/NotFound.vue'
import OrderIndex from '../views/order/OrderIndex.vue'
import ShoppingMall from '../views/shoppingMall/ShoppingMall.vue'
import User from '../views/user/UserIndex.vue'
import UserAccountLoginView from '../views/user/account/UserAccountLoginView.vue'
import UserAccountRegisterView from '../views/user/account/UserAccountRegisterView.vue'
import stroe from '../store/index.js'

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/homepage/",
    meta:{
      requestAuth:true,//家目录
    }

  },
  {
    path: "/homepage/",
    name: "home_index",
    component: HomePage //首页组件
    ,
    meta:{
      requestAuth:true,
    }
  },
  {
    path: "/404/",
    name: "404_index",
    component: NotFound //404组件
    ,
    meta:{
      requestAuth:false,
    }
  },
  {
    path: "/myorder/",
    name: "order_index",
    component: OrderIndex //订单组件
    ,
    meta:{
      requestAuth:true,
    }
  },
  {
    path: "/store/",
    name: "store_index",
    component: ShoppingMall//商城组件
    ,
    meta:{
      requestAuth:true,
    }
  },
  {
    path: "/user/",
    name: "user_index",
    component: User//用户组件
    ,
    meta:{
      requestAuth:true,
    }
  },
  {
    path:"/user/account/login/",
    name:"user_account_login",
    component:UserAccountLoginView,
    meta:{
      requestAuth:false,
    }
  },
  {
    path:"/user/account/register/",
    name:"user_account_register",
    component:UserAccountRegisterView,
    meta:{
      requestAuth:false,
    }
  },
  
  {
    path:"/:catchAll(.*)",
    redirect: "/404",
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})
//通过router进入每个页面之前，都会调守卫
router.beforeEach((to,from,next) => {
  
  //要授权且没登陆
  if(to.meta.requestAuth&& !stroe.state.user.is_login)
  {
    next({name:"user_account_login"});
  }
  else{
    next();
  }


})

export default router
