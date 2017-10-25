/* 
* @Author: xgd
* @Date:   2017-10-22 22:32:03
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 22:32:03
*/


Ext.define('Admin.view.activiti.activitiProcessGridViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.activitiProcessGridViewController',
	
	startProcessInstance: function(grid, rowIndex, colIndex){
		var recordId = grid.getStore().getAt(rowIndex).getId();
		var arr = new Array();
		arr.push({ 
			name :'userId',
			value:loginUserId
		});
		var Mdata = {
			processDefinitionId: recordId,
			businessKey: "myBusinessKey",
			variables:arr
		};
		Ext.Ajax.request({
			url: 'runtime/process-instances',
			method:'POST',
			params:Ext.encode(Mdata), 
			headers : {
				'Content-Type':'application/json'
			},
			success: function(response) {
				Ext.toast("开启实例成功");
			},
			failure: function(response) {
				Ext.toast("失败了~");
			}
		});
	},
	activateProcess : function(grid, rowIndex, colIndex){
		var recordId = grid.getStore().getAt(rowIndex).getId();
		var Mdata = {
			action: "suspend"
		};
		Ext.Ajax.request({
			url: 'repository/process-definitions/'+recordId,
			method:'PUT',
			params:Ext.encode(Mdata), 
			headers : {
				'Content-Type':'application/json'
			},
			success: function(response) {
				Ext.toast("激活成功");
			},
			failure: function(response) {
				Ext.toast("传火失败");
			}
		});
	},
	showDetails : function(grid, rowIndex, colIndex){
	    var recordId = grid.getStore().getAt(rowIndex).getId();
	    var detail = undefined;
	    Ext.Ajax.request({url:'repository/process-definitions/' + recordId, method:'GET', 
	    success:function(response) {
			detail = Ext.util.JSON.decode(response.responseText);
			if (detail != undefined) {
				var cfg = Ext.widget('activitiWindow', {title:'流程定义详情', items:[Ext.apply({xtype:'activitiProcessDetailForm'})]});
					cfg.down('form').items.getAt(0).setValue(detail.id);
					cfg.down('form').items.getAt(1).setValue(detail.version);
					cfg.down('form').items.getAt(2).setValue(detail.key);
					cfg.down('form').items.getAt(3).setValue(detail.suspended);
					cfg.down('form').items.getAt(4).setValue(detail.name);
					cfg.down('form').items.getAt(5).setValue(detail.description);
				}
			}, 
		failure:function(response) {
				Ext.toast("加载失败");
			}
		});
	},
	closeWindow : function(btn) {
		var win = btn.up('window');
		if(!win){
			win.close();
		}
	}
});