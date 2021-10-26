<template>
	<view style="width: 100%;">
		<view style="margin-bottom: 120rpx;width: 100%;">
		<view style="margin-left: 2%;font-size: 20px;color: #007AFF;">{{userId.userName}}</view>
		<view style="margin-left: 2%;font-size: 15px;background-color: #F1F1F1;">{{userId.text}}</view>
		<view style="width: 100%;margin-left: 3%;height: 300rpx;margin-top: 20rpx;"><image :src="userId.imglist" style="height: 280rpx;width: 94%;"></image></view>
		<view style="width: 100%;border: 1px solid #EAEAEA;margin-bottom: 40px;">
			<textarea class="textarea-bg" v-model="text" @blur="inputText"  placeholder="请输入评论..." style="float: right; width: 80%;height: 70rpx;line-height: 70rpx;"/> 
			<button style="width: 18%;height: 70rpx;font-size: 15px;line-height: 70rpx;float: left;background-color: #007AFF" @click="Pinglun">评论</button>
		</view>
		<view style="width: 100%;border: 1px solid #EAEAEA;margin-top: 30px;" v-for="(item,index) in lists":key="index">
			<view>{{item.userName}}:{{item.text}}</view>
		</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userId: {},
				lists:[],
				userName:{},
				text:'',
			}
		},
		onLoad(option) {
			console.log(option)
			var that = this
			var userId = option
			that.userId = userId
			uni.getStorage({
			    key: 'globalUser',
			    success: function (res) {
			        console.log(res.data.id);
				var userName = res.data.userName
				that.userName = userName
				}
				}),
			uni.request({
						url: 'http://localhost:8082/user/pinglun',
						data: {
							userId: that.userId.id
						},
						method: "GET",
						success: (res) => {
							if (res.data.code === 200) {
								console.log(res.data.pinglun)
								var lists = res.data.pinglun
								that.lists = lists
							} else {
								uni.showToast({
									title: res.data.msg,
									duration: 2000,
								})
							}
							}
						})
					
				},
				methods: {
					inputText:function (e) {
						var that = this;
						this.text = e.detail.value	
						console.log(that.text)
					},
					Pinglun(){
						var that = this
						uni.request({
									url: 'http://localhost:8082/user/talk',
									data: {
										userId: that.userId.id,
										userName: that.userName,
										text: that.text
									},
									method: "GET",
									success: (res) => {
										if (res.data.code === 200) {
											uni.showToast({
												title: "评论成功",
												duration: 2000,
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

</style>
