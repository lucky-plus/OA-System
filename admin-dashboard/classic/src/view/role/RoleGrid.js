/**
*订单模块子视图
*/
Ext.define('Admin.view.role.RoleGrid', {		//1.修改文件路径
      extend: 'Ext.grid.Panel',					//2.继承的组件类型
	//3.重写继承组件的属性：
    xtype: 'roleGrid',
	title:'<b>角色列表</b>',
	bind:'{roleLists}',
	id:'roleGrid',
	selModel: Ext.create('Ext.selection.CheckboxModel'),
	columns: [
		{text: 'roleId'	,sortable:true ,dataIndex:'roleId',hidden:true},
        {text: '角色名称' ,sortable:true ,dataIndex:'roleName' ,width:150},
		{text: '角色等级'  ,sortable:true ,dataIndex:'roleLevel'  ,width:125},
		{text: '所拥有的权限', sortable:true ,dataIndex:'modulesText' ,flex:1}
	],	


	tbar: Ext.create('Ext.Toolbar', {
			items:[ {
			text: '添加角色',
			iconCls:'x-fa fa-plus',
			ui:'soft-blue',
			//handler: 'roleGridAdd'
			listeners:{
				click:'roleGridAdd'
			}
		},'-', {
			text: '修改',
			iconCls:'x-fa fa-edit',
			handler: 'roleGridEdit'
		},'-', {
			text: '删除',
			iconCls:'x-fa fa-trash',
			handler: 'roleGridDelete'
		}]
	}),
	
	
	
	bbar: Ext.create('Ext.PagingToolbar', {
		bind:'{roleLists}',
		displayInfo: true,
		displayMsg: '第 {0} - {1}条， 共 {2}条',
		emptyMsg: "暂无数据",
	}),

	on: function(){
	    Ext.Msg.alert("test");
	    Ext.getCmp('deleteButton').hide();
	}
	
});