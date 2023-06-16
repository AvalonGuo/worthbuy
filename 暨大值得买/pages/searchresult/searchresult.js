
Page({
    data: {
        goods:[],
        uid:"",
        userfollowList:["1","2"],
        starlist:[0,1],
        startext:"已关注",
        plain:false,
        zelist:["../../images/ze-success.png","../../images/ze-plus.png"],
        checkprice:[0,1,0],
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.setData({
            goods:JSON.parse(options.goods),
            uid:wx.getStorageSync('uid')
        })
        console.log(this.data.goods)
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
    onClickHtryprice:function(e){
        let that = this
        var checkprice = this.data.checkprice
        if(checkprice[e.currentTarget.id]==0){
            wx.request({
              url: 'http://localhost:8080/goodsModule/htryPriceGet',
              data:{
                Id:e.currentTarget.dataset.goodid,
              },
              success(){
                  checkprice[e.currentTarget.id]=1
                  that.setData({
                      checkprice:checkprice,
                  })
                  
              }
            })
        }else{
            checkprice[e.currentTarget.id]=0
            that.setData({
                checkprice:checkprice,
            })
        }
    },
    getStarInfo:function(e){
        var that = this
        var starlist = that.data.starlist
        var uid = that.data.uid
        var index =  e.currentTarget.dataset.index
        var goodID = e.currentTarget.dataset.goodid
        console.log(goodID)
        var startext = this.data.startext
        starlist[index] =  starlist[index]==1?0:1,
        that.setData({
            starlist:starlist
        })
        wx.request({
          url: 'http://localhost:8080/goodsModule/getStarInfo',
          data:{
              star:starlist[index],
              uid:uid,
              goodID:goodID
          },
          success(){
          }
        })
    },
    OnTapReturn(){
        wx.switchTab({
          url: '../../pages/search/search',
        })
    }
})