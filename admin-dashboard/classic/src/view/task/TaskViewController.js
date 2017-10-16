Ext.define('Admin.view.task.TaskViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.taskViewController',
   
   taskGridAdd: function(bt) {
		//alert("Add Wiondws");
		var cfg = Ext.apply({
			xtype:'taskGridWindow'
		},{
			title:'发布任务',
			items:[Ext.apply({xtype:'taskGridForm'})]
		});
		Ext.create(cfg);
    },
	
	taskGridEdit: function(btn) {
		var grid = btn.up('gridpanel');//获取Grid视图
		var selModel = grid.getSelectionModel();//获取Grid的SelectionModel
        if (selModel.hasSelection()) {//判断是否选中记录
           var record = selModel.getSelection()[0];//获取选中的第一条记录
           //创建修改window和form
		   var orderWindow = Ext.widget('taskGridWindow',{
				title:'修改任务',
				items: [{xtype: 'taskGridForm'}]
			});
		   		//让form加载选中记录
           orderWindow.down("form").getForm().loadRecord(record);
        }else{
        	Ext.Msg.alert('提示',"请选择一行数据进行编辑!");
        }

   },
   
	taskGridDelete: function(btn) {
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    var selected = selModel.getSelection();
                    var selectIds = []; //要删除的id
                    Ext.each(selected, function (record) {
                        selectIds.push(record.data.taskId);
                    })
                  	Ext.Ajax.request({ 
						url : 'task/delete', 
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
   
	taskGridFormSubmit: function(btn) {
		var orderForm = btn.up('form').getForm();
		var win = btn.up('window');
		orderForm.submit( { 
			//waitTitle : '请稍后...', 
			//waitMsg : '正在保存订单信息,请稍后...', 
			url : 'task/saveOrUpdate', 
			method : 'post', 
			success : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				win.close();
				Ext.getCmp('taskGrid').store.reload();
			}, 
			failure : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				
			} 
		}); 

   },
   
	taskGridWindowClose: function(btn) {
		var win=btn.up('window');
		if(win){
			win.close();
		}
   },

   taskGridFind: function(btn){
	   var grid = btn.up('gridpanel');
	   var record = grid.getStore();
	   var userName=Ext.getCmp('taskCondition').items.getAt(5).getValue();
	   var createName=Ext.getCmp('taskCondition').items.getAt(7).getValue();
	   var taskState=Ext.getCmp('taskCondition').items.getAt(8).getValue();
	   var beginTime=null;
	   var endTime=null;
	   beginTime=Ext.getCmp('taskCondition').items.getAt(10).getValue();

	   if(beginTime&&beginTime.length!=0){
			endTime=Ext.Date.add(Ext.getCmp('taskCondition').items.getAt(12).getValue(), Ext.Date.DAY,1);
	   } else {
			endTime=Ext.getCmp('taskCondition').items.getAt(12).getValue();
	   }
	   Ext.Ajax.request({ 
			url : 'task/findByCondition.json', 
			params : { 
                    userName:userName,
                    createName:createName,
                    taskState:taskState,
					beginDate:Ext.util.Format.date(beginTime, 'Y/m/d H:i:s'),
					endDate:Ext.util.Format.date(endTime, 'Y/m/d H:i:s'),
					page:1,
					start:0,
					limit:15,
					sort:'createDate',
					dir:'DESC'
			},
			success: function(response, options){
				var tnpdata= Ext.util.JSON.decode(response.responseText) ;
				grid.getStore().loadData(tnpdata.content,false);
			}
			
	   });
	  

   }
	
});