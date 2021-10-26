<template>
	<view>
		<view class='pages'>
		  
			<view class='father_view'> 
				<view class='son_view'> 
				  <view class="title-bg">这一刻的想法(最多120个汉字)：</view>
				  <textarea class="textarea-bg" v-model="text" @blur="inputText"  placeholder="请输入内容" /> 
				</view> 
			</view>
			
			<!-- 图片 -->
			<view class="images_box">
				<block v-for="(item, index) in imglist" :key="index">
				  <view class='img-box'>
					<image class='img' :src='item' mode='aspectFill'></image>
					<view class='img-delete' @click='imgDelete1' :data-delindex="index">
					    <image class='img' src='../../static/叉叉.png' ></image> 
					</view>
				  </view>
				</block>
				<view class='img-box' @click='addPic1' v-if="imglist.length<9">
					<image class='img' src='../../static/图片上传.png'></image>  
				</view>
			</view>
			
			<button @click="uploadimage">发布</button>			
		</view>		
	</view>	
</template>

<script>
	export default {
		data() {
			return {
				imglist:[],//选择图片后生成的临时地址数组
				text:'',	
				userInfo:{},
				userName:{}
			}
		},
		
		onLoad() {
			var that = this
			uni.getStorage({
			    key: 'globalUser',
			    success: function (res) {
			        console.log(res.data.id);
				var userInfo = res.data.id;
				that.userInfo = userInfo;
				var userName = res.data.userName
				that.userName = userName
				}
				})
		},
		
		methods: {	
			//*获取文本框内容*//
			inputText:function (e) {
				var that = this;
				this.text = e.detail.value	
				console.log(that.text)
			},
			
			//*选择图片*//
			addPic1: async function() {
				let that = this
				uni.chooseImage({
					count: 1,  // 最多可以选择的图片张数，默认9
					sizeType: ['compressed'], // original 原图，compressed 压缩图，默认二者都有
					sourceType: ['album', 'camera'], // album 从相册选图，camera 使用相机，默认二者都有
					success: function (res) {						
						if (that.imglist.length === 0) {
							that.imglist = res.tempFilePaths
							console.log(res.tempFilePaths[0])
						} else if (that.imglist.length < 9) {
							that.imglist = that.imglist.concat(res.tempFilePaths); //concat追加到数组
						}						
					},
				})
			},
			
			//*显示选择后的图片*//
			imgbox: function (e) {
			    this.imglist = e.detail.value
			},
			
			//* 删除已经选择的图片 *//
			imgDelete1: function (e) {				
				let index = e.currentTarget.dataset.delindex; //获取要删除的图片的下标，否则删除的永远是第一张 ,对应 <view class='img-delect' @click='imgDelete1' :data-delindex="index">
				this.imglist.splice(index, 1);			  	
			},
			
			//*上传图片*//
			uploadimage: function () {
				let app = getApp()
				let that = this;		
				
				if (that.imglist.length != 0) { //数组不为空的时候才执行 		
						
					uni.request({
					    url: 'http://localhost:8082/user/fabu', //上传至 服务器的路径url
					    method: 'GET', 
					    data: {
					        userInfo: that.userInfo,
					        text: that.text,
							imglist: that.imglist[0],
							userName: that.userName
					    },
					    success: (res) => {
							console.log(res)
					        if(res.data.code == 200){
					            uni.showToast({
					                icon:'success',
					                title:'上传成功'
					            })
								uni.navigateTo({
									url:"../quanzi/quanzi",
								})
					        }else{
					            uni.showToast({
					                icon:'none',
					                title:"上传失败",
									duration: 2000
					            })
					        }
					    }
					});
				 
				}else{
					uni.showToast({
					  title: "请选择图片！",
					  icon: 'none',
					  duration: 2000,
					})
				}
			}
		}
	}
</script>

<style>
	page{
	 background-color: rgba(200, 198, 201, 0.527);
	}
	.pages{
	 width: 98%;
	 margin: auto;
	 overflow: hidden;
	}
	
	/* 图片 */
	.images_box{
	 width: 98%;
	 display: flex;
	 flex-direction: row;
	 flex-wrap: wrap;
	 justify-content: flex-start;
	 background-color: white;
	 margin: 10rpx;
	}
	.img-box{ 
	 border: 1rpx;
	 border-style: solid;
	 border-color: rgba(0, 0, 0, 0.452);
	 width: 200rpx;
	 height: 200rpx;
	 margin-left: 25rpx;
	 margin-top: 20rpx;
	 margin-bottom: 20rpx;
	 position: relative;
	}
	.img{
	  width: 100%;
	  height:100%;  
	}
	
	/* 删除图片 */
	.img-delete{
	 width:50rpx;
	 height:50rpx;
	 border-radius:50%;
	 position:absolute;
	 right:-20rpx;
	 top:-20rpx;
	}
	
	button{
	 width: 98%;	
	 margin-top: 20rpx; 
	 background-color: #ffaa00;
	}
	
	.img1{
	 border: 5rpx;
	 border-style: solid;
	 border-color: rgba(0, 0, 0, 0.452);
	 width: 200rpx;
	 height: 200rpx;
	}
	
	.img2{
	  width: 100%;
	  height:100%;
	}
	
	/* 多行文本 */
	textarea {
	  width: 700rpx;
	  height: 250rpx;
	  margin-left: 10rpx;
	  margin-right: 10rpx;
	  margin-top: 10rpx;
	} 
	.textarea-bg {
	  background-color: #ffffff;
	  padding: 10rpx;
	  font-size: 32rpx;
	} 
	.title-bg {
	  font-size: 32rpx;
	  margin-left: 10rpx;
	  margin-right: 10rpx;
	  margin-top: 10rpx;
	  color: #43c729;
	}
</style>
