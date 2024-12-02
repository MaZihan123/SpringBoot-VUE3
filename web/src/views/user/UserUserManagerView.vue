<template>
    <div class="user-management-container">
        <ContentField>
            <h2>超级用户管理</h2>

            <div class="container">
                <div class="col-12">
                    <div class="card" style="margin-top:20px;">
                        <div class="card-header">
                            <h3>用户列表</h3>
                        </div>

                        <!-- 用户表格 -->
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>订单数量</th>
                                    <th>头像网址</th>
                                    <th>用户等级</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="user in users" :key="user.id">
                                    <td>{{ user.id }}</td>
                                    <td>{{ user.username }}</td>
                                    <td>{{ user.phone }}</td>
                                    <td>{{ user.email }}</td>
                                    <td>{{ user.mernum }}</td>
                                    <td>{{ user.photo }}</td>
                                    <td>{{ user.userlevel }}</td>
                                    <td>
                                        <button type="button" class="btn btn-secondary" @click="editUser(user)" data-bs-toggle="modal" :data-bs-target="'#edit-user-modal-' + user.id">修改</button>
                                        <button type="button" class="btn btn-danger" @click="deleteUser(user)">删除</button>

                                        <!-- 修改用户信息的模态框 -->
                                        <div class="modal fade" :id="'edit-user-modal-' + user.id" tabindex="-1">
                                            <div class="modal-dialog modal-xl">
                                                <div class="modal-content">
                                                    <!-- 模态框头部 -->
                                                    <div class="modal-header">
                                                        <h1 class="modal-title">修改用户信息</h1>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <!-- 模态框主体 -->
                                                    <div class="modal-body">
                                                        <!-- 表单字段 -->
                                                        <div class="mb-3">
                                                            <label class="form-label">用户名</label>
                                                            <input v-model="user.username" type="text" class="form-control" placeholder="请输入用户名">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">密码</label>
                                                            <input v-model="user.password" type="password" class="form-control" placeholder="请输入新密码">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">手机号</label>
                                                            <input v-model="user.phone" type="text" class="form-control" placeholder="请输入手机号">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">邮箱</label>
                                                            <input v-model="user.email" type="email" class="form-control" placeholder="请输入邮箱">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">头像网址</label>
                                                            <input v-model="user.photo" type="text" class="form-control" placeholder="请输入头像网址">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">用户等级</label>
                                                            <input v-model="user.userlevel" type="text" class="form-control" placeholder="请输入用户等级">
                                                        </div>
                                                    </div>
                                                    <!-- 模态框底部 -->
                                                    <div class="modal-footer">
                                                        <div class="error-message text-danger">{{ user.error_msg }}</div>
                                                        <button type="button" class="btn btn-primary" @click="updateUser(user)">保存修改</button>
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- 结束模态框 -->
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </ContentField>
    </div>
</template>

<script>
import ContentField from '@/components/ContentField.vue';
import $ from 'jquery';
//import { reactive, ref } from 'vue';
import { useStore } from 'vuex';
import { Modal } from 'bootstrap/dist/js/bootstrap';
import {ref} from 'vue';

export default {
    components: {
        ContentField
    },
    setup() {
        const store = useStore();

        // 用户列表
        let users = ref([]);

        // 获取用户列表的函数
        const fetchUsers = () => {
            const jwt_token = localStorage.getItem("jwt_token");
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/getuserlist/",
                type: "GET",
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    users.value = resp;
                    console.log("用户列表获取成功");
                    console.log("用户列表:", users.value);
                },
                error(err) {
                    console.error("获取用户列表失败:", err);
                }
            });
        };

        fetchUsers();

        // 编辑用户信息（弹出模态框）
        const editUser = (user) => {
            user.error_msg = "";
            // 如果需要，可以在这里克隆用户对象以避免直接修改
        };

        // 更新用户信息
        const updateUser = (user) => {
            user.error_msg = "";
            const jwt_token = localStorage.getItem("jwt_token");
            const data = {
                id: user.id,
                username: user.username,
                phone: user.phone,
                email: user.email,
                photo: user.photo,
                userlevel: user.userlevel,
            };
            if (user.password && user.password.length > 0) {
                data.password = user.password;
            }
            $.ajax({
                url: "http://127.0.0.1:3000/user/update/",
                type: "POST",
                data: data,
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    if (resp.success_message === 'success') {
                        console.log("用户更新成功");
                        Modal.getInstance('#edit-user-modal-' + user.id).hide();
                        fetchUsers();
                    } else {
                        user.error_msg = resp.error_message;
                    }
                },
                error(err) {
                    console.error("更新用户失败:", err);
                }
            });
        };

        // 删除用户
        const deleteUser = (user) => {
            const jwt_token = localStorage.getItem("jwt_token");
            $.ajax({
                url: "http://127.0.0.1:3000/superuser/user/delete/",
                type: "POST",
                data: {
                    id: user.id,
                },
                headers: {
                    Authorization: "Bearer " + jwt_token,
                },
                success(resp) {
                    if (resp.success_message === 'success') {
                        console.log("用户删除成功");
                        fetchUsers();
                    } else {
                        console.error("删除用户失败:", resp.error_message);
                    }
                },
                error(err) {
                    console.error("删除用户失败:", err);
                }
            });
        };

        return {
            store,
            users,
            editUser,
            updateUser,
            deleteUser,
        };
    }
};
</script>

<style scoped>
div.error-message {
    color: red;
}

.table {
    overflow-x: auto;
    display: block;
}
</style>