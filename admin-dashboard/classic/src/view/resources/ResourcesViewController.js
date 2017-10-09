Ext.define('Admin.view.resources.ResourcesViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.resourcesViewController',

    fileUpload: function(btn) {
	var resourcesForm = btn.up('form').getForm();
	var win = btn.up('window');
	resourcesForm.submit({  
			url : 'resources/upload', 
			method : 'post', 
			enctype:'multipart/form-dat',
			success : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				win.close();
				Ext.getCmp('resourcesGrid').store.reload();
			}, 
			failure : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				
			}
	   })
    },
	
	
	resourcesGridDeleteOne:function(grid, rowIndex, colIndex){
	   Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
		if (button == "yes") {
	   var record = grid.getStore().getAt(rowIndex);
	   var resId=record.data.resId;
	   Ext.Ajax.request({ 
			url : 'resources/deleteone', 
			method : 'post', 
			params : { 
					id:resId
			},  
			
	   });
	   grid.getStore().reload();
		}
	   })
   },
   
   orderGridWindowsClose:function(btn) {
		var win = btn.up('window');
		if (win) {
			win.close();
		}
	},
   
      resourcesGridDeleteDate: function(btn) {
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    var selected = selModel.getSelection();
                    var selectIds = []; //要删除的id
                    Ext.each(selected, function (record) {
                        selectIds.push(record.data.resId);
                    })
                  	Ext.Ajax.request({ 
						url : 'resources/delete', 
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
		}
	}
	
});