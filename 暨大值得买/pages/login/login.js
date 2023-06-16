// pages/login/login.js
const app = getApp()
Page({
  data: {
    uid:'',
    password:''
  },

  // 跳转到忘记密码页面
  onTapForgetPW(){
    wx.navigateTo({
      url: '/pages/forgotpassword/forgotpassword',
    })
  },

  // 跳转到注册页面
  onTapSignup(){
    wx.navigateTo({
      url: '/pages/register/register',
    })
  },

  // 点击登录按钮跳转到主页
  onTapLogin(){
    let that = this
    wx.request({
      url: 'http://localhost:8080/loginModule/loginGet',
      data :{
          uid:that.data.uid,
          password:that.data.password
      },
      success(res){
          wx.setStorageSync('uid', that.data.uid)
          console.log(that.data.uid)
          if(res.data == false){
            console.log("密码或账户名错误!")
          }
          else if(res.data==true){
              wx.switchTab({
                url: '../home/home',
              })
          }
        
      },
      fail(res){
          console.log("请求失败",res.data)
      }
    })
  },
   //获取用户账户
  getUserID:function(e){
      var uid = e.detail.value
      this.setData({
          uid:uid
      })
  },
  /**
   * 获取用户密码
   */
  getUserPassword:function(e){
      var password = e.detail.value
      this.setData({
          password:password
      })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})
