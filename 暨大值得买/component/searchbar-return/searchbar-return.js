// component/searchbar-return/searchbar-return.js
Component({
    properties: {

    },
    data: {
        match:'',
        goods:[]
    },
    methods: {
        onClickReturn:function(){
            wx.switchTab({
              url: '../../pages/home/home',
              success: (res) => {
                  var page = getCurrentPages().pop();
                  page.onLoad();
              },
              fail: (res) => {},
              complete: (res) => {},
            })
        },
        onclickTopSearch(){
            wx.switchTab({
              url: '../search/search',
            })
        },
        OnTapX(){
            this.setData({
                match:''
            })
        },
        getInputValue(e){
            this.setData({
                match:e.detail.value
            })
        },
        OnTapSearch(){
            wx.request({
              url: 'http://localhost:8080/goodsModule/getMatchGoods',
              data:{
                  match:this.data.match
              },
              success:(res)=>{
                  this.setData({
                      goods:res.data
                  })
                  let goodStr = JSON.stringify(this.data.goods)
                  wx.navigateTo({
                    url: '../../pages/searchresult/searchresult?goods='+goodStr,
                  })
              }
            })
        }
    }
})
