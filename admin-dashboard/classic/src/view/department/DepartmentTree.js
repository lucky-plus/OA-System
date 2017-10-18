Ext.define('Admin.view.department.DepartmentTree', {		//1.修改文件路径
      extend: 'Ext.tree.Panel',					//2.继承的组件类型
    id:'departmentTree',
	xtype: 'departmentTree',
	deptment:undefined,
	deptFind:undefined,
	rootVisible: false,
	title:'<b>组织架构</b>',
	bind:'{deptLists}',//viewModel
	listeners:{
    cellclick:function(tree,td, cellIndex, record, tr, rowIndex){
			tree.up('panel').deptment = rowIndex;
			tree.up('panel').deptFind=record;
			Ext.getCmp('postGrid').getStore().load({params:{deptId : record.get('id')}});
		}
	},
	tbar: Ext.create('Ext.Toolbar', {
      items:[ {
        text: '新增部门',
        iconCls:'x-fa fa-plus',
        ui:'soft-blue',
        handler: 'deptGridOpenAddWindow'
      },'-', {
        text: '删除',
        iconCls:'x-fa fa-trash',
        handler: 'deptGridDeleteOne'
      },'-', {
        text: '修改',
        iconCls:'x-fa fa-edit',
        handler: 'deptGridOpenEditWindow'
      }
      ]
  }),
});