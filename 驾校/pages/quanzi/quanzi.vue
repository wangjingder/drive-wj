<template>
	<view style="width: 100%;">
		<view style="line-height: 80rpx;height: 80rpx;width: 30%;height: 80rpx;text-align: right;margin-left: 70%;">
			<button style="height: 80rpx;width: 100%; border: none;text-align: right;float: right;" class="FABU-button" @click="toclick"><image src="/static/发布.png" alt="" style="width: 55rpx;height:55rpx;"></button>
		</view>
		<view style="margin-bottom: 120rpx;width: 100%;" v-for="(item,index) in lists":key="index" @click="toclicktwo(index)">
		<view style="margin-left: 2%;font-size: 20px;color: #007AFF;">{{item.userName}}</view>
		<view style="margin-left: 2%;font-size: 15px;background-color: #F1F1F1;">{{item.text}}</view>
		<view style="width: 100%;margin-left: 3%;height: 300rpx;margin-top: 20rpx;"><image :src="item.imglist" style="height: 280rpx;width: 94%;"></image></view>
		<view style="width: 100%;border: 1px solid #EAEAEA;"><button style="width: 18%;height: 70rpx;font-size: 15px;line-height: 70rpx;float: left;background-color: #007AFF" @click="toclicktwo(index)">评论</button></view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				lists:{}
			}
		},
		onLoad() {
			uni.request({
				url: 'http://localhost:8082/user/pengyouquan',
				data: {},
				success: (res) => {
					if (res.data.code != 200) {
						console.log("请求失败")
					} else {
						var that = this
						var lists = res.data.data
						that.lists = lists
						console.log(lists)
					}
				}
			})
		},
		methods: {
			toclick() {
				uni.navigateTo({
					url: '/pages/quanzi-fabu/quanzi-fabu'
				})
			},
			toclicktwo(index) {
				var that = this;
							uni.navigateTo({
								url: '/pages/xiangqing/xiangqing?id='+that.lists[index].id+"&userName="+that.lists[index].userName+"&text="+that.lists[index].text+"&imglist="+that.lists[index].imglist
							})
		},
	},
	}
</script>

<style>
	.FABU-button {
		border: none;
		outline: 0;
		background-color: white;
	}
	.FABU-button::after {
		border: none;
	}
</style>
