<!--内容们写在这里-->
<template>
    <div class ="container  content-field">
        <div class="card">
            <div class ="card-body">
                <slot></slot>   <!--之后要填充的内容放在这里面-->
            </div>
        </div>
    </div>
</template>



<script>

</script>



<style scoped>
div.content-field{
    margin-top:20px;
}
</style>