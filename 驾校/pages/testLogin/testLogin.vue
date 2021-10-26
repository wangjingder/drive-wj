<template>
	<view class="body">
		<form action="" @submit="testformSubmit">
		<view class="info-wapper">
			<label class="words-lbl">账号</label>
			<input type="text" class="input" name="userName" placeholder="请输入用户名" placeholder-class="graywords">
		</view>
		<view class="info-wapper" style="margin-top: 40upx;">
			<label class="words-lbl">密码</label>
			<input type="text" class="input" name="password" placeholder="请输入密码" placeholder-class="graywords">
		</view>
		<view class="infa-wapper">
			<label class="words-lbl">手机号</label>
			<input type="text" class="input" name="phone" placeholder="请输入手机号" placeholder-class="graywords">
		</view>
		<view class="infa-wapper">
			<label class="words-lbl">真实姓名</label>
			<input type="text" class="input" name="name" placeholder="请输入真实姓名" placeholder-class="graywords">
		</view>
		<button type="primary" style="margin-top: 60upx;width: 90%;" form-type="submit">注册</button>
		</form>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				text:'',
			}
		},
		methods: {
			testformSubmit(e){
				var that = this;
				var userName = e.detail.value.userName;
				var password = e.detail.value.password;
				var phone = e.detail.value.phone;
				var name = e.detail.value.name;
				//请求
				uni.request({
					url:'http://localhost:8082/user/register',
					data:{
						userName:userName,
						password:password,
						phone:phone,
						name:name,
					},
					method:"GET",
					success: (res) => {
						if(res.data.code === 200){
							uni.navigateTo({
								url:"../Login/Login"
							})
						}else{
							uni.showToast({
								title: '当前用户名已存在！！！',
								duration:2000,
							})
						}
					},
					fail: (err) =>{
						alert("注册失败")
					}	
				})
			}
		}
	}
</script>

<style>
.body{
	border-top: 1px solid #DBDBDA;
	padding: 0 40upx;
}
.face-wapper{
	display: flex;
	flex-direction: row;
	justify-content: center;
	margin-top: 120upx;
	margin-bottom: 120upx;
}
.face{
	height: 160upx;
	width: 160upx;
	border: 1px solid black;
}
.info-wapper{
	display: flex;
	flex-direction: row;
	justify-content: center;
	border-bottom: 1px solid #DBDBDA;
	padding-bottom: 20upx;
}
.words-lbl{
	color: #808080;
}
.input{
	width: 500upx;
	margin-left: 40upx;
}
.graywords{
	color: #EAEAEA;
}
.infa-wapper{
	display: flex;
	flex-direction: row;
	justify-content: center;
	border-bottom: 1px solid #DBDBDA;
	padding-bottom: 20upx;
	padding-top: 40upx;
}
</style>
