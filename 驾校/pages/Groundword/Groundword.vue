<template>
	<view>
	<view>
		您当前位于：{{address}}
	</view>
	<view>
		<view class="title">当前第{{current}}题/共{{cityword.length}}题</view>
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
		</radio-group>
		<view class="title" v-if="text===true">题目解析：{{object.text}}</view>
	</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				address:{},
				city:{},
				cityword:{},
				right: {},
				select: '',
				lists: [],
				current: 1,
				object: [],
				isRefresh: true,
				text: false
			}
		},
		onReady(){
			this.isGetLocation();
		},
		methods: {
			// getAuthorizeInfo(a="scope.userLocation"){  //1. uniapp弹窗弹出获取授权（地理，个人微信信息等授权信息）弹窗
			// 		var _this=this;
			// 		uni.authorize({
			// 			scope: a,
			// 			success() { //1.1 允许授权
			// 				_this.getLocationInfo();
			// 			},
			// 			fail(){    //1.2 拒绝授权
			// 				console.log("你拒绝了授权，无法获得周边信息")
			// 			}
			// 		})
			// 	},
				getLocationInfo(){  //2. 获取地理位置
					var _this=this;
					uni.getLocation({
						type: 'wgs84',
						success (res) {
						    console.log("你当前经纬度是：")
							console.log(res)
							let latitude,longitude;
							latitude = res.latitude.toString();
							longitude = res.longitude.toString();
							uni.request({
								header:{
									"Content-Type": "application/text"
								},
								url:'http://apis.map.qq.com/ws/geocoder/v1/?location='+latitude+','+longitude+'&key=GVUBZ-F2MR6-HDGS7-MLZ6N-K7IVV-VMFFS',
								success(re) {
									console.log("中文位置")
									console.log(re)	   
									if(re.statusCode===200){
										console.log("获取中文街道地理位置成功")
										console.log(re.data.result.address_component.city)
										var address = re.data.result.address_component.city
										_this.address = address
										if(_this.address==='青岛市'){
											_this.city = '2'
										}else if(_this.address==='重庆市'){
											_this.city = '1'
										}else if(_this.address==='福建市'){
											_this.city = '3'
										}else{
											_this.city = '4'
										}
										console.log('我是提示'+_this.city)
										console.log('我是提示'+_this.address)
										uni.request({
											url:'http://localhost:8082/user/city',
											data:{
												city:_this.city
											},
											method:"GET",
											success: (res) => {
												console.log(res)
												if(res.data.code === 200){
													var cityword = res.data.projectseven;
													_this.cityword = cityword
													console.log(_this.cityword);
													var object = _this.cityword[0]
													_this.object = object
													var right = res.data.projectseven[0].answer
													_this.right = right
													console.log(res.data.projectseven[0].answer)
												} else {
													uni.showToast({
														title: res.data.msg,
														duration:2000,
													})
												}
												console.log("我成功了!!!!!!!!")
											}
										})
										
									}else{
										console.log("获取信息失败，请重试！")
									}
								 }
							});
						}
					});
				},
				isGetLocation(a="scope.userLocation"){ // 3. 检查当前是否已经授权访问scope属性，参考下截图
					var _this=this;
					uni.getSetting({
					    success(res) {					
							if (!res.authSetting[a]) {  //3.1 每次进入程序判断当前是否获得授权，如果没有就去获得授权，如果获得授权，就直接获取当前地理位置
								_this.getAuthorizeInfo()
							}else{
								_this.getLocationInfo()
							}
						}
					});
				},
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
						if (that.current < that.cityword.length) {
							var that = this;
							var num = that.current + 1
							that.current = num
							console.log(that.current - 1)
							that.right = that.cityword[that.current - 1].answer
							that.object = that.cityword[that.current - 1]
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
					console.log(that.select)
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
