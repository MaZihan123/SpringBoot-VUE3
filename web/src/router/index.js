import { createRouter, createWebHistory } from 'vue-router'


//首页、购买、已结算订单
import HomePage from '../views/head/HomePage.vue'
import NotFound from '../views/error/NotFound.vue'
import OrderIndex from '../views/order/OrderIndex.vue'
import ShoppingMall from '../views/shoppingMall/ShoppingMall.vue'
import User from '../views/user/UserIndex.vue'
import UserAccountLoginView from '../views/user/account/UserAccountLoginView.vue'
import UserAccountRegisterView from '../views/user/account/UserAccountRegisterView.vue'


const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/homepage/"

  },
  {
    path: "/homepage/",
    name: "home_index",
    component: HomePage //首页组件
  },
  {
    path: "/404/",
    name: "404_index",
    component: NotFound //404组件
  },
  {
    path: "/myorder/",
    name: "order_index",
    component: OrderIndex //订单组件
  },
  {
    path: "/store/",
    name: "store_index",
    component: ShoppingMall//商城组件
  },
  {
    path: "/user/",
    name: "user_index",
    component: User//用户组件
  },
  {
    path:"/user/myself",
    name:"user_myself",
  },
  {
    path:"/user/account/login",
    name:"user_account_login",
    component:UserAccountLoginView,
  },
  {
    path:"/user/account/register",
    name:"user_account_register",
    component:UserAccountRegisterView,
  }

  ,
  {
    path:"/:catchAll(.*)",
    redirect: "/404"
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
