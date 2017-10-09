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
   
	authorityGridWindowClose: function(btn) {
		var win=btn.up('window');
		if(win){
			win.close();
		}
   }
});