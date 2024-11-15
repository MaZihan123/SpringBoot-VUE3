<!--这里是导航栏的组件们-->
<template>
<nav class="navbar navbar-expand-lg navbar-light"><!--lg格式小折叠，大展开-->
  <div class="container-fluid"> <!--自适应宽度100%-->
    <a class="navbar-brand" href="#">重生之我在内大写网页</a>
    
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#">首页</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">购买</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">已结算订单</a>
        </li>
      </ul>

      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#">马梓涵</a>
        </li>
        
      </ul>

      <span class="nav-item">

      </span>
    </div>
  </div>
</nav>
</template>

<script>

</script>

<!--scoped加入之后，样式只在当前组件生效，不会影响到其他组件。 -->
<style scoped>

</style>