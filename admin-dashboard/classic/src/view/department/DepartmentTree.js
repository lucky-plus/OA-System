/**
*订单模块子视图
*/
Ext.define('Admin.view.department.DepartmentTree', {		//1.修改文件路径
      extend: 'Ext.tree.Panel',					//2.继承的组件类型
    id:'departmentTree',
	xtype: 'departmentTree',
	title:'<b>组织架构</b>',
	bind:'{deptLists}',//viewModel
	
	tbar: Ext.create('Ext.Toolbar', {
      items:[ {
        text: '新增部门',
        iconCls:'x-fa fa-plus',
        ui:'soft-blue',
        handler: 'deptGridOpenAddWindow'
      },'-', {
        text: '删除',
        iconCls:'x-fa fa-trash',
        //handler: 'noticeGridDeleteDate'
      }
      ]
  }),
});
