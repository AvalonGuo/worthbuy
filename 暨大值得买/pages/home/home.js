// pages/home/home.js
//获取应用实例
Page({
    data: {
        uid:"",
        userfollowList:["1","2"],
        starlist:[0,1],
        navHeight: 0,
        menuButtonInfo: {},
        searchMarginTop: 0, // 搜索框上边距
        searchWidth: 0, // 搜索框宽度
        searchHeight: 0 ,// 搜索框高度
        margin:8,
        startext:"已关注",
        plain:false,
        zelist:["../../images/ze-success.png","../../images/ze-plus.png"],
        checkprice:[0,1,0],
    },
    onLoad() {
        this.setData({
            menuButtonInfo:wx.getMenuButtonBoundingClientRect(),
            uid:wx.getStorageSync('uid')
        })

        const{height,left,top} = this.data.menuButtonInfo
        wx.getSystemInfo({
            success:(res)=>{
                const {statusBarHeight} = res
                const margin = top-statusBarHeight //胶囊与状态栏的上边距
                this.setData({
                    navHeight : statusBarHeight+height+margin*2,
                    searchMarginTop:statusBarHeight+margin,
                    searchHeight:height,
                    searchWidth:left,
                })
            }
        })
        wx.request({
          url: 'http://localhost:8080/goodsModule/FollowsGet',
          data:{
              uid:this.data.uid
          },
          success:(res)=>{
              var checkprice = new Array(res.data.length).fill(0)
              var starlist = new Array(res.data.length).fill(1)
              this.setData({
                  userfollowList:res.data,
                  checkprice:checkprice,
                  starlist:starlist
              })
          }
        })
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
                that.onLoad()
          }
        })
    },
    OnClickComment(e){
        var app = getApp()
        var index = e.currentTarget.dataset.index
        var currentGood = this.data.userfollowList[index]
        app.good.length=0
        app.good.push(currentGood)
        wx.navigateTo({
          url: '../comment/comment',
        })
    },
    onReady() {
    },
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
    //用户点击顶部搜索框,在wxml中使用bindTap关联
    onclickTopSearch(){
        wx.switchTab({
          url: '../search/search',
        })
    },
})