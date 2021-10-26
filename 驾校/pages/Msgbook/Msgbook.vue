<template>
	<view>
		<view v-if="flag===1">
			<view>题目：{{object.test}}</view>
			<radio-group @change="radioChange">
				<view>
					<radio value="A" v-if="isRefresh" />
					<text>A:{{object.choose_a}}</text>
				</view>
				<view>
					<radio value="B" v-if="isRefresh" />
					<text>B:{{object.choose_b}}</text>
				</view>
				<view>
					<radio value="C" v-if="isRefresh" />
					<text>C:{{object.choose_c}}</text>
				</view>
				<view>
					<radio value="D" v-if="isRefresh" />
					<text>D:{{object.choose_d}}</text>
				</view>
				<button type="button" @click="Checkboxone">下一题</button>
				<view class="title" v-if="text===true">题目解析：{{object.text}}</view>
			</radio-group>
		</view>


		<view v-else-if="flag===2">
			<!-- <view class="title">当前第{{current}}题/共{{result2.length}}题</view> -->
			<view class="uni-padding-wrap">
				<view class="uni-title uni-common-mt">
					题目:{{object.test}}
				</view>
			</view>
			<view class="uni-list">
				<checkbox-group @change="checkboxChange">
					<view>
						<checkbox value="A" v-if="isRefresh" />
						<text>A:{{object.choose_a}}</text>
					</view>
					<view>
						<checkbox value="B" v-if="isRefresh" />
						<text>B:{{object.choose_b}}</text>
					</view>
					<view>
						<checkbox value="C" v-if="isRefresh" />
						<text>C:{{object.choose_c}}</text>
					</view>
					<view>
						<checkbox value="D" v-if="isRefresh" />
						<text>D:{{object.choose_d}}</text>
					</view>
				</checkbox-group>
				<button type="button" @click="Checkbox">提交</button>
				<button type="button" @click="Checkboxone">下一题</button>
				<view class="title" v-if="text===true">题目解析：{{object.text}}</view>
			</view>
		</view>


		<view v-else="flag===3">
			<!-- <view class="title">当前第{{current}}题/共{{result3.length}}题</view> -->
			<view>题目：{{object.test}}</view>
			<radio-group @change="radioChangeone">
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
	</view>
</template>

<script>
	export default {
		data() {
			return {
				subjectNow: [],
				object: {},
				flag: 1,
				current: 1,
				isRefresh: true,
				select: '',
				right: {},
				text: false
			}
		},
		onShow() {
			// var subjectNow = getStorageSync("Msg");
			// this.subjectNow = subjectNow
			var that = this
			uni.getStorage({
				key: 'Msg',
				success: function(res) {
					// console.log(res.data);
					var subjectNow = res.data;
					that.subjectNow = subjectNow
					console.log(subjectNow);
				}
			});
			console.log(that.subjectNow)
			var object = that.subjectNow[0]
			that.object = object
			var right = that.subjectNow[0].answer
			that.right = right
			console.log(object)
			console.log(that.subjectNow[0].answer)
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
			radioChangeone(res) {
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
			checkboxChange(res) {
				var that = this;
				var select = res.detail.value
				that.select = select
				console.log('用户选择了', res.detail.value)
			},
			Checkbox() {
				var that = this;
				var count = 0
				// console.log(that.select[0])
				// console.log(that.right[0])
				// console.log(that.select.length)
				// console.log(that.right.length)
				if (that.select.length == that.right.length) {
					for (var i = 0; i < that.select.length; i++) {
						if (that.select[i] !== that.right[i]) {
							uni.showToast({
								title: '正确答案是:' + that.right,
								duration: 2000,
							})
							that.text = true
							break;
						} else {
							count++;
						}
					}
					if (count == that.right.length) {
						uni.showToast({
							title: '正确！',
							duration: 2000,
						})
					}
				} else {
					console.log(that.select[0])
					console.log(that.right[0])
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
				// var select = ""
				// that.select = select
				if (that.select != "") {
					if (that.current < that.subjectNow.length) {
						var that = this;
						var num = that.current + 1
						that.current = num
						var object = that.subjectNow[num - 1]
						that.object = object
						var right = that.subjectNow[num - 1].answer
						that.right = right
						if (that.subjectNow[num - 1].type == '单选') {
							that.flag = 1
						} else if (that.subjectNow[num - 1].type == '多选') {
							that.flag = 2
						} else if (that.subjectNow[num - 1].type == '判断') {
							that.flag = 3
						}
						// that.right = that.subjectNow[that.current1-1].answer
						// that.object = that.subjectNow[that.current1-1]

						console.log(that.flag)
						// var percent=((num - 1)/(result1.length+result2.length+result3.length) * 100).toFixed(2)
						// that.percent = percent
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

</style>
