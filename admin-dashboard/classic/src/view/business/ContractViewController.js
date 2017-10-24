Ext.define('Admin.view.contract.ContractViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.contractViewController',

    fileUpload: function(btn) {
	var contractForm = btn.up('form').getForm();
	var win = btn.up('window');
	contractForm.submit({  
			url : 'contract/upload', 
			method : 'post', 
			enctype:'multipart/form-dat',
			success : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				win.close();
				Ext.getCmp('contractGrid').store.reload();
			}, 
			failure : function(form, action) { 
				Ext.Msg.alert("提示",action.result.msg); 
				
			}
	   })
    },
	
	contractGridDownloadOne:function(grid, rowIndex, colIndex){
	   Ext.Msg.confirm("提示", "确定要下载吗？", function (button) {
		if (button == "yes") {
		   var record = grid.getStore().getAt(rowIndex);
		   var contractId=record.data.contractId;
		   var file="contract/downloadone/"+contractId;
		   location.href=file;
		}
	   });
	   },
	
	
	
	contractGridDeleteOne:function(grid, rowIndex, colIndex){
	   Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
		if (button == "yes") {
	   var record = grid.getStore().getAt(rowIndex);
	   var contractId=record.data.contractId;
	   Ext.Ajax.request({ 
			url : 'contract/deleteone', 
			method : 'post', 
			params : { 
					id:contractId
			}
			
	   });
	   Ext.getCmp('contractGrid').store.reload();
		}
	   })
   },
	   
	
	contractGridDownloadMany:function(btn){
	   Ext.Msg.confirm("提示", "确定要下载吗？", function (button) {
		if (button == "yes") {
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
			var selected = selModel.getSelection();
            var selectIds = []; 
            Ext.each(selected, function (record) {
				selectIds.push(record.data.contractId);
            });
			//alert(selectIds.length);
			for(var i=0;i<selectIds.length;i++){
				var file="contract/downloadone/"+selectIds[i];
				var elemIF = document.createElement('iframe');   
				elemIF.src = file;   
				elemIF.style.display = "none";   
				document.body.appendChild(elemIF); 
				
				
			}
		}
	   }
	   })
	},
	
	
	contractGridFind:function(btn){
	   var grid = btn.up('gridpanel');
	   var record = grid.getStore();
	   var searchText=Ext.getCmp('contractBar').items.getAt(1).getValue();
	   var beginTime=null;
	   var endTime=null;
	   beginTime=Ext.getCmp('contractBar').items.getAt(3).getValue();
	   if(beginTime&&beginTime.length!=0){
		endTime=Ext.Date.add(Ext.getCmp('contractBar').items.getAt(5).getValue(), Ext.Date.DAY,1);
	   }
	   else{
		   endTime=Ext.getCmp('contractBar').items.getAt(5).getValue();
	   }
	   Ext.Ajax.request({ 
			url : 'contract/findByCondition', 
			params : { 
                    company:searchText,
					beginDate:Ext.util.Format.date(beginTime, 'Y/m/d H:i:s'),
					endDate:Ext.util.Format.date(endTime, 'Y/m/d H:i:s'),
					page:1,
					start:0,
					limit:25,
					sort:'contractId',
					dir:'DESC'
			},
			success: function(response, options){
				var tnpdata= Ext.util.JSON.decode(response.responseText) ;
				grid.getStore().loadData(tnpdata.content,false);
			}
			
	   });
	  

   },
	
	contracGridOpenEditWindow:function(grid, rowIndex, colIndex){
			var record = grid.getStore().getAt(rowIndex);
		   var noticeWindow = Ext.widget('contractEditWindow',{
				title:'合同修改',
				items: [{xtype: 'contractForm'}]
			});
		   		//让form加载选中记录
           noticeWindow.down("form").getForm().loadRecord(record);
	},
   
   contractWindowsClose:function(btn) {
		var win = btn.up('window');
		if (win) {
			win.close();
		}
	},
   
     contractGridDelete: function(btn) {
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    var selected = selModel.getSelection();
                    var selectIds = []; //要删除的id
                    Ext.each(selected, function (record) {
                        selectIds.push(record.data.contractId);
                    });
                  	Ext.Ajax.request({ 
						url : 'contract/delete', 
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