Ext.define('Admin.view.authority.AuthorityViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.AuthorityViewController',
	
	roleGridEdit: function(btn) {
		var grid = btn.up('gridpanel');//获取Grid视图
		var selModel = grid.getSelectionModel();//获取Grid的SelectionModel
        if (selModel.hasSelection()) {//判断是否选中记录
           var record = selModel.getSelection()[0];//获取选中的第一条记录
           //创建修改window和form
		   var orderWindow = Ext.widget('authorityGridWindow',{
				title:'修改权限',
				items: [{xtype: 'authorityGridForm'}]
			});
		   		//让form加载选中记录
           orderWindow.down("form").getForm().loadRecord(record);
        }else{
        	Ext.Msg.alert('提示',"请选择一行数据进行编辑!");
        }

   },
   
	authorityGridFormSubmit: function(btn) {
		var orderForm = btn.up('form').getForm();
		var win = btn.up('window');
		orderForm.submit( { 
			//waitTitle : '请稍后...', 
			//waitMsg : '正在保存订单信息,请稍后...', 
			url : 'staff/userRoleUpdate', 
			method : 'post', 
			success : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				win.close();
				Ext.getCmp('authorityGrid').store.reload();
			}, 
			failure : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				
			} 
		}); 

   },
   
   authorityGridFind: function(btn){
	   var grid = btn.up('gridpanel');
	   var record = grid.getStore();
	   var userName=Ext.getCmp('authorityCondition').items.getAt(3).getValue();
	   var realName=Ext.getCmp('authorityCondition').items.getAt(5).getValue();
	   var roleName=Ext.getCmp('authorityCondition').items.getAt(7).getValue();
	   
	   Ext.Ajax.request({ 
			url : 'staff/findUserRoleByCondition.json', 
			params : {
					roleLevel:loginUserRoleLevel,
                    userName:userName,
                    realName:realName,
                    roleName:roleName,
					page:1,
					start:0,
					limit:15,
					sort:'userId',
					dir:'DESC'
			},
			success: function(response, options){
				var tnpdata= Ext.util.JSON.decode(response.responseText) ;
				grid.getStore().loadData(tnpdata.content,false);
			}
			
	   });

   },

	authorityGridWindowClose: function(btn) {
		var win=btn.up('window');
		if(win){
			win.close();
		}
   }
});