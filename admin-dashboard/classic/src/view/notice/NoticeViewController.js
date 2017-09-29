Ext.define('Admin.view.notice.NoticeViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.NoticeViewController',

    noticeGridAdd: function(bt) {
		var cfg = Ext.apply({
			xtype:'orderWindow'
		},{
			title:'新建公告',
			items:[Ext.apply({xtype:'noticeCompose'})]
		});
		Ext.create(cfg);
    },
	
	
	noticeGridTextSubmit: function(btn) {
		var noticeCompose = btn.up('form').getForm();
		var win = btn.up('window');
		noticeCompose.submit( {  
			url : 'notice/saveOrUpdate', 
			method : 'post', 
			success : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				win.close();
				Ext.getCmp('noticeGrid').store.reload();
			}, 
			failure : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				
			} 
		}); 

   },
   
      noticeGridOpenEditWindow:function(grid, rowIndex, colIndex){
			var record = grid.getStore().getAt(rowIndex);
		   var orderWindow = Ext.widget('orderWindow',{
				title:'修改公告',
				items: [{xtype: 'noticeCompose'}]
			});
		   		//让form加载选中记录
           orderWindow.down("form").getForm().loadRecord(record);
	},
   
      noticeGridWatchWindow:function(grid, rowIndex, colIndex){
			var record = grid.getStore().getAt(rowIndex);
		   var orderWindow = Ext.widget('orderWindow',{
				title:'查看公告',
				items: [{xtype: 'noticeText'}]
			});
		   		//让form加载选中记录
           orderWindow.down("form").getForm().loadRecord(record);
	},
   
   noticeGridDeleteOne:function(grid, rowIndex, colIndex){
	   Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
		if (button == "yes") {
	   var record = grid.getStore().getAt(rowIndex);
	   var noticeId=record.data.noticeId;
	   Ext.Ajax.request({ 
			url : 'notice/deleteone', 
			method : 'post', 
			params : { 
					id:noticeId
			},  
			
	   })
	   grid.getStore().reload();
		}
	   })
   },
   
   
   noticeGridFind:function(btn){
	   var grid = btn.up('gridpanel');
	   var record = grid.getStore();
	   var searchText=Ext.getCmp('xiaotingzi2').items.getAt(5).getValue();
	   var beginTime=Ext.getCmp('xiaotingzi2').items.getAt(7).getValue();
	   var endTime=Ext.Date.add(Ext.getCmp('xiaotingzi2').items.getAt(9).getValue(), Ext.Date.DAY,1);
	   //Ext.getCmp('xiaotingzi2').items.getAt(9).getValue();
	   Ext.Ajax.request({ 
			url : 'notice/findByCondition', 
			params : { 
                    noticeName:searchText,
					beginDate:Ext.util.Format.date(beginTime, 'Y/m/d H:i:s'),
					endDate:Ext.util.Format.date(endTime, 'Y/m/d H:i:s'),
					page:1,
					start:0,
					limit:25,
					sort:'noticeId',
					dir:'DESC'
			},
			success: function(response, options){
				var tnpdata= Ext.util.JSON.decode(response.responseText) ;
				grid.getStore().loadData(tnpdata.content,false);
			}
			
	   });
	  

   },
   
   
   noticeGridDeleteDate: function(btn) {
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    var selected = selModel.getSelection();
                    var selectIds = []; //要删除的id
                    Ext.each(selected, function (record) {
                        selectIds.push(record.data.noticeId);
                    })
                  	Ext.Ajax.request({ 
						url : 'notice/delete', 
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

   
   
   
	noticeGridWindowsClose: function(btn) {
		var win=btn.up('window');
		if(win){
			win.close();
		}
   }
	
});