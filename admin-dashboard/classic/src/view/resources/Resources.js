/**
*订单模块主视图
	在主视图配置viewController和viewModel，
	那么主视图的子视图也可以访问配置好viewController和viewModel。
*/
Ext.define('Admin.view.resources.resources', {		//1.修改文件路径
      extend: 'Ext.container.Container',	//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'resources',

    controller: 'resourcesViewController',			//视图绑定viewController
    viewModel : {type: 'resourcesViewModel'},	//视图绑定viewModel
	

    layout:'fit',
    margin: '20 20 20 20',
    items: [{
		xtype: 'resourcesGrid'
	}]
});
