Ext.define('Admin.view.dept.DepartmentViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.departmentViewController',
	
    deptGridOpenAddWindow: function(btn) {
			Ext.widget('deptWindow',{
				title:'新增部门',
				items: [{xtype: 'deptForm'}]
			});
    },

	
	deptGridDeleteOne:function(tree,rowIndex, colIndex){
	   Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
		if (button == "yes"&&tree.up('panel').deptment!=undefined) {
			   var record=tree.up('panel').getStore().getAt(tree.up('panel').deptment).get('id');
			   Ext.Ajax.request({ 
					url : 'dept/delete', 
					method : 'post', 
					params : { 
							deptId:record
					},  
				
		   });
		tree.up('panel').getStore().reload();
		tree.up('panel').deptment=undefined;
		Ext.getCmp("postForm").items.getAt(1).store.reload();
		}else{
			Ext.Msg.alert('警告','请选择一行数据进行编辑')
		}
	   })
   },
	  
	  deptGridOpenEditWindow:function(tree, rowIndex, colIndex){
		  if(tree.up('panel').deptment!=undefined){
			var parentId=tree.up('panel').getStore().getAt(tree.up('panel').deptment);
			var record=tree.up('panel').deptFind;
		   var deptWindow = Ext.widget('deptWindow',{
				title:'修改部门',
				items: [{
					xtype: 'deptForm',
					}]
			});
		  //让form加载选中记录
		  deptWindow.down("form").items.getAt(0).setValue(record.get('id'));
		  deptWindow.down("form").getForm().loadRecord(parentId);
          deptWindow.down("form").items.getAt(2).setValue(record.get('text'));
		  }else{
			  Ext.Msg.alert('警告','请选择一行数据进行编辑')
		  }
	},
	
	
	deptGridFormSubmit: function(btn) {
		
		var deptGridForm = btn.up('form').getForm();
		var win = btn.up('window');
			deptGridForm.submit( { 
				url : 'dept/saveOrUpdate', 
				method : 'post', 
				success : function(form, action) { 
					Ext.Msg.alert("提示",action.result.msg); 
					win.close();
					Ext.getCmp("departmentTree").store.reload();
					Ext.getCmp("postForm").items.getAt(1).store.reload();
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
    },
	
	
	postGridFormSubmit: function(btn) {
		var deptGridForm = btn.up('form').getForm();
		var win = btn.up('window');
			deptGridForm.submit( { 
				url : 'post/saveOrUpdate', 
				method : 'post', 
				success : function(form, action) { 
					Ext.Msg.alert("提示",action.result.msg); 
					Ext.getCmp("postGrid").store.reload();
					Ext.getCmp("postForm").getForm().reset();
				}, 
				failure : function(form, action) { 
					Ext.Msg.alert("提示",action.result.msg); 
					
				} 
			}); 
    },
	
	postGridDeleteOne:function(grid, rowIndex, colIndex){
	   Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
		if (button == "yes") {
			var record=Ext.getCmp("postGrid").deleteId;
			   Ext.Ajax.request({ 
					url : 'post/delete', 
					method : 'post', 
					params : { 
							postId:record.get('postId')
					},  
				success: function(response, options) {
			                var json = Ext.util.JSON.decode(response.responseText);
				            if(json.success){
				            	Ext.Msg.alert('操作成功', json.msg);
				               	Ext.getCmp("postGrid").store.reload();
								Ext.getCmp("postForm").getForm().reset();
					        }else{
					        	Ext.Msg.alert('操作失败', json.msg);
					        }
			     }
		   });
		
		}else{
			Ext.Msg.alert('警告','请选择一行数据进行编辑')
		}
	   })
   },
});