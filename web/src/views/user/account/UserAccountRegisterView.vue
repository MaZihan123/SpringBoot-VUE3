<template>
    <ContentField><!-- grid、居中、 -->
        <div class="row justify-content-md-center">
                <div class="col-3">

                <form @submit.prevent="register">
                    <h1>注册</h1>
                    <!-- 表单 -->
                    
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="孩子:请输入你的用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="孩子:请输入你的密码">
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">确认密码</label>
                        <input v-model="confirmPassword" type="password" class="form-control" id="confirmPassword" placeholder="孩子:再输入一遍密码确认一下">
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">电话号</label>
                        <input v-model="phone" type="text" class="form-control" id="phone" placeholder="孩子:请输入你的电话号">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">邮箱</label>
                        <input v-model="email" type="text" class="form-control" id="email" placeholder="孩子:请输入你的邮箱">
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
//import {useStore} from 'vuex'
//import router from '@/router/index'
import $ from 'jquery'
import router from '../../../router';

export default 
{
    components: 
    {
        ContentField
    },
    setup()
    {   
        //修改state值的时候放入user.js中，不用修改就放这里就行了
        //const store=useStore();
        let username=ref('');
        let password=ref('');
        let confirmPassword=ref('');
        let phone=ref('');
        let email=ref('');

        let error_message=ref('');

        const register=()=>{
            $.ajax({
                url:"http://127.0.0.1:3000/user/account/register/",
                type:"post",//改数据库一般用post，get是获取数据
                data:
                {
                    username:username.value, 
                    password:password.value,
                    confirmPassword:confirmPassword.value,
                    phone:phone.value, 
                    email:email.value,
                },
                success(resp)
                {
                    if(resp.error_message==="success")
                    {
                        router.push({name:"user_account_login"});
                    }
                    else{
                        error_message.value=resp.error_message;
                    }
                },
            });
        }
        
        return{
            username,
            password,
            confirmPassword,
            phone,
            email,
            error_message,
            register,
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
 