/* 
* @Author: xgd
* @Date:   2017-10-22 23:33:33
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 23:35:16
*/

Ext.define('Admin.view.activiti.activitiProcessInstance', {        //1.修改文件路径
      extend: 'Ext.container.Container',    //2.继承的组件类型
    //3.重写继承组件的属性：
    xtype: 'activitiProcessInstance',

    viewModel : {
        type: 'activitiProcessInstanceGridViewModel'
    },
	controller: 'activitiProcessInstanceGridViewController',

    layout:'fit',
    margin: '20 20 20 20',
    items: [{
        xtype: 'activitiProcessInstanceGrid'
    }]
});
