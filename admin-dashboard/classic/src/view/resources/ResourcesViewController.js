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
	
	resourcesGridDownloadOne:function(grid, rowIndex, colIndex){
	   Ext.Msg.confirm("提示", "确定要下载吗？", function (button) {
		if (button == "yes") {
		   var record = grid.getStore().getAt(rowIndex);
		   var resId=record.data.resId;
		   var file="resources/downloadone/"+resId;
		   location.href=file;
		}
	   });
	   },
	
	
	resourcesGridDownloadMany:function(btn){
	   Ext.Msg.confirm("提示", "确定要下载吗？", function (button) {
		if (button == "yes") {
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
			var selected = selModel.getSelection();
            var selectIds = []; 
            Ext.each(selected, function (record) {
				selectIds.push(record.data.resId);
            });
			//alert(selectIds.length);
			for(var i=0;i<selectIds.length;i++){
				var file="resources/downloadone/"+selectIds[i];
				var elemIF = document.createElement('iframe');   
				elemIF.src = file;   
				elemIF.style.display = "none";   
				document.body.appendChild(elemIF); 
				
				
			}
		}
	   }
	   })
	},
	
	
	resourcesGridFind:function(btn){
	   var grid = btn.up('gridpanel');
	   var record = grid.getStore();
	   var searchText=Ext.getCmp('resources2').items.getAt(7).getValue();
	   var beginTime=null;
	   var endTime=null;
	   beginTime=Ext.getCmp('resources2').items.getAt(9).getValue();
	   if(beginTime&&beginTime.length!=0){
		endTime=Ext.Date.add(Ext.getCmp('resources2').items.getAt(11).getValue(), Ext.Date.DAY,1);
	   }
	   else{
		   endTime=Ext.getCmp('resources2').items.getAt(11).getValue();
	   }
	   Ext.Ajax.request({ 
			url : 'resources/findByCondition', 
			params : { 
                    resName:searchText,
					beginDate:Ext.util.Format.date(beginTime, 'Y/m/d H:i:s'),
					endDate:Ext.util.Format.date(endTime, 'Y/m/d H:i:s'),
					page:1,
					start:0,
					limit:25,
					sort:'resId',
					dir:'DESC'
			},
			success: function(response, options){
				var tnpdata= Ext.util.JSON.decode(response.responseText) ;
				grid.getStore().loadData(tnpdata.content,false);
			}
			
	   });
	  

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
			}
			
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
   
      resourcesGridDelete: function(btn) {
		var grid = btn.up('gridpanel');
		var selModel = grid.getSelectionModel();
        if (selModel.hasSelection()) {
            Ext.Msg.confirm("警告", "确定要删除吗？", function (button) {
                if (button == "yes") {
                    var selected = selModel.getSelection();
                    var selectIds = []; //要删除的id
                    Ext.each(selected, function (record) {
                        selectIds.push(record.data.resId);
                    });
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