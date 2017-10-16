Ext.define('Admin.view.dept.DepartmentViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.departmentViewController',
	
    deptGridOpenAddWindow: function(btn) {
			Ext.widget('deptWindow',{
				title:'新增部门',
				items: [{xtype: 'deptForm'}]
			});
    },

	deptGridOpenEditWindow: function(btn) {
		var grid = btn.up('gridpanel');//获取Grid视图
		
		var selModel = grid.getSelectionModel();//获取Grid的SelectionModel
        if (selModel.hasSelection()) {//判断是否选中记录
		
           var record = selModel.getSelection()[0];//获取选中的第一条记录
		   
           //创建修改window和form
		   var deptWindow = Ext.widget('deptWindow',{
				title:'修改订单',
				items: [{xtype: 'deptForm'}]
			});
			
		   //让form加载选中记录
           deptWindow.down("form").getForm().loadRecord(record);
        }else{
        	Ext.Msg.alert('提示',"请选择一行数据进行编辑!");
        }
    },
	
	deptGridDeleteDate: function(btn) {
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
		
		
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    var selected = selModel.getSelection();
                    var selectIds = []; //要删除的id
                    Ext.each(selected, function (record) {
                        selectIds.push(record.data.id);
                    });
                  	Ext.Ajax.request({ 
						url : 'dept/delete', 
						method : 'post', 
						params : { 
							ids:selectIds
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
		}else{
        	Ext.Msg.alert('提示',"请选择一行数据进行删除!");
        }
    },
	
	deptGridFormSubmit: function(btn) {
		
		var deptGridForm = btn.up('form').getForm();
		var win = btn.up('window');
			//this.lookupReference('deptGrid').store.reload();  //lookupReference配合reference属性
			deptGridForm.submit( { 
				//waitTitle : '请稍后...', 
				//waitMsg : '正在保存订单信息,请稍后...', 
				url : 'dept/saveOrUpdate', 
				method : 'post', 
				success : function(form, action) { 
					Ext.Msg.alert("提示",action.result.msg); 
					win.close();
					Ext.getCmp("departmentTree").store.reload();
				}, 
				failure : function(form, action) { 
					Ext.Msg.alert("提示",action.result.msg); 
					
				} 
			}); 
    },
	
	deptGridWindowClose: function(btn) {
		var win = btn.up('window');
		if(win){
			win.close();
		}
    }
});