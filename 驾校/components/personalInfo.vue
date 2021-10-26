<template>
	<view class="personalInfo">
		<view class="background-img-box">
			<image class="background-img" src="../static/img/1.jpg" mode="">
			</image>
		</view>
		<view class="top key-cell">
			<view v-if="userIsLogin">
				<view>
				{{userInfo.name}}先生
				</view>
			</view>
			<view v-else>
				<navigator url="/pages/Login/Login">
				<view>
				点击登录
				</view>
				</navigator>
			</view>
		</view>
		<view class="list">
			<view class="list-box" @click="changeInfo">
				<view class="left">	<image src="/static/个人资料.png" alt="" style="width: 55rpx;height:55rpx;vertical-align: middle;margin-right: 15px;" class="image_saoyisao"></image>个人资料</view>
				<view class="right" style="color: #999999;margin-right: 10px;">></view>
			</view>
			<view class="list-box" @click="changeInfoMsg">
				<view class="left"><image src="/static/错题.png" alt="" style="width: 55rpx;height:55rpx;vertical-align: middle;margin-right: 15px;" class="image_saoyisao"></image>错题本</view>
				<view class="right" style="color: #999999;margin-right: 10px;">></view>
			</view>
		</view>	
	</view>

</template>

<script>
	export default {
		data() {
			return {
				userIsLogin:true ,
				userInfo:{}
			}
		},
		created() {
			console.log("A")
			var that = this;
			// var userInfo = getStorageSync("globalUser");
			// this.userInfo = userInfo
			uni.getStorage({
			    key: 'globalUser',
			    success: function (res) {
			        console.log(res.data);
				var userInfo = res.data;
			if(userInfo != null && userInfo != "" && userInfo != undefined){
				that.userIsLogin = true;
				that.userInfo = userInfo;
			}else{
				that.userIsLogin = false;
				that.userInfo = {};
						}
					}
				});
		},
		methods: {
			changeInfo(){
				uni.navigateTo({
					url:'/pages/payList/payList'
				})
			},
			changeInfoMsg(){
				uni.navigateTo({
					url:'/pages/Msgbook/Msgbook'
				})
			}
		}
	}
</script>

<style>
	.key-cell{
					height:20px;
					width:20px;
		            border-radius: 5px;	
		        }
		
	 .key-cell:active{
		            color: white;   //点击颜色变为白色
		            border-radius: 5px;
		            background: #61CBD6;  //黑色
		            opacity: 0.5;    //这里重要，就是通过这个透明度来设置
		        }
	.personalInfo{
		width: 100%;
	}
	.background-img-box{
		width: 100%;
		padding: 0 1px;
		z-index: 19;
		}
	.background-img{
		 width: 100%;
	}
	.top{
		width: 100%;
		position: absolute;
		top: 140px;
		left: 0;
		text-align: center;
		z-index: 20;
		height: 120px;
		background: white;
	}
	.personal-img-box{
		margin: -25px 0 0 0px;
	}
	.personal-img{
		height: 80px;
		width: 80px;
		border-radius: 50%;
		z-index: 20;
		border: 3.5px solid white;
	}
	.list{
		margin-top: 40px;
		padding: 5rpx 2px;
	}
	.list-box{
		width: 100%;
		height: 45px;
		line-height: 45px;
		border: 1px solid #dedede;
		margin:0 0 -1px -1px;
		position: relative; 
		z-index: 0;
	}
	.left{
		float: left;
		font-size: 15px;
		margin-left: 10px;
	}
	.right{
		float: right;
		font-size: 15px;
		width: 10px;
	}
</style>
