// pages/register/register.js
Page({
  data: {
    uid:"",
    password:"",
    code:"",
    open:false, //默认不显示密码
    btndisable:false, //默认不可点击
  },
  // 点击注册按钮
  onTapRegister(){
        wx.request({
          url: 'http://localhost:8080/loginModule/registerGet',
          data:{
              uid:this.data.uid,
              password:this.data.password,
              code:this.data.code
          },
          success(res){
              console.log("请求成功")
              if(res.data==false){
                  console.log("验证码错误或用户名已存在")
              }else{
                  console.log("注册成功")
                  wx.navigateTo({
                    url: '../../pages/login/login',
                  })
              }

          }
        })
  },
    //切换新密码显示函数
    switch(){
        this.setData({
            open:!this.data.open
        })
    },
    //切换按钮点击可否显示函数
    switchbtn () {
        this.setData({
            btndisable:!this.data.btndisable
        })
    },
        /**
   * 获取用户账户
   */
  getUserID:function(e){
    var uid = e.detail.value
    this.setData({
        uid:uid
    })
    },
    getUserPassword(e){
        var password = e.detail.value
        this.setData({
            password:password
        })
    },
    onTapcodeGet(){
        wx.request({
          url: 'http://localhost:8080/loginModule/codeGet',
          data:{
            uid:this.data.uid
          },
          success(res){
              console.log(res.data)
          },
          fail(){
              console.log("获取验证码失败")
          }
        })
    },
        //获取用户输入验证码
        getCodeInput(e){
            var code = e.detail.value
            this.setData({
                code:code
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