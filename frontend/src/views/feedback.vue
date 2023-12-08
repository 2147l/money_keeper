<template>
    <div class="feedback">
        <!-- 标题 -->
        <div class="title">
            <div class="exit"><img src="../assets/image/左箭头.png" alt="" @click="goBack"></div>
            意见反馈
        </div>
        <!-- 下面填充反馈内容 -->
        <div></div>
        <!-- 发送模块 -->
        <div class="send">
            <!-- 文本输入 -->
            <textarea name="" id="" cols="30" rows="10" placeholder="请输入"></textarea>
            <!-- 发送按钮 -->
            <button>发送</button>
        </div>

    </div>
</template>
<script>
export default {
    data() {
        return {
            id: this.$route.query.id
        }
    },
    methods: {
        // 返回上一页
        goBack() {
            this.$router.go(-1)
        }
    }
}
</script>
<style scoped>
.feedback {
    width: 390px;
    height: 844px;
    background-color: #F6F6F6;
}

.title {
    position: relative;
    width: 390px;
    height: 60px;
    background-color: #FFD946;
    text-align: center;
    font-size: 20px;
    color: #101010;
    line-height: 60px;
}


.exit {
    position: absolute;
    margin-left: 15px;
    float: left;
}

.send {
    margin-top: 700px;
}

.send textarea {
    float: left;
    margin-left: 11px;
    width: 304px;
    height: 57px;
    background-color: #fff;
    border-radius: 10px;
}

.send button {
    float: right;
    margin-right: 8px;
    width: 58px;
    height: 57px;
    color: #fff;
    border-radius: 10px;
}
</style>