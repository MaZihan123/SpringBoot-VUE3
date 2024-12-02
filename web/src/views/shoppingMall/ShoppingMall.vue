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
                            <!-- 输入购买数量 -->
                            <input 
                                type="number" 
                                class="form-control" 
                                style="width: 80px; display: inline-block; margin-right: 5px;"
                                v-model.number="merchandise.purchaseQuantity" 
                                min="1" 
                                placeholder="数量">
                            <!-- 购买按钮 -->
                            <button 
                                type="button" 
                                class="btn btn-success" 
                                @click="submitPurchase(merchandise)"> 
                                购买
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
            <!--商城表单end-->
        </ContentField>
    </div>
</template>

<script>
import ContentField from '@/components/ContentField.vue';
import $ from 'jquery';
import { reactive, ref } from 'vue';
import { useStore } from 'vuex';

export default { 
    components: 
    {
        ContentField
    },
    setup() {
        const store = useStore();

        const user = reactive({
            username: '',
            phone: '',
            email: '',
            mernum: 0,
            photo: '' 
        });

        let merchandises = ref([]); // 商品数据

        // 获取用户信息
        $.ajax({
            // url: "http://127.0.0.1:3000/user/",
            url: "http://127.0.0.1:3000/user/manage/",
            type: "GET",
            headers: {
                Authorization: "Bearer " + store.state.user.token,
            },
            success(resp) {
                console.log("这里是token", store.state.user.token);
                user.phone = resp.phone;
                user.email = resp.email;
                user.username = resp.username;
                user.photo = resp.photo; // 假设后端返回字段为 photo
                console.log("用户信息获取成功:", resp);
                console.log("it is username:", user.username);
            },
            error(err) {
                console.error("获取用户信息失败:", err);
            }
        });

        // 显示商品
        const refresh_merchandises = () => {
            const jwt_token = localStorage.getItem("jwt_token");
            $.ajax({
                url: "http://127.0.0.1:3000/user/merchandise/getlist/",
                type: "GET",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    // 后端返回一个列表
                    // 为每个商品添加 purchaseQuantity 属性，默认值为 1
                    merchandises.value = resp.map(item => {
                        return {
                            ...item,
                            purchaseQuantity: 1 // 默认购买数量为 1
                        };
                    });
                    console.log("merchandise get successfully");
                },
                error(resp) {
                    console.log(resp);
                }
            });
        };
        refresh_merchandises();

        // 提交购买请求，接受参数 merchandise
        const submitPurchase = (merchandise) => {
            const jwt_token = localStorage.getItem("jwt_token");
            $.ajax({
                url: "http://127.0.0.1:3000/user/merchandise/buy/",
                type: "POST",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                data: {
                    mername: merchandise.mername, // 商品名
                    quantity: merchandise.purchaseQuantity, // 购买数量
                },
                success(resp) {
                    console.log(resp);
                    if(resp.error_message!="success")
                    {
                        console.log("购买失败，商品名:", merchandise.mername);
                        console.log("购买数量:", merchandise.purchaseQuantity);
                        console.error("购买失败:", resp.error_message);
                        alert(resp.error_message);
                        return;
                    }
                    alert("购买成功！");
                    refresh_merchandises(); // 重新刷新商品列表
                },
            });
        };

        return {
            user,
            merchandises,
            submitPurchase
        };
    }
};
</script>

<style scoped>
div.error-message{
    color:red;
}
.merchandise-image {
    width: 100px;  
    height: 100px; 
    object-fit: cover; 
}
.user-photo {
    width: 100px;
    height: 100px;
    border-radius: 50%; /* 将图片裁剪为圆形 */
    object-fit: cover; /* 确保图片以正确比例显示 */
    border: 2px solid #ddd;
}
</style>