登陆页面中
<template>
    <div class="user-info-container">
        <ContentField>
            <div class="card">
                <div class="card-body">
                    <div><h1>商城</h1></div>
                </div>
            </div>
            <div class="user-details">
                <div class="photo-container">
                    <img :src="user.photo" alt="用户头像" class="user-photo" />
                </div>
                <div>
                    <div>欢迎{{ user.username }}来到商城~</div>
                </div>
            </div>
            <!--列表展示商品-->
            <table class="table">
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>商品图片</th>
                    <th>商品名</th>
                    <th>单价</th>
                    <th>库存</th>
                    <th>简介</th>
                    <th>操作</th>
                    </tr>
                </thead>
                <tbody><!--每个商品占一行-->
                    <tr v-for="merchandise in merchandises" :key="merchandise.id">
                        <th scope="row">{{ merchandise.id }}</th>
                        <td>
                            <img :src="merchandise.merimg" alt="商品图片" class="merchandise-image" />
                        </td>
                        <td>{{ merchandise.mername }}</td>
                        <td>{{ merchandise.mervalue }}</td>
                        <td>{{ merchandise.mernum }}</td>
                        <td>{{ merchandise.description }}</td>
                        <td>
                            <button
                                type="button"
                                class="btn btn-success"
                                data-bs-toggle="modal" :data-bs-target="'#buy-merchandise-modal-'+merchandise.id"> 购买
                            </button>
                        </td>
                        
                    </tr>
                   


                </tbody>
                </table>
            <!--商城表单end-->
        </ContentField>


    </div>

     <!--Modal实现-->
<div class="modal fade" :id="'buy-merchandise-modal-'+merchandise.id" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5">选择购买</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <div class="modal-body">
                                <!--forms start-->
                                <div class="mb-3">
                                    <label for="buy-num" class="form-label">购买个数</label>
                                    <input type="text" class="form-control" id="buy-num" placeholder="请输入购买的个数" v-model="purchaseQuantity">
                                </div>
                                <!--forms end-->
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-bs-toggle="modal"  @click="submitPurchase">
                                    提交
                                </button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                    关闭
                                </button>
                            </div>
                            </div>
                        </div>
                        </div>
                        <!--Modal end-->
    
    
    
</template>

<script>
import ContentField from '@/components/ContentField.vue';
import $ from 'jquery';
import { reactive } from 'vue';
import { useStore } from 'vuex';
import {ref} from 'vue';

export default { 
    components: {
        ContentField
    },
    setup() {
        const store = useStore();
        const merchandise=reactive({
            mername:"",
            mervalue:"",
            mernum:0,
            merimg:"",
            description:"",
            error_msg:"",

        });
        const user = reactive({
            username:'',
            phone: '',
            email: '',
            mernum: 0,
            photo: '' 
        });
        let merchandises = ref([]); // 商品数据

        //传User信息
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

        
        //show商品
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

        //BUY
        const purchaseQuantity = ref(1); // 默认购买数量
        const selectedMerchandise = reactive({
        id: "",
        mername: "",
        mervalue: "",
        mernum: 0,
        });

        // 设置当前选择的商品数据
        const setModalData = (merchandise) => {
            console.log("选中商品信息：", merchandise);
            selectedMerchandise.id = merchandise.id;
            selectedMerchandise.mername = merchandise.mername;
            selectedMerchandise.mervalue = merchandise.mervalue;
            selectedMerchandise.mernum = merchandise.mernum;
            purchaseQuantity.value =1; // 重置购买数量
        };

        // 提交购买请求
        const submitPurchase = () => {
        const jwt_token = localStorage.getItem("jwt_token");
        $.ajax({
            url: "http://127.0.0.1:3000/user/merchandise/buy/",
            type: "POST",
            headers: {
            Authorization: "Bearer " + jwt_token,
            },
            data: {
            merid: selectedMerchandise.username,
            mernum: purchaseQuantity,
            },
            success(resp) {
                console.log(resp);
            alert("购买成功！");
            refresh_merchandises(); // 重新刷新商品列表
            },
            error(err) {
                console.log("it is mername:",selectedMerchandise.mername);
                console.log("it is mervalue:",purchaseQuantity);
                console.error(err);
            alert("购买失败：" + err.responseJSON.message);
            },
        });
        };
        
        

        return {
            user,merchandises,merchandise,
            //buy
            purchaseQuantity,selectedMerchandise,setModalData,submitPurchase,
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