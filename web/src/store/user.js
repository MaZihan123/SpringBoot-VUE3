import $ from "jquery";
//关于state、getters、mutations、actions的详细解释，参考https://vuex.vuejs.org/zh/guide/state.html
//Vuex是Vue3中的一个状态管理工具，stata是存储应用全局状态的地方
//state存所有全局状态 eg.用户登陆状态、用户信息、用户token等
//getters是从state派生出来的状态，类似于计算属性
//mutations是修改state的方法，但是不能异步操作
//actions是异步操作，可以调用mutations来修改state

export default {
    state: {
        id: "",
        user_name: "",
        photo: "",
        token: "",
        is_login: false,
    },
    getters: {
    },
    mutations: {//修改state
        updateUser(state, user) {
            state.id = user.id;
            state.user_name = user.user_name;
            state.photo = user.photo;
            state.is_login = user.is_login;
        },
        updateUserToken(state, token) {
            state.token = token;
        }
    },
    actions: {//修改state
        //1.
        login(context, data) {
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/token/",
                type: "post",
                data:
                {
                    user_name: data.user_name,
                    password: data.password,
                },
                success(resp) {
                    if (resp.error_message == "success") {
                        context.commit("updateUserToken", resp.token);
                        data.success(resp);
                    }
                    else {
                        data.error(resp);
                    }
                },
                error(resp) {
                    data.error(resp);
                }
            });
        },
        //2.登陆后操作
        getinfo(context, data) {
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/info/",
                type: "get",
                headers: {
                    //授权
                    Authorization: "Bearer " + context.state.token,
                    //认证是Authentication
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        context.commit("updateUser", {
                            ...resp,//解构resp，其中有id、user_name、photo等
                            is_login: true,
                        });
                        data.success(resp);
                    } else {
                        data.error(resp);
                    }
                },
                error(resp) {
                    data.error(resp);
                }
            });


        }
    },
    modules: {
    }
}
