/* 
* @Author: xgd
* @Date:   2017-10-22 21:46:08
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 22:30:38
*/

Ext.define('Admin.view.activiti.activitiProcessModoler', {        //1.修改文件路径
      extend: 'Ext.container.Container',    //2.继承的组件类型
    //3.重写继承组件的属性：
    xtype: 'activitiProcessModoler',

	controller: 'activitiProcessModolerPanelViewController',
	
    viewModel : {
        type: 'activitiProcessModolerPanelViewModel'
    },

    layout:'fit',
    margin: '20 20 20 20',
    items: [{
        xtype: 'activitiProcessModolerPanel'
    }]
});
