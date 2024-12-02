<template>
    
        <ContentField>
                        <h1>我的订单</h1>
                            <table class="table  ">
                                <thead>
                                    <tr>
                                        <th >ID</th>
                                        <th>商品名称</th>
                                        <th>商品图片</th>
                                        <th>单次消费金额</th>
                                        <th>商品个数</th>
                                        <th>商品描述</th>
                                        
                                        <th>购买时间</th>
                                    </tr>
                                </thead> 
                                <tbody><!--行-->
                                    <tr v-for="order in orders" :key="order.id"><!--VUE循环，绑定关键字-->
                                        <td>{{ order.id }}</td>  
                                        <td>{{ order.mername }}</td>
                                        <td>
                                            <img :src="order.photo" alt="商品图片" style="max-width: 100px; max-height: 100px;" />
                                        </td>
                                        <td>{{ order.mervalue }}</td>
                                        <td>{{ order.quantity }}</td>
                                        <td>{{ order.description }}</td>
                                        
                                        <!--<td>{{ order.merimg }}</td>-->
                                        <td>{{ order.createtime }}</td>
                                    </tr>
                                </tbody>
                                </table>
        </ContentField>
    
</template>

<script>
import ContentField from '@/components/ContentField.vue';
import $ from 'jquery';
import { useStore } from 'vuex';
import { ref } from 'vue';

export default {
    components: {
        ContentField
    },
    setup() {
        //商品
        let orders = ref([]);

        const store = useStore();

        const jwt_token = localStorage.getItem("jwt_token"); 
        $.ajax({
            url: "http://127.0.0.1:3000/myorder/", // 修改为获取订单列表的后端接口
            type: "GET",
            data:{
                userid:store.state.user.id,
            },
            headers: {
                Authorization: "Bearer " + jwt_token,
            },
            success(resp)
            {
                //后端返回一个列表
                console.log("后端返回的数据：", resp);
                orders.value = resp;
                console.log("order get successfuly");
            },
            error(resp)
            {
                console.log("后端返回的数据：", resp);
                console.log(resp);
            }
            
        });

        return {
            orders
        };
    }
}
</script>

<style scoped>

</style>