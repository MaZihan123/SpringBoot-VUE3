<template>
    <ContentField><!-- grid、居中、 -->
        <div class="row justify-content-md-center">
                <div class="col-3">

                <form @submit.prevent="login"><!-- 提交触发login函数 ,v-model和下面de绑定 -->

                    <!-- 表单 -->
                    <div class="mb-3">
                        <label for="user_name" class="form-label">用户名</label>
                        <input v-model="user_name" type="text" class="form-control" id="user_name" placeholder="牢大提醒：输入用户名">
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
        let user_name=ref('');
        let password=ref('');
        let error_message=ref('');

        const login=()=>{
            error_message.value="";
            store.dispatch("login",{
                user_name:user_name.value,
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
            user_name,
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
 