<template>
    <div class="user-info-container">
        <ContentField>
            <h2>管理员信息</h2>
            <div class="user-details">
                <div class="photo-container">
                    <img :src="user.photo" alt="用户头像" class="user-photo" />
                </div>
                <div>
                    <strong>👦🏻 用户名:</strong> {{ user.username }}
                </div>
                <div>
                    <strong>📱 手机号:</strong> {{ user.phone }}
                </div>
                <div>
                    <strong>📧 邮箱:</strong> {{ user.email }}
                </div>
            </div>

            <div class="container">
                <div class="col-12">
                    <div class="card" style="margin-top:20px;">
                        <div class ="card-header">
                            <h3>商品列表</h3>

                            <button type="button" class="btn btn-primary float-end" data-bs-toggle="modal" data-bs-target="#add-merchandise-btn">
                                创建商品
                            </button>
                            <!---->
                            <!-- Modal -->
                            <div class="modal fade" id="add-merchandise-btn" tabindex="-1">
                                <div class="modal-dialog modal-xl">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title">添加商品</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                            <div class="modal-body">
                        <!--forms 然后改type="text"-->
                        <div class="mb-3">
                            <label for="add-merchandise-name" class="form-label">*商品名称</label>
                            <input v-model="merchandiseAdd.mername" class="form-control" placeholder="请输入商品名称">
                        </div>
                        <div class="mb-3">
                            <label for="add-merchandise-value" class="form-label">*商品金额</label>
                            <input v-model="merchandiseAdd.mervalue" class="form-control" placeholder="请输入商品金额">
                        </div>
                        <div class="mb-3">
                            <label for="add-merchandise-photo" class="form-label">商品图片网址</label>
                            <input v-model="merchandiseAdd.merimg" class="form-control" placeholder="请输入商品图片网址">
                        </div>
                        <div class="mb-3">
                                <label for="add-merchandise-description" class="form-label">商品简介</label>
                                <textarea v-model="merchandiseAdd.description" class="form-control" rows="3" placeholder="请输入商品简介"></textarea>
                        </div>
                        <!---->             
                    </div>
                    <div class="modal-footer">
                        <!--报错-->
                        <div class="error-message">{{ merchandiseAdd.error_msg }}</div>
                        
                        <button type="button" class="btn btn-primary" @click="add_merchandise">提交</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                            
                    </div>
            </div>
        </div>
    </div> 

                                <!--商品table-->
                                <table class="table  ">
                                <thead>
                                    <tr>
                                        <th >ID</th>
                                        <th>商品名称</th>
                                        <th>金额</th>
                                        <th>描述</th>
                                        <th>图片网址</th>
                                        <th>管理员操作</th>
                                    </tr>
                                </thead> 
                                <tbody><!--行-->
                                    <!--后端给一个list，然后merchandise遍历每一个[n]-->
                                    <tr v-for="merchandise in merchandises" :key="merchandise.id"><!--VUE循环，绑定关键字-->
                                        <td>{{ merchandise.id }}</td>  
                                        <td>{{ merchandise.mername }}</td>
                                        <td>{{ merchandise.mervalue }}</td>
                                        <td>{{ merchandise.description }}</td>
                                        <td>{{ merchandise.merimg }}</td>
                                        <td>                                                                                                                <!--表达式！！！！冒号！！！！气死了v-bind-->
                                            <button type="button" class="btn btn-secondary" @click="update_merchandise(merchandise)" data-bs-toggle="modal" :data-bs-target="'#update-merchandise-modal-'+merchandise.id">修改</button>
                                            <button type="button" class="btn btn-danger" @click="remove_merchandise(merchandise)">删除</button>
                                            <!--modal-->
                                            <div class="modal fade" :id="'update-merchandise-modal-'+merchandise.id" tabindex="-1">
                                                <div class="modal-dialog modal-xl">
                                                    <div class="modal-content">
                                                        <!-- Modal Header -->
                                                        <div class="modal-header">
                                                            <h1 class="modal-title">修改商品</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <!-- Modal Body -->
                                                        <div class="modal-body">
                                                            <!-- 表单内容 -->
                                                            <div class="mb-3">
                                                                <label for="add-merchandise-name" class="form-label">*商品名称</label>
                                                                <input
                                                                    v-model="merchandise.mername"
                                                                    type="text"
                                                                    class="form-control"
                                                                    id="add-merchandise-name"
                                                                    placeholder="请输入商品名称"
                                                                />
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="add-merchandise-value" class="form-label">*商品金额</label>
                                                                <input
                                                                    v-model="merchandise.mervalue"
                                                                    type="text"
                                                                    class="form-control"
                                                                    id="add-merchandise-value"
                                                                    placeholder="请输入商品金额"
                                                                />
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="add-merchandise-photo" class="form-label">商品图片网址</label>
                                                                <input
                                                                    v-model="merchandise.merimg"
                                                                    type="text"
                                                                    class="form-control"
                                                                    id="add-merchandise-photo"
                                                                    placeholder="请输入商品图片网址"
                                                                />
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="add-merchandise-description" class="form-label">商品简介</label>
                                                                <textarea
                                                                    v-model="merchandise.description"
                                                                    class="form-control"
                                                                    id="add-merchandise-description"
                                                                    rows="3"
                                                                    placeholder="请输入商品简介"
                                                                ></textarea>
                                                            </div>
                                                        </div>
                                                        <!-- Modal Footer -->
                                                        <div class="modal-footer">
                                                            <div class="error-message text-danger">{{ merchandiseAdd.error_msg }}</div>
                                                            <button type="button" class="btn btn-primary" @click="update_merchandise(merchandise)">保存修改</button>
                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--modal_end-->
                                        
                                        </td>
                                    </tr>

                                </tbody>
                                </table>
                        </div>
                    </div>
                </div>
            </div>

        </ContentField>
    </div>
    
    
    
</template>

<script>
import ContentField from '@/components/ContentField.vue';
import $ from 'jquery';
import { reactive } from 'vue';
import { useStore } from 'vuex';
import {ref} from 'vue'//绑定变量，绑定对象reactive

import {Modal} from 'bootstrap/dist/js/bootstrap';

export default { 
    components: {
        ContentField
    },
    setup() {
        const store = useStore();
        //新建的商品
        const merchandiseAdd=reactive({
            mername:"",
            mervalue:"",
            merimg:"",
            description:"",
            error_msg:"",

        });
        const user = reactive({
            username:'',
            phone: '',
            email: '',
            mernum: 0,
            photo: '' // 用于存储头像 URL
        });
        let merchandises = ref([]); // 商品数据

        
        // 使用 jQuery 发起 AJAX 请求
        $.ajax({
          //url: "http://127.0.0.1:3000/user/",
            url: "http://127.0.0.1:3000/user/manage/",
            type: "GET",
            headers: {
                Authorization: "Bearer " + store.state.user.token,
                
            },
            success(resp) {
                console.log("这里是token",store.state.user.token),
                user.phone = resp.phone;
                user.email = resp.email;
                user.username = resp.username;
                user.photo = resp.photo; // 假设后端返回字段为 photo
                console.log("用户信息获取成功:", resp);
                console.log("it is username:",user.username);
            },
            error(err) {
                console.error("获取用户信息失败:", err);
            }
        });

        
        const refresh_merchandises =()=>{
            const jwt_token=localStorage.getItem("jwt_token");
            $.ajax({
                url: "http://127.0.0.1:3000/user/merchandise/getlist/",
                type:"GET",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp)
                {
                    //后端返回一个列表
                    merchandises.value = resp;
                    console.log("merchandise get successfuly");
                },
                error(resp)
                {
                    
                    console.log(resp);
                }
            })
        }
        refresh_merchandises();
        
        //click add merchandise
        const add_merchandise=()=>
        {
            //first clean last errorMsg
            merchandiseAdd.error_msg="";
            const jwt_token=localStorage.getItem("jwt_token");
            $.ajax({
                url:"http://127.0.0.1:3000/user/merchandise/add/",
                type:"POST",
                data:{
                     mername:merchandiseAdd.mername,
                     mervalue:merchandiseAdd.mervalue,
                     description:merchandiseAdd.description,
                     merimg:merchandiseAdd.merimg,
                     error_msg:merchandiseAdd.error_msg,
                },
                headers:{
                    Authorization:"Bearer "+jwt_token,
                },
                success(resp)
                {
                    console.log(resp);
                    if(resp.success_message==='success')
                    {
                        console.log("add merchandise successfully");
                        //clear
                        merchandiseAdd.mername="";
                        merchandiseAdd.mervalue="";
                        merchandiseAdd.merimg="";
                        merchandiseAdd.description="";
                        merchandiseAdd.error_msg="";
                        
                        console.log("add merchandise successfully");
                        Modal.getInstance("#add-merchandise-btn").hide();//id
                        console.log("close modal");

                        refresh_merchandises();
                    }
                    else
                    {
                        merchandiseAdd.error_msg=resp.error_message;
                    }
                },
            })
        }

        //remove merchandise
        const remove_merchandise=(merchandise)=>
        {
            const jwt_token=localStorage.getItem("jwt_token");
            $.ajax({
                url: "http://127.0.0.1:3000/user/merchandise/remove/",
                type:"POST",
                data:
                {
                    mername:merchandise.mername,
                },
                headers:{
                    Authorization:"Bearer "+jwt_token,
                },
                success(resp)
                {
                    console.log(merchandiseAdd.mername);
                    console.log(resp);
                    if(resp.success_message==='success')
                    {
                        console.log("remove merchandise successfully");
                        refresh_merchandises();
                    }
                },
                error(resp)
                {
                    console.log(merchandiseAdd.mername);
                    console.log(resp);
                }
            })
        }
        //click update merchandise
        const update_merchandise=(merchandise)=>
        {
            //first clean last errorMsg
            merchandiseAdd.error_msg="";
            const jwt_token=localStorage.getItem("jwt_token");
            $.ajax({
                url:"http://127.0.0.1:3000/user/merchandise/update/",
                type:"POST",
                data:{
                     mername:merchandise.mername,
                     mervalue:merchandise.mervalue,
                     description:merchandise.description,
                     merimg:merchandise.merimg,
                     error_msg:merchandise.error_msg,
                },
                headers:{
                    Authorization:"Bearer "+jwt_token,
                },
                success(resp)
                {
                    console.log(resp);
                    if(resp.success_message==='success')
                    {
                        console.log("update merchandise successfully");
                        
                        
                        console.log("update merchandise successfully");
                        Modal.getInstance('#update-merchandise-modal-'+merchandise.id).hide();//id
                        console.log("close modal");

                        refresh_merchandises();
                    }
                    else
                    {
                        merchandiseAdd.error_msg=resp.error_message;
                    }
                },
            })
        }

        return {
            user,merchandises,merchandiseAdd,add_merchandise,remove_merchandise,
            update_merchandise,
        };

        
    }
};
</script>

<style scoped>
div.error-message{
    color:red;
}



.user-photo {
    width: 100px;
    height: 100px;
    border-radius: 50%; /* 将图片裁剪为圆形 */
    object-fit: cover; /* 确保图片以正确比例显示 */
    border: 2px solid #ddd;
}
</style>