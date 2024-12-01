<template>
<template>
    <div class="user-info-container">
        <ContentField>
            

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
                                        <td></td>  
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <button type="button" class="btn btn-secondary">修改</button>
                                            <button type="button" class="btn btn-danger" >删除</button>
                                        </td>
                                    </tr>

                                </tbody>
                                </table>
                       
                    
        </ContentField>
    </div>
    
    
    
</template>


</template>

<script>
import ContentField from '@/components/ContentField.vue'
//import { ref } from 'vue'
//import $ from 'jquery'

export default {
    components: {
        ContentField
    },
       
};
</script>

<style scoped>

</style>