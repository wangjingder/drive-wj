<template>
	<view>
		<view class="title">当前第{{current}}题/共{{lists.length}}题</view>
		<view>题目：{{object.test}}</view>
		<radio-group @change="radioChange">
			<view>
				<radio value="T" v-if="isRefresh" />
				<text>T</text>
			</view>
			<view>
				<radio value="F" v-if="isRefresh" />
				<text>F</text>
			</view>
			<button type="button" @click="Checkboxone">下一题</button>
			<view class="title" v-if="text===true">题目解析：{{object.text}}</view>
		</radio-group>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				right: {},
				select: '',
				lists: [],
				current: 1,
				object: [],
				isRefresh: true,
				text: false
			}
		},
		onLoad() {
			var that = this;
			uni.request({
				url: 'http://localhost:8082/user/testsix',
				data: {},
				success: (res) => {
					if (res.data.code != 200) {
						console.log("请求失败")
					} else {
						that.lists = res.data.data
						var object = that.lists[0]
						that.object = object
						var right = res.data.data[0].answer
						that.right = right
						console.log(res.data.data[0].answer)
					}
				}
			})
		},
		methods: {
			radioChange(res) {
				var that = this;
				var select = res.detail.value
				that.select = select
				console.log('用户选择了', select)
				if (that.select == this.right) {
					uni.showToast({
						title: '正确！',
						duration: 2000,
					})
				} else {
					uni.showToast({
						title: '正确答案是:' + that.right,
						duration: 2000,
					})
					that.text = true
				}
			},
			Checkboxone() {
				var that = this;
				this.reload()
				if (that.select != "") {
					if (that.current < that.lists.length) {
						var that = this;
						var num = that.current + 1
						that.current = num
						console.log(that.current - 1)
						that.right = that.lists[that.current - 1].answer
						that.object = that.lists[that.current - 1]
					} else {
						uni.navigateTo({
							url: "../notice/notice"
						})
					}
				} else {
					uni.showToast({
						title: '请选择答案',
						duration: 2000,
						icon: 'none'
					})
				}
				that.text = false
				var select = ""
				that.select = select
			},
			reload() {
				this.isRefresh = false
				this.$nextTick(() => {
					this.isRefresh = true
				})
			}
		}
	}
</script>

<style>
	.title {
		text-align: center;
		color: red;
		margin: 15rpx;
		font-size: 45rpx;
	}
</style>
