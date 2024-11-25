<template>
    <!--正在显示信息的话，不展示，pulling之后再展示-->
    <ContentField v-if="!$store.state.user.pulling_info">
        <!-- grid、居中、 -->
        <div class="row justify-content-md-center">
                <div class="col-3">

                <form @submit.prevent="login"><!-- 提交触发login函数 ,v-model和下面de绑定 -->

                    <!-- 表单 -->
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="牢大提醒：输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="牢大提醒：我不到你密码啊">
                    </div>

                    <!--error Login-->
                    <div class="error-message">{{ error_message }}</div>



                    <button type="submit" class="btn btn-primary">提交</button>
                </form>


                </div>
            
        </div>
    </ContentField>

</template>

<script>
import ContentField from '@/components/ContentField.vue'
import { ref } from 'vue'
import {useStore} from 'vuex'
import router from '@/router/index'

export default 
{
    components: 
    {
        ContentField
    },
    setup()
    {
        const store=useStore();
        let username=ref('');
        let password=ref('');
        let error_message=ref('');


        //开始看看有没有token
        const jwt_token=localStorage.getItem("jwt_token");
        if(jwt_token)
        {
            store.commit("updateUserToken",jwt_token);//调user.js中mutation
            store.dispatch("getinfo",{
                success()
                {
                    router.push({name:"home_index"});
                    store.commit("updatePullingInfo",false);
                },
                error()
                {//pulling结束，false 
                    store.commit("updatePullingInfo",false);

                }
            })
        }
        else//本地没有token
        {   //pull结束，false
            store.commit("updatePullingInfo",false);//调user.js中mutation
        }

        const login=()=>{
            error_message.value="";
            store.dispatch("login",{
                username:username.value,
                password:password.value,
                success()
                {
                    store.dispatch( "getinfo",{//成功的回调
                            success()
                            {
                                router.push({name:"home_index"});
                                console.log(store.state.user);
                            }
                        }
                    )
                    
                },
                error()
                {
                    //console.log(resp);
                    error_message.value="用户名或密码错误";
                }
            })
        }
        return{
            username,
            password,
            error_message,
            login,  
        }
    }
}
</script>

<style scoped>
button{
    width:100%;
}
div.error-message{
    color:red;
}
</style>
 