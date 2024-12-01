<template>
    <ContentField>
        我的订单
    </ContentField>

</template>

<script>
import ContentField from '@/components/ContentField.vue'
import $ from 'jquery';
//import {useStore} from 'vuex'

export default {
    components: {
        ContentField
    },
    setup()
    {
        //const jwt_token=localStorage.getItem("jwt_token");
        // $.ajax({
        //     url: "http://127.0.0.1:3000/user/merchandise/add/",
        //     type:"POST",
        //     data: {   
        //         mername:"*商品名"
        //         ,mervalue:"*金额",
        //         description:"*描述",
        //         merimg:"*图片网址",
        //     },
        //     headers: {
        //         Authorization: "Bearer " + jwt_token,
        //     },
        //     success(resp)
        //     {
        //         console.log(resp);
        //     },
        //     error(resp)
        //     {
                
        //         console.log(resp);
        //     }
        // })
        //remove
        // const jwt_token=localStorage.getItem("jwt_token");
        // $.ajax({
        //     url: "http://127.0.0.1:3000/user/merchandise/remove/",
        //     type:"POST",
        //     data: {   
        //         mername:"冰红茶",
        //     },
        //     headers: {
        //         Authorization: "Bearer " + jwt_token,
        //     },
        //     success(resp)
        //     {
        //         console.log(resp);
        //     },
        //     error(resp)
        //     {
                
        //         console.log(resp);
        //     }
        // })

        //update
        // const jwt_token=localStorage.getItem("jwt_token");
        // $.ajax({
        //     url: "http://127.0.0.1:3000/user/merchandise/update/",
        //     type:"POST",
        //     data: {   
        //         mername:"冰红茶",
        //         mervalue:"5",
        //         description:"好喝",
        //         merimg:"https://img.alicdn.com/imgextra/i2/2200720004236/O1CN01Zz1Z2o1C5Z2Q6Q6Zz_!!2200720004236.jpg",
                 
        //     },
        //     headers: {
        //         Authorization: "Bearer " + jwt_token,
        //     },
        //     success(resp)
        //     {
        //         console.log(resp);
        //     },
        //     error(resp)
        //     {
                
        //         console.log(resp);
        //     }
        // })      
        //getlist  
        const jwt_token=localStorage.getItem("jwt_token");
        $.ajax({
            url: "http://127.0.0.1:3000/user/merchandise/getlist/",
            type:"GET",
            data: {   
                mername:"冰红茶",
                mervalue:"商品金额",
                description:"商品描述",
                merimg:"商品图片",
                 
            },
            headers: {
                Authorization: "Bearer " + jwt_token,
            },
            success(resp)
            {
                console.log(resp);
            },
            error(resp)
            {
                
                console.log(resp);
            }
        })        
    }
}
</script>

<style>

</style>