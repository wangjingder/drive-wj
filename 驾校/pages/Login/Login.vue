<template>
	<view class="body">
		<form action="" @submit="formSubmit">
			<view class="face-wapper">
				<image src="../../static/img/1.jpg" class="face">

				</image>
			</view>
			<view class="info-wapper">
				<label class="words-lbl">账号</label>
				<input type="text" class="input" name="userName" placeholder="请输入用户名" placeholder-class="graywords">
			</view>
			<view class="info-wapper" style="margin-top: 40upx;">
				<label class="words-lbl">密码</label>
				<input type="text" class="input" name="password" password="true" placeholder="请输入密码"
					placeholder-class="graywords">
			</view>
			<button type="primary" style="margin-top: 60upx;width: 90%;" form-type="submit">登录</button>
			<button type="primary" style="margin-top: 60upx;width: 90%;" @click="Loginchange">注册</button>
		</form>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {
			Loginchange() {
				uni.navigateTo({
					url: '../testLogin/testLogin'
				})
			},
			formSubmit(e) {
				var that = this;
				var userName = e.detail.value.userName;
				var password = e.detail.value.password;
				console.log(e.detail.value.userName)
				console.log(password)
				//请求

				uni.request({
					url: 'http://localhost:8082/user/loginapp',
					data: {
						userName: userName,
						password: password
					},
					contentType: 'application/json',
					method: "GET",
					success: (res) => {
						if (res.data.code === 200) {
							var userInfo = res.data.user;
							uni.setStorageSync("globalUser", userInfo)
							console.log(userInfo);
							uni.switchTab({
								url: "../index/index"
							})
						} else {
							uni.showToast({
								title: res.data.msg,
								duration: 2000,
							})
						}
					}
				})
			}
		}
	}
</script>

<style>
	.body {
		border-top: 1px solid #DBDBDA;
		padding: 0 40upx;
	}

	.face-wapper {
		display: flex;
		flex-direction: row;
		justify-content: center;
		margin-top: 120upx;
		margin-bottom: 120upx;
	}

	.face {
		height: 160upx;
		width: 160upx;
	}

	.info-wapper {
		display: flex;
		flex-direction: row;
		justify-content: center;
		border-bottom: 1px solid #DBDBDA;
		padding-bottom: 20upx;
	}

	.words-lbl {
		color: #808080;
	}

	.input {
		width: 500upx;
		margin-left: 40upx;
	}

	.graywords {
		color: #EAEAEA;
	}
</style>
