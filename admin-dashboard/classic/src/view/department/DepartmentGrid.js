/**
*订单模块子视图
*/
Ext.define('Admin.view.department.DepartmentGrid', {		//1.修改文件路径
      extend: 'Ext.grid.Panel',					//2.继承的组件类型
    id:'departmentGrid',
	xtype: 'departmentGrid',
	title:'<b>组织架构</b>',
	bind:'{deptLists}',//viewModel
	selModel: Ext.create('Ext.selection.CheckboxModel'),
	columns: [
		{text: 'ID'			  ,sortable:true ,dataIndex:'deptId',hidden:true},
        {text: '部门名名称' ,sortable:true ,dataIndex:'deptName' ,flex:1}
	],
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{deptLists}',
		displayInfo: true,
		displayMsg: '第{0}-{1}条  共{2}条',
		emptyMsg: "没有任何记录",
	})
});
