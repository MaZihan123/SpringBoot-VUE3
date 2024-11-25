<template>
    <div>
        <h2>用户信息</h2>
        <ContentField>
            <div>
                <p>手机号: {{ user.phone }}</p>
                <p>邮箱: {{ user.email }}</p>
            </div>
        </ContentField>
    </div>
</template>

<script>
import ContentField from '@/components/ContentField.vue';
import $ from 'jquery';
import { useStore } from 'vuex';

export default {
    components: {
        ContentField
    },
    setup() {
        const store = useStore();
        const user = {
            phone: '',
            email: ''
        };

        // 使用 jQuery 发起 AJAX 请求http://localhost:8080/user/
        $.ajax({
            url: "http://127.0.0.1:8080/user/",
            type: "GET",
            headers: {
                Authorization: "Bearer " + store.state.user.token, // 添加用户的 token
            },
            success(resp) {
                user.phone = resp.phone; // 从返回数据中提取用户手机号
                user.email = resp.email; // 从返回数据中提取用户邮箱
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
h2 {
    font-size: 20px;
    margin-bottom: 10px;
}

p {
    font-size: 16px;
    line-height: 1.5;
    color: #333;
}
</style>