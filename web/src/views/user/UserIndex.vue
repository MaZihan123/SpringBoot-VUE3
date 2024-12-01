<template>
    <div class="user-info-container">
        <ContentField>
            <h2>用户信息</h2>
            <div class="user-details">
                <div class="photo-container">
                    <img :src="user.photo" alt="用户头像" class="user-photo" />
                </div>
                <div>
                    <strong>📱 手机号:</strong> {{ user.phone }}
                </div>
                <div>
                    <strong>📧 邮箱:</strong> {{ user.email }}
                </div>
                <div>
                    <strong>📦 现有订单个数:</strong> {{ user.mernum }}
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

export default {
    components: {
        ContentField
    },
    setup() {
        const store = useStore();
        const user = reactive({
            phone: '',
            email: '',
            mernum: 0,
            photo: '' // 用于存储头像 URL
        });

        // 使用 jQuery 发起 AJAX 请求
        $.ajax({
          //url: "http://127.0.0.1:3000/user/",
            url: "http://127.0.0.1:3000/user/",
            type: "GET",
            headers: {
                Authorization: "Bearer " + store.state.user.token,
                
            },
            success(resp) {
                console.log("这里是token",store.state.user.token),
                user.phone = resp.phone;
                user.email = resp.email;
                user.mernum = resp.mernum;
                user.photo = resp.photo; // 假设后端返回字段为 photo
                console.log("用户信息获取成功:", resp);
            },
            error(err) {
                console.error("获取用户信息失败:", err);
            }
        });

        return {
            user
        };
    }
};
</script>

<style scoped>
.user-info-container {
    margin: 20px auto;
    padding: 15px;
    border: 1px solid #ddd;
    border-radius: 5px;
    max-width: 400px;
    background-color: #f9f9f9;
}

h2 {
    text-align: center;
    color: #333;
    font-size: 20px;
    margin-bottom: 10px;
}

.user-details > div {
    margin: 10px 0;
    font-size: 16px;
    color: #555;
}

strong {
    color: #000;
}

.photo-container {
    text-align: center;
    margin-bottom: 15px;
}

.user-photo {
    width: 100px;
    height: 100px;
    border-radius: 50%; /* 将图片裁剪为圆形 */
    object-fit: cover; /* 确保图片以正确比例显示 */
    border: 2px solid #ddd;
}
</style>