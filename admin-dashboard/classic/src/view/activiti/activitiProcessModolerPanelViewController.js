/* 
* @Author: xgd
* @Date:   2017-10-22 23:34:06
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 23:35:15
*/

Ext.define('Admin.view.activiti.activitiProcessModolerPanelViewController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.activitiProcessModolerPanelViewController',
	showModelerEditor: function(grid, rowIndex, colIndex){
		var prefix_url = 'modeler.html?modelId=';
		var cfg = Ext.apply({
            xtype:'activitiWindow',
			minHeight:700,
			minWidth:1100,
			scrollable: true
        },{
            title:'编辑模型界面',
            items:[Ext.apply({
				xtype:'panel',
				html:'<iframe width=1200 height=800 src="'+prefix_url+grid.getStore().getAt(rowIndex).getId()+'"></iframe>'
			})]
        });
        Ext.create(cfg);
	}

});