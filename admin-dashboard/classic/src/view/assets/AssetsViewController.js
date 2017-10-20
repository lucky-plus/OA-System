Ext.define('Admin.view.assets.AssetsViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.assetsViewController',

    assetsGridOpenAddWindow: function(btn) {
			Ext.widget('assetsGridWindow',{
				title:'新建资产',
				items: [Ext.apply({xtype: 'assetsGridForm'})]
			});
    },

	assetsGridOpenEditWindow: function(btn) {
  		if(btn.up('panel').assets!=undefined){
			var assets=btn.up('panel').getStore().getAt(btn.up('panel').assets);
			var record=btn.up('panel').assetsFind;
		    var assetsWindow = Ext.widget('assetsGridWindow',{
				title:'修改资产',
				items: [{
					xtype: 'assetsGridForm',
					}]
	  	});
	    //让form加载选中记录
	    assetsWindow.down("form").items.getAt(6).setValue(record.get('userId'));
	    assetsWindow.down("form").getForm().loadRecord(assets);
	    }else{
	    	Ext.Msg.alert('警告','请选择一行数据进行编辑')
	    }
    },

    assetsGridOpenDeleteDate: function(btn) {
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    var selected = selModel.getSelection();
                    var selectIds = []; //要删除的id
                    Ext.each(selected, function (record) {
                        selectIds.push(record.data.assetsId);
                    })
                  	Ext.Ajax.request({
						url : 'assets/delete',
						method : 'post',
						params : {
							assetsIds:selectIds
						},
						success: function(response, options) {
			                var json = Ext.util.JSON.decode(response.responseText);
				            if(json.success){
				            	Ext.Msg.alert('操作成功', json.msg);
				                grid.getStore().reload();
					        }else{
					        	Ext.Msg.alert('操作失败', json.msg);
					        }
			            }
					});

                }
            });
		}
    },

	assetsGridFormSubmit: function(btn) {
		var assetsGridForm = btn.up('form').getForm();
		var win = btn.up('window');
			//this.lookupReference('assetsGrid').store.reload();  //lookupReference配合reference属性
			assetsGridForm.submit( {
				//waitTitle : '请稍后...',
				//waitMsg : '正在保存订单信息,请稍后...',
				url : 'assets/saveOrUpdate',
				method : 'post',
				success : function(form, action) {
					Ext.Msg.alert("提示",action.result.msg);
					win.close();
					Ext.getCmp("assetsGrid").store.reload();
				},
				failure : function(form, action) {
					Ext.Msg.alert("提示",action.result.msg);

				}
			});
    },
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