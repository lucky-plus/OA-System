Ext.define('Admin.view.myAssets.MyAssetsViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.myAssetsViewController',
    
    assetsPanelSearch:function(btn){
		var searchField = this.lookupReference('assetsSearchField').getValue();
		var searchText = this.lookupReference('assetsSearchText').getValue();
		var store = Ext.getCmp('assetsGrid').getStore();//对应grid的id属性
		//1.清空所有QueryDTO中的查询条件
		Ext.apply(store.proxy.extraParams, {
			assetsName:'',
			assetsNumber:''
		});
		//2.按照所选字段进行查询参数（条件）的扩展
		if(searchField=='assetsNumber'){
			Ext.apply(store.proxy.extraParams, {
				assetsNumber:searchText,
			});
		}
		if(searchField=='assetsName'){
			Ext.apply(store.proxy.extraParams, {
				assetsName:searchText
			});
		}
		store.load();
	},

	showAssetsSearchWindow:function(btn){
		Ext.widget('assetsSearchWindow').show();//autoShow
		},

	assetsSearchFormSubmit:function(btn){
		var store = Ext.getCmp('assetsGrid').getStore();
		//1.清空所有查询条件
		Ext.apply(store.proxy.extraParams, {
			assetsNumber:'',
			assetsName:'',
			assetsType:'',
			lowPrice:'',
			highPrice:'',
			beginDate:'',
			endDate:''
		});
		//2.按照所选字段进行查询参数（条件）的扩展
		Ext.apply(store.proxy.extraParams, {
			assetsName:this.lookupReference('assetsSearchForm-assetsName').getValue(),
			assetsType:this.lookupReference('assetsSearchForm-assetsType').getValue(),
			lowPrice:this.lookupReference('assetsSearchForm-lowPrice').getValue(),
			highPrice:this.lookupReference('assetsSearchForm-highPrice').getValue(),
			beginDate:Ext.util.Format.date(this.lookupReference('assetsSearchForm-beginDate').getValue(), 'Y/m/d H:i:s'),
			endDate:Ext.util.Format.date(this.lookupReference('assetsSearchForm-endDate').getValue(), 'Y/m/d H:i:s')
		});
		store.load();
		btn.up('window').hide();
	},

	assetsGridWindowClose: function(btn) {
		var win = btn.up('window');
		if(win){
			win.close();
		}
    }
});