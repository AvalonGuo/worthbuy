// pages/forgotpassward/forgotpassward.js
Page({
    data: {
        uid:"",
        password:"",
        code:"",
        open:false, //默认不显示密码
    },
    //切换新密码显示函数
    switch(){
        this.setData({
            open:!this.data.open
        })
    },
    // 点击确定按钮
    onTaptrue(){
        wx.request({
          url: 'http://localhost:8080/loginModule/fgPassword',
          data:{
              code:this.data.code,
              uid:this.data.uid,
              password:this.data.password      
          },
          success(res){
              if(res.data==true){
                  wx.navigateTo({
                    url: '../login/login',
                  })
              }
              else{
                  console.log("修改密码失败!")
              }
          }
        
        })

    },
    onClickReturn(){
        wx.navigateTo({
          url: '/pages/login/login',
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
    /**
     * 获取用户新密码
     */
    getUserPassword(e){
        var password = e.detail.value
        this.setData({
            password:password
        })
    },
    //获取用户输入验证码
    getCodeInput(e){
        var code = e.detail.value
        this.setData({
            code:code
        })
    },
    //从后端获取验证码
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

    },
})