/* 
* @Author: xgd
* @Date:   2017-10-22 23:34:06
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 23:35:15
*/

Ext.define('Admin.view.activiti.activitiProcessInstanceGridViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.activitiProcessInstanceGridViewController',
	
	searchWhereToBe:function(grid, rowIndex, colIndex){
		var prefix_url = 'runtime/process-instances/';
		var subfix_url = '/diagram';
		var cfg = Ext.apply({
            xtype:'activitiWindow',
			minHeight:500,
			minWidth:500,
			scrollable: true
        },{
            title:'流程实例进度',
            items:[Ext.apply({
				xtype:'panel',
				html:'<iframe width=1200 height=800 src="'+prefix_url+grid.getStore().getAt(rowIndex).getId()+subfix_url+'"></iframe>'
			})]
        });
        Ext.create(cfg);
	},
	stopInstance:function(){
		
	},
	showDetail : function(){
		
	}
});