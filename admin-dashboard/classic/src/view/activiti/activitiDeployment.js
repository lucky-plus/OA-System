/* 
* @Author: xgd
* @Date:   2017-10-22 21:44:26
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 22:22:09
*/

Ext.define('Admin.view.activiti.activitiDeployment', {        //1.修改文件路径
      extend: 'Ext.container.Container',    //2.继承的组件类型
    //3.重写继承组件的属性：
    xtype: 'activitiDeployment',
	controller: 'activitiDeploymentGridViewController',
	
    viewModel : {
        type: 'activitiDeploymentGridViewModel'
    },

    layout:'fit',
    margin: '20 20 20 20',
    items: [{
        xtype: 'activitiDeploymentGrid'
    }]
});
