Ext.define('Admin.view.staff.StaffViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.StaffViewController',

    staffGridOnClick: function(bt) {
		//alert("Add Wiondws");
		var cfg = Ext.apply({
			xtype:'staffWindow'
		},{
			title:'添加员工',
			items:[Ext.apply({xtype:'staffForm'})]
		});
		Ext.create(cfg);
    },
	
	staffGridOpenEditWindow:function(grid, rowIndex, colIndex){
			var record = grid.getStore().getAt(rowIndex);
		   var staffWindow = Ext.widget('staffWindow',{
				title:'修改部门',
				items: [{xtype: 'staffEditForm'}]
			});
		   		//让form加载选中记录
           staffWindow.down("form").getForm().loadRecord(record);
	},
   
	staffGridDelete: function(btn) {
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    var selected = selModel.getSelection();
                    var selectIds = []; //要删除的id
                    Ext.each(selected, function (record) {
                        selectIds.push(record.data.userId);
                    })
                  	Ext.Ajax.request({ 
						url : 'staff/delete', 
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

   },
   
    staffGridDeleteOne:function(grid, rowIndex, colIndex){
	   Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
		if (button == "yes") {
	   var record = grid.getStore().getAt(rowIndex);
	   var userId=record.data.userId;
	   Ext.Ajax.request({ 
			url : 'staff/delete', 
			method : 'post', 
			params : { 
					ids:userId
			},  
			
	   });
	   grid.getStore().reload();
		}
	   })
   },
   
   staffGridEditSubmit: function(btn) {
		var staffForm = btn.up('form').getForm();
		var win = btn.up('window');
		var userId=Ext.getCmp('staffEditForm').items.getAt(0).getValue();
		var postId=Ext.getCmp('staffEditForm').items.getAt(3).getValue();
		staffForm.submit( { 
			url : 'post/updateUserPost', 
			method : 'post', 
			params:{
				userId:userId,
				postId:postId
			},
			success : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				win.close();
				Ext.getCmp('staffGrid').store.reload();
			}, 
			failure : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				
			} 
		}); 

   },
   
   
	staffGridFromSubmit: function(btn) {
		var staffForm = btn.up('form').getForm();
		var win = btn.up('window');
		staffForm.submit( { 
			url : 'staff/saveOrUpdate', 
			method : 'post', 
			success : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				win.close();
				Ext.getCmp('staffGrid').store.reload();
			}, 
			failure : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				
			} 
		}); 

   },
   
   
   staffGridSearch: function(bt) {
		var searchField = this.lookupReference('staffGridSearchField').getRawValue();
		var searchText = this.lookupReference('staffGridSearchText').getValue();
		Ext.Ajax.request({ 
			url : 'staff/findByPage', 
			params : { 
                    realName:searchText,
					deptName:searchField,
					page:1,
					start:0,
					limit:25,
					sort:'userId',
					dir:'DESC'
			},
			success: function(response, options){
				var tnpdata= Ext.util.JSON.decode(response.responseText) ;
				Ext.getCmp('staffGrid').getStore().loadData(tnpdata.content,false);
			}
			
	   });
	},
   
	staffGridWindowsClose: function(btn) {
		var win=btn.up('window');
		if(win){
			win.close();
		}
   }
});